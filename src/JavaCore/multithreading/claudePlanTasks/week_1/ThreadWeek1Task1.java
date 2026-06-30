package JavaCore.multithreading.claudePlanTasks.week_1;

import org.jetbrains.annotations.NotNull;

public class ThreadWeek1Task1 {

	public static void main(String[] args) {
		Job job1 = new Job("thread job1");

		Thread job2 = new Thread(new Job2(), "thread job2");


		job1.start();
		job2.start();
		System.out.println("main: " + Thread.currentThread().getName());

	}

}


class Job extends Thread{

	public Job(@NotNull String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class Job2 implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
