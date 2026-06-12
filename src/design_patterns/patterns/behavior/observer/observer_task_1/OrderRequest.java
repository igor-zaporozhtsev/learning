package design_patterns.patterns.behavior.observer.observer_task_1;

public class OrderRequest {
	private final String customerId;
	private final double totalAmount;

	public OrderRequest(String customerId, double totalAmount) {
		this.customerId = customerId;
		this.totalAmount = totalAmount;
	}

	public String getCustomerId() { return customerId; }
	public double getTotalAmount() { return totalAmount; }
}
