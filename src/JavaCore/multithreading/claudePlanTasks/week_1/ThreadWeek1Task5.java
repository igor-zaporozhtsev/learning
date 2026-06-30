package JavaCore.multithreading.claudePlanTasks.week_1;

public class ThreadWeek1Task5 {

	public static void main(String[] args) {
		Thread thread1 = new Thread(()-> System.out.println("start():   " + Thread.currentThread().getName()));
		Thread thread2 = new Thread(()-> System.out.println("run():   " + Thread.currentThread().getName()));
		thread1.start();
		thread2.run();
	}
}


/*
.start() vs .run() — у чому різниця на рівні JVM?
thread2.run() - job that executed in run method, will be executed in main thread?
But when we use thread1.start(),the job in run method will be execute in new thread.
Am I right?




* */





