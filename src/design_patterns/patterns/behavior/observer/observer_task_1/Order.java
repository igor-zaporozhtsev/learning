package design_patterns.patterns.behavior.observer.observer_task_1;

import java.util.UUID;

public class Order {
	private final String id;
	private final String customerId;
	private final double totalAmount;
	private OrderStatus status;

	public Order(OrderRequest request) {
		this.id = UUID.randomUUID().toString();
		this.customerId = request.getCustomerId();
		this.totalAmount = request.getTotalAmount();
		this.status = OrderStatus.PENDING;
	}

	public String getId() { return id; }
	public String getCustomerId() { return customerId; }
	public double getTotalAmount() { return totalAmount; }
	public OrderStatus getStatus() { return status; }
	public void setStatus(OrderStatus status) { this.status = status; }
}