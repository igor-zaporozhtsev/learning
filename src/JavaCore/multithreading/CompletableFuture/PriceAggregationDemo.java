package JavaCore.multithreading.CompletableFuture;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PriceAggregationDemo {

	public static void main(String[] args) {
		PriceProvider bloomberg = new PriceProvider() {
			public CompletableFuture<Double> getPrice(String instrument) {
				return CompletableFuture.supplyAsync(() -> {
					// симулюємо мережевий запит
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new RuntimeException(e);
					}
					return 150.25;
				});
			}

			public String getName() {
				return "Bloomberg";
			}
		};

		PriceProvider IB =  new PriceProvider() {
			public CompletableFuture<Double> getPrice(String instrument) {
				return CompletableFuture.supplyAsync(() -> {
					// симулюємо мережевий запит
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new RuntimeException(e);
					}
					return 150.26;
				});
			}

			public String getName() {
				return "IB";
			}
		};

		PriceAggregationService service = new PriceAggregationService(List.of(bloomberg, IB));
		AggregatedPrice aggregatedPrice;
		try {
			aggregatedPrice = service.getAggregatedPrice("APPL").get();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		System.out.println(aggregatedPrice);
	}

}

class PriceAggregationService {

	private final List<PriceProvider> providers;

	PriceAggregationService(List<PriceProvider> providers) {
		this.providers = providers;
	}

	CompletableFuture<AggregatedPrice> getAggregatedPrice(String instrument) {
		//stream api with CompletableFuture

		return null;

	}

	private AggregatedPrice aggregate(
		String instrument,
		BigDecimal price,
		List<String> sources
	) {
		return new AggregatedPrice(
			instrument,
			price,
			sources,
			Instant.now()
		);
	}
}

record AggregatedPrice(
	String instrument,
	BigDecimal price,
	List<String> sources,
	Instant timestamp
) {
	//як зробити щоб працював method reference без static


}

interface PriceProvider {

	CompletableFuture<Double> getPrice(String instrument);

	String getName();
}
