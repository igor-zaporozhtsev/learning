package JavaCore.multithreading;

public class RaceCondition2 {

	public static void main(String[] args) throws InterruptedException {
		//read_modify_write();
		read_modify_write_2();
	}

	private static void read_modify_write_2() throws InterruptedException {
		Order order = new Order();

		Runnable t1 = () -> {
			String current = order.getStatus();        // READ
			String next = current + "_processed_A";    // MODIFY
			order.setStatus(next);                    // WRITE
		};

		Runnable t2 = () -> {
			String current = order.getStatus();        // READ
			String next = current + "_processed_B";    // MODIFY
			order.setStatus(next);                    // WRITE
		};

		Thread a = new Thread(t1);
		Thread b = new Thread(t2);

		a.start();
		b.start();

		a.join();
		b.join();

		System.out.println(order.getStatus());
	}

	private static void read_modify_write() {
		Order order = new Order("NEW");

		Runnable t1 = () -> {
			// хоче перевести NEW → PAID
			if (order.getStatus().equals("NEW")) {     // READ
				order.setStatus("PAID");               // WRITE
			}
		};

		Runnable t2 = () -> {
			// хоче перевести NEW → SHIPPED (наприклад, інший процес)
			if (order.getStatus().equals("NEW")) {     // READ
				order.setStatus("SHIPPED");            // WRITE
			}
		};

		new Thread(t1).start();
		new Thread(t2).start();

		System.out.println(order);
	}

}




class Order{
	private String status = "NEW";

	public Order() {
	}

	public Order(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order{" +
			"status='" + status + '\'' +
			'}';
	}
}
