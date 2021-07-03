class SimpleThread extends Thread {

    private volatile boolean running = true;
    long count = 0;

    public SimpleThread(String str) {
        super(str);
    }

    public SimpleThread() {
        super();
    }

    public void run() {
        while (running) {
            count++;
            this.yield();
        }
    }

    public void terminate() {
        running = false;
        this.interrupt();
    }
}

public class PriorityTest {
    public static void main(String[] args) throws InterruptedException {
        int numthreads = 30;
        SimpleThread[] threads = new SimpleThread[numthreads];
        int[] priorities = new int[]{1, 1, 1}; // { 1, 5, 10 }
        // start threads
        for (int i = 0; i < numthreads; i++) {
            threads[i] = new SimpleThread();
            threads[i].setPriority(priorities[i % priorities.length]);
            threads[i].start();
        }
        // wait for some seconds
        Thread.currentThread().sleep(2000);
        // terminate all threads
        for (SimpleThread thread : threads) {
            thread.terminate();
        }
        // print results
        for (SimpleThread thread : threads) {
            System.out.println(thread.getName() +
                " : " + thread.getPriority() +
                " = " + thread.count);
        }
        System.out.println("main exits" + Thread.currentThread().toString());
    }
}
