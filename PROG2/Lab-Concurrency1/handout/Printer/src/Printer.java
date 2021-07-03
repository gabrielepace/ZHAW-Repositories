public class Printer implements Runnable {

	private char ch;
	private int sleepTime;

	public Printer(char ch, int sleepTime) {
		this.ch = ch;
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " run laueft an");
		for (int i = 1; i < 100; i++) {
			System.out.println(ch);
			Thread.yield();
			/* // Aus der Klasse Printer1
			 * try { Thread.sleep(sleepTime); } catch (InterruptedException e) { }
			 */
		}
		System.out.println("\n" + Thread.currentThread().getName() + " run fertig");
	}

	public static void main(String[] arg) {
		Printer a = new Printer('.', 0);
		Printer b = new Printer('*', 0);
		Thread t1 = new Thread(a, "PrinterA");
		Thread t2 = new Thread(b, "PrinterB");
		t1.start();
		t2.start();
		
		// Aufgabe d
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main beendet");
	}

}
