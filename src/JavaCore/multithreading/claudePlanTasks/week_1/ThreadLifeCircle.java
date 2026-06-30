package JavaCore.multithreading.claudePlanTasks.week_1;

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLifeCircle {

	public static void main(String[] args) throws InterruptedException {
		Thread newThread = new Thread(() -> {
			//do some job
		});
		System.out.println(newThread.getState());

		Thread runnableThread = new Thread(() -> {
			//do some job
		});

		runnableThread.start();

		System.out.println(runnableThread.getState());

		Thread thread3 = new Thread(() -> {
			//do some job
				someJob();
		});

		Thread blockedThread = new Thread(() -> {
				someJob();
		});

		Thread stateChecker = new Thread(() -> {
			AtomicInteger counter = new AtomicInteger();

			while (true) {
				if (blockedThread.getState().equals(State.BLOCKED)) {
					System.out.println("blockedThread state: " + blockedThread.getState() + " " + counter.incrementAndGet());
					break;
				}
				System.out.println("blockedThread state: " + blockedThread.getState());
			}

			//System.exit(0);
		});

		stateChecker.setName("stateChecker");
		thread3.setName("thread3");
		blockedThread.setName("blockedThread");

		stateChecker.start();
		thread3.start();
		blockedThread.start();
	}


	static synchronized void someJob(){
		try {
			Thread.sleep(150);
		}catch (InterruptedException e){
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}

}
