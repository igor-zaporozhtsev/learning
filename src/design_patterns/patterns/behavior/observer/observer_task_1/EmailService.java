package design_patterns.patterns.behavior.observer.observer_task_1;

import design_patterns.patterns.behavior.observer.observer_task_1.solution.OrderObserver;

public class EmailService implements OrderObserver {
	@Override
	public void onOrderPlaced(Order order) {
		System.out.println("Email sent to customer " + order.getCustomerId()
			+ " for order " + order.getId());
	}
}
