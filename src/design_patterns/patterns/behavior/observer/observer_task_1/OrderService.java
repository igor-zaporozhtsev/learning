package design_patterns.patterns.behavior.observer.observer_task_1;

import design_patterns.patterns.behavior.observer.observer_task_1.solution.OrderObserver;

import java.util.List;

public class OrderService {

	private final List<OrderObserver> orderObservers;

	public OrderService(List<OrderObserver> orderObservers) {
		this.orderObservers = orderObservers;
	}

/*	private final EmailService emailService;
	private final InventoryService inventoryService;
	private final InvoiceService invoiceService;
	private final AuditService auditService;
	private final LoyaltyService loyaltyService;

	public OrderService(
		EmailService emailService,
		InventoryService inventoryService,
		InvoiceService invoiceService,
		AuditService auditService,
		LoyaltyService loyaltyService) {
		this.emailService = emailService;
		this.inventoryService = inventoryService;
		this.invoiceService = invoiceService;
		this.auditService = auditService;
		this.loyaltyService = loyaltyService;
	}*/

	public void placeOrder(OrderRequest request) {
		Order order = new Order(request);
		order.setStatus(OrderStatus.CONFIRMED);

		orderObservers.forEach(orderObserver -> orderObserver.onOrderPlaced(order));
	}
}