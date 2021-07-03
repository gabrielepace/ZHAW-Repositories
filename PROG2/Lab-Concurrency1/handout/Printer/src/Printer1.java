class PrinterThread extends Thread {
    char ch;
    int sleepTime;

    public PrinterThread(String name, char c, int t) {
        super(name);
        ch = c;
        sleepTime = t;
    }

    public void run() {
    	if(!currentThread().equals(this)) {
    		return;
    	}
        System.out.println(getName() + " run laueft an");
        for (int i = 1; i < 100; i++) {
            System.out.print(ch);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        System.out.println('\n' + getName() + " run  fertig");
    }
}

public class Printer1 {
    public static void main(String[] arg) {
        PrinterThread a = new PrinterThread("PrinterA", '.', 10);
        PrinterThread b = new PrinterThread("PrinterB", '*', 20);
        a.start();
        b.start();
        b.run(); // wie kann das abgefangen werden?
    }
}
