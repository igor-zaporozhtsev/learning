package JavaCore.multithreading.dead_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleFixDeadLockDemo1 {

	private static final Lock lock = new ReentrantLock(true);

	/*
	* jps -l
	* jstack -l <PID>

	 *
	* */
	public static void main(String[] args) throws InterruptedException {
		Account account1 = new Account(100);
		Account account2 = new Account(100);

		Thread thread1 = new Thread(()-> transfer(account1, account2, 100));
		Thread thread2 = new Thread(()-> transfer(account2, account1, 50));
		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println(account1.value);
		System.out.println(account2.value);
	}

	private static void transfer(Account from, Account to, int amount) {


		try {
			lock.lock();
			Thread.sleep(200); // якщо потрібен для симуляції
			from.value -= amount;
			to.value   += amount; // обидві мутації під одним локом
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	private static void transfer2(Account from, Account to, int amount) { //wrong solution
		try {
			lock.lock();
			Thread.sleep(200);
			from.value = from.value - amount;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
		//може зайти інш потік
		try {
			lock.lock();
			to.value = to.value + amount;
		} finally {
			lock.unlock();
		}
	}

	static class Account{
		int value;

		private Account(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Account{" +
				"value=" + value +
				'}';
		}
	}
}
