package design_patterns.patterns.behavior.observer.observer_task_1;

import java.util.List;

public class DemoOrderObserverTask1 {

	public static void main(String[] args) {

		OrderService orderService = new OrderService(
			List.of(
				new EmailService(),
				new InventoryService(),
				new InvoiceService(),
				new AuditService(),
				new LoyaltyService()
			)
		);

		OrderRequest request = new OrderRequest("customer-42", 149.99);
		orderService.placeOrder(request);
	}
}
