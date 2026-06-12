package design_patterns.patterns.behavior.observer.observer_task_1;

import design_patterns.patterns.behavior.observer.observer_task_1.solution.OrderObserver;

public class InvoiceService implements OrderObserver {
	@Override
	public void onOrderPlaced(Order order) {
		System.out.println("Invoice generated for order " + order.getId()
			+ ", amount: " + order.getTotalAmount());
	}
}