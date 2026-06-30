package JavaCore.multithreading.claudePlanTasks.week_1;

public class ThreadWeek1Task2 {

	public static void main(String[] args) throws InterruptedException {
		VJobber vJobber = new VJobber();
		Thread thread1 = new Thread(vJobber);
		Thread thread2 = new Thread(vJobber);
		Thread thread3 = new Thread(vJobber);
		Thread thread4 = new Thread(vJobber);
		Thread thread5 = new Thread(vJobber);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();

		System.out.println("All threads done, main continues");


	}
}

class VJobber implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			/*
			* Проблема в іншому: ти проковтуєш interrupt-статус.
			* Коли InterruptedException спіймано, JVM скидає прапорець interrupted у false.
			* Якщо десь вище по стеку хтось перевіряє isInterrupted() — він не побачить, що потік просили зупинити.
			* Тому правило: зловив InterruptedException → одразу Thread.currentThread().interrupt() →
			* відновлюєш прапорець назад у true.
			* */

			//throw new RuntimeException(e);
			Thread.currentThread().interrupt();
		}
		System.out.println(Thread.currentThread().getName());
	}
}
