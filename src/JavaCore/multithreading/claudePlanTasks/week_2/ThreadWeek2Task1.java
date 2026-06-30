package JavaCore.multithreading.claudePlanTasks.week_2;

import java.util.ArrayList;
import java.util.List;

public class ThreadWeek2Task1 {

	private static int count = 0;
	//private volatile static int count = 0; try to use volatile but output is 274440

	public static void main(String[] args) {
		// Крок 1: race condition — без синхронізації
		runTest("Race condition (no sync)", ThreadWeek2Task1::unsafeIncrement);

		count = 0;
		// Крок 2: фікс через synchronized-метод
		runTest("Fix 1: synchronized method", ThreadWeek2Task1::syncMethodIncrement);

		count = 0;
		// Крок 3: фікс через synchronized-блок
		runTest("Fix 2: synchronized block", ThreadWeek2Task1::syncBlockIncrement);
	};

	private static void runTest(String label, Runnable increment){
		count = 0;
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(()-> {
				for (int j = 0; j < 100_000_000; j++) {
					increment.run();
				}
			}));

		}

		threads.forEach(Thread::start);

		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
		System.out.println(label + ": " + count);
	}

	// Небезпечно — race condition
	private static void unsafeIncrement() {
		count++;
	}

	// Фікс 1: synchronized static method
	private static synchronized void syncMethodIncrement() {
		count++;
	}

	// Фікс 2: synchronized block на class-об'єкті
	private static void syncBlockIncrement() {
		synchronized (ThreadWeek2Task1.class) {
			count++;
		}
	}
}
