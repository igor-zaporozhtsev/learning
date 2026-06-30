package JavaCore.multithreading.claudePlanTasks.week_2;

public class ThreadWeek2Task2 {

	private static  boolean running = true;

	public static void main(String[] args) throws InterruptedException {

		Thread thread1 = new Thread(() -> {
			while (running){
				//System.out.println("running is true"); //fuck it's sync method
			}
		});

		thread1.start();

		Thread.sleep(50);

		running = false;
		System.out.println("finished");

	}
}
