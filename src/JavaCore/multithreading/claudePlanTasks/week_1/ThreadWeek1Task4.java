package JavaCore.multithreading.claudePlanTasks.week_1;

public class ThreadWeek1Task4 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(()->{
			while (!Thread.currentThread().isInterrupted()) { //while (true) - теж працює, але може стати проблемою вкінці опис коли
				System.out.println(Thread.currentThread().getName());
				try {
					//sleep() або (або інший blocking-виклик) при interrupt() перевіряє isInterrupted() кидає exception та JVM скидає прапорець isInterrupted() → false
					// наприклад - queue.take(), latch.await(), socket.accept()
					//тобто в коді я в циклі перевіряю самостійно isInterrupted плюс блокуючий виклик в середені перевіряє також isInterrupted
					Thread.sleep(200);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); //без цього у нас буде постійний цикл через isInterrupted() → false
					break;
				}
			}
			System.out.println("Stopped cleanly.");
		}
		);

		thread1.start();
		Thread.sleep(200);
		thread1.interrupt();

	}
}

/* МЕНТАЛЬНА МОДЕЛЬ початкова: interrupt() механізм

 1. thread.interrupt() → встановлює прапорець isInterrupted() = true

 2. Два рівні реакції:
    - while (!isInterrupted())     → активна робота (потік не заблокований)
    - sleep()/take()/await()...    → заблокований стан (не може дійти до while)

 3. Blocking-виклик бачить прапорець true → кидає InterruptedException
    → JVM скидає прапорець false як частина механізму кидання

 4. catch (InterruptedException e):
    → Thread.currentThread().interrupt()  // відновлюємо прапорець назад у true
    → break                               // виходимо з циклу
    (порожній catch = проковтнути повідомлення, хтось вище не побачить сигнал)

    */




/*
* Коли while(true) може стати проблемою — якщо між break і наступною ітерацією є код без blocking-виклику:
while (true) {
    doHeavyWork();  // довга CPU-робота без sleep/take
    // interrupt() тут ніколи не спрацює через exception
    // і while(true) не перевіряє прапорець → потік не зупиниться
}
*
* Тому канонічний варіант — while (!isInterrupted()) — більш універсальний і явний.
 *
*
* */


class ThreadWeek1Task41 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			while (true) { //while (!Thread.currentThread().isInterrupted())
				try {
					// довга CPU-робота без будь-якого blocking-виклику
					//немає блокуючиого аиклики і немає нікому переводити прапореці isInterrupted -> false
					for (int i = 0; i < 1_000_000_000; i++) {
						Math.sqrt(i); // імітація важкої роботи
					}
					System.out.println("iteration done");
					// interrupt() прийшов під час Math.sqrt() —
					// але exception не кинулось, бо немає blocking-виклику
					// while(true) не перевіряє прапорець → іде на наступну ітерацію
				} catch (RuntimeException e){
					Thread.currentThread().interrupt();
					break;
				}

			}
		});

		thread1.start();
		Thread.sleep(100);
		thread1.interrupt(); // ← сигнал є, але потік його НІКОЛИ не побачить
		System.out.println("interrupt sent, but thread keeps running...");
	}
}

/*
Ментальна модель фінальна: interrupt() механізм

 1. thread.interrupt() → встановлює прапорець isInterrupted() = true

 2. Два сценарії:

 СЦЕНАРІЙ А — є blocking-виклик (sleep/take/await/accept)

   while (!isInterrupted()) {      ← перевірка 1 (між ітераціями)
       sleep()                     ← перевірка 2 (під час блокування)
   }

   sleep() бачить прапорець true
       → кидає InterruptedException
       → JVM скидає прапорець false (як частина механізму кидання)
   catch (InterruptedException e) {
       interrupt()  ← відновлюємо прапорець назад true (компенсація за скидання JVM)
       break
   }

 СЦЕНАРІЙ Б — немає blocking-виклику (CPU-робота)

   while (!isInterrupted()) {      ← єдина перевірка
       Math.sqrt(i)                ← не кидає InterruptedException
   }

   прапорець ніхто не скидає → while сам його читає і виходить
   try/catch не потрібен — компілятор навіть не дозволить

 ГОЛОВНЕ ПРАВИЛО:
 порожній catch = проковтнути повідомлення
 зловив InterruptedException → завжди interrupt() → відновлюєш сигнал для тих хто вище по стеку

 */
