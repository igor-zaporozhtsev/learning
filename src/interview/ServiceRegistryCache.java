package interview;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceRegistryCache {

	public static void main(String[] args) {
		try (ServiceRegistry registry = new ServiceRegistry(4)) {
			registry.registryOrUpdate("PaymentService", new PaymentService("PaymentService-1"));
			registry.registryOrUpdate("PaymentService", new PaymentService("PaymentService-2"));
			CompletableFuture<Optional<Object>> paymentService = registry.getService("PaymentService");

			PaymentService service = (PaymentService) paymentService.get().get();
			System.out.println(service.name());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

class ServiceRegistry implements AutoCloseable {

	private final ConcurrentMap<String, Object> cache;
	private final ExecutorService executor;

	public ServiceRegistry(int numThreads) {
		this.cache = new ConcurrentHashMap<>();
		executor = Executors.newFixedThreadPool(numThreads);
	}

	Object register(
		String name,
		Object service
	) {
		//putIfAbsent?claude
		return cache.computeIfAbsent(name, f -> service);
	}

	void registryOrUpdate(
		String name,
		Object service
	) {
		cache.put(name, service);
	}

	CompletableFuture<Optional<Object>> getService(String name) {
		return CompletableFuture.supplyAsync(() -> Optional.ofNullable(cache.get(name)), executor);
	}

	@Override
	public void close() throws Exception {
		executor.shutdown();
	}
}

record PaymentService(String name) {

	@Override
	public String toString() {
		return "PaymentService{" +
			"name='" + name + '\'' +
			'}';
	}
}
