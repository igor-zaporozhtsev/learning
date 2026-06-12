package JavaCore.multithreading.dead_lock;

public class SimpleDeadLockDemo1 {


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

		//System.out.println(value);
	}

	private static void transfer(Account from, Account to, int amount) {
		synchronized (from){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			from.value = from.value - amount;
			synchronized (to){
				to.value = to.value + amount;
			}
		}
	}

	static class Account{
		int value;

		private Account(int value) {
			this.value = value;
		}
	}
}
