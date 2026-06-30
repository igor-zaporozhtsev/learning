package JavaCore.multithreading.claudePlanTasks.week_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Перепиши лічильник на ReentrantLock з lock()/unlock() у try/finally. Поясни, навіщо finally.
*/

public class ThreadWeek3Task1 {

	private static int count = 0;
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		runTest("ReentrantLock", ThreadWeek3Task1::increment);

	};

	private static void runTest(String label, Runnable increment){
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

	private static void increment() {
		/*
		lock.lock();
		count++;  // якщо тут кинеться RuntimeException — unlock() ніколи не викличеться
		lock.unlock();
		*/

		//better solution
		lock.lock();
		try{
			count++;
		} finally {
			lock.unlock();
		}
	}
}
