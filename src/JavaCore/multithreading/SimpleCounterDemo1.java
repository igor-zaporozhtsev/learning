package JavaCore.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class SimpleCounterDemo1 {
//	static LongAdder value = new LongAdder();
	static int value;

	public static void main(String[] args) throws InterruptedException {


		Thread thread1 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread2 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread3 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread4 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread5 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread6 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread7 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread8 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread9 = new Thread(SimpleCounterDemo1::doTask);
		Thread thread10 = new Thread(SimpleCounterDemo1::doTask);

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

		System.out.println(value);
	}

	private static void doTask() {
		for (int i = 0; i < 1000; i++) {
			increment();
		}
	}

	public static void increment(){
//		value.increment();
		value++;
	}
}
