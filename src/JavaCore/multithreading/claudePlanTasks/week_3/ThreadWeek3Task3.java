package JavaCore.multithreading.claudePlanTasks.week_3;

/*
Полагодь deadlock: єдиний порядок захоплення локів.
* */

public class ThreadWeek3Task3 {
	/*
	 * jps -l
	 * jstack -l <PID>

	 *
	 * */
	public static void main(String[] args) throws InterruptedException {
		Account account1 = new Account(1,100);
		Account account2 = new Account(2, 100);

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
		Account first = from.id < to.id ? from: to;
		Account second = from.id > to.id ? from: to;

		synchronized (first){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			//from.value = from.value - amount;  -> correctness — чи правильно захищені змінні (тут все ок) перенесли цю строчку в нижній блок
			//пояснення в Notion deadlock
			synchronized (second){
				from.value = from.value - amount;
				to.value = to.value + amount;
			}
		}
	}

	static class Account{
		int id;
		int value;

		private Account(
			int id,
			int value
		) {
			this.id = id;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Account{" +
				"id=" + id +
				", value=" + value +
				'}';
		}
	}
}
