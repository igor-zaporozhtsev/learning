package JavaCore.multithreading.claudePlanTasks.week_1;

public class ThreadWeek1Task3 {

	private static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread3 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread4 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread5 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread6 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread7 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread8 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});
		Thread thread9 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});

		Thread thread10 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				increment();
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();

		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
		thread7.join();
		thread8.join();
		thread9.join();
		thread10.join();



		System.out.println(count);
	};

	private static void increment(){
		count++;
	}
}
