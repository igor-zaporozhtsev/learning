package interview.load_balancer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public class LoadBalancerDemo {

	public static void main(String[] args) {
		ServerPool serverPool = new ServerPool();
		Server server1 = new Server(1, "123.001.23.10", true);
		Server server2 = new Server(2, "123.001.23.11", true);
		Server server3 = new Server(3, "123.001.23.12", true);
		server1.setWeight(3);
		server2.setWeight(1);
		server3.setWeight(2);
		serverPool.addAll(List.of(server1, server2, server3));
//		LoadBalancingStrategy randomStrategy = new RandomStrategy();
//		LoadBalancingStrategy roundRobinStrategy = new RoundRobinStrategy();
		LoadBalancingStrategy weightedRoundRobinStrategy = new WeightedRoundRobinStrategy();
		LoadBalancer loadBalancer = new LoadBalancer(serverPool, weightedRoundRobinStrategy);
		loadBalancer.start();

	}
}

interface LoadBalancingStrategy{
	Server selectServer(List<Server> servers);
}

class LoadBalancer{
	ExecutorService executor = Executors.newFixedThreadPool(5);
	private final ServerPool serverPool;
	private final LoadBalancingStrategy strategy;

	LoadBalancer(
		ServerPool serverPool,
		LoadBalancingStrategy strategy
	) {
		this.serverPool = serverPool;
		this.strategy = strategy;
	}

	public Server execute(Request request){
		Server server = strategy.selectServer(serverPool.getActive());
		server.process(request);
		return server;
	}

	void start() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String json = scanner.nextLine();
				Request request = Request.toRequest(json); //mock path request
				executor.submit(() -> {
					execute(request);
				});
			}
		}
		executor.shutdown();
	}

}

class RandomStrategy implements LoadBalancingStrategy{

	private final RandomGenerator random = RandomGenerator.getDefault();

	@Override
	public Server selectServer(
		List<Server> servers
	) {
		if (servers.isEmpty()) {
			throw new IllegalStateException("No active servers available");
		}
		int index = random.nextInt(servers.size());
		return servers.get(index);
	}
}

class RoundRobinStrategy implements LoadBalancingStrategy {

	private int currentIndex = 0;

	@Override
	public Server selectServer(
		List<Server> servers
	) {
		Server server = servers.get(currentIndex);
		currentIndex = (currentIndex + 1) % servers.size();
		return server;
	}
}

class WeightedRoundRobinStrategy implements LoadBalancingStrategy {

	private int currentIndex = 0;

	@Override
	public Server selectServer(
		List<Server> servers
	) {
		List<Server> sortedByWeight = servers.stream()
			.sorted(Comparator.comparingInt(Server::getWeight).reversed())
			.toList();

		List<Server> weightedServers = new ArrayList<>();

		for (Server server : sortedByWeight) {
			for (int i = 0; i < server.getWeight(); i++) {
				weightedServers.add(server);
			}
		}

		Server server = weightedServers.get(currentIndex);
		currentIndex = (currentIndex + 1) % weightedServers.size();

		return server;
	}
}

class Server{
	private int id;
	private String host;
	private boolean isActive;
	private int currentNumberOfRequest;
	private int weight = 1;
	private AtomicInteger totalRequest = new AtomicInteger();

	public Server(
		int id,
		String host,
		boolean isActive
	) {
		this.id = id;
		this.host = host;
		this.isActive = isActive;
	}

	public void process(Request request){
		System.out.println(
			request+ "\n" +
			"serverId " + id + "\n" +
			"totalRequest " + totalRequest.incrementAndGet()
		);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public String getHost() {
		return host;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getCurrentNumberOfRequest() {
		return currentNumberOfRequest;
	}

	public int getTotalRequest() {
		return totalRequest.get();
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Server{" +
			"id=" + id +
			", host='" + host + '\'' +
			", isActive=" + isActive +
			", currentNumberOfRequest=" + currentNumberOfRequest +
			", totalRequest=" + totalRequest +
			", weight=" + weight +
			'}';
	}
}

class Request{
	private int id;
	private LocalDateTime date;
	private int processDuration;
	private String metadata;

	public Request(
		int id,
		String metadata
	) {
		this.id = id;
		this.metadata = metadata;
	}

	public static Request toRequest(String request) {
		RandomGenerator randomGenerator = RandomGenerator.getDefault();
		int number = randomGenerator.nextInt(100);
		return new Request(number, request + number);
	}

	@Override
	public String toString() {
		return "Request{" +
			"id=" + id +
			", date=" + date +
			", processDuration=" + processDuration +
			", metadata='" + metadata + '\'' +
			'}';
	}
}

class ServerPool{
	private final List<Server> servers;

	public ServerPool() {
		this.servers = new ArrayList<>();
	}

	public void add(Server server){
		servers.add(server);
	}
	public void addAll(List<Server> servers){
		this.servers.addAll(servers);
	}
	public void remove(int serverId){
		servers.removeIf(server -> server.getId() == serverId);
	}

	public List<Server> getAll(){
		return servers;
	}

	public List<Server> getActive(){
		return servers.stream()
			.filter(Server::isActive)
			.toList();
	}

	public Optional<Server> find(int serverId){
		return servers.stream()
			.filter(server -> server.getId() == serverId)
			.findFirst();
	}
}
