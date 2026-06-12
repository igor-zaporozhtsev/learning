package design_patterns.patterns.behavior.observer.observer_task_1.solution;

import design_patterns.patterns.behavior.observer.observer_task_1.Order;

public interface OrderObserver {
	void onOrderPlaced(Order order);
}
