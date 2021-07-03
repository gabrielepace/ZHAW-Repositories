public class Main {

    private static void nap(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            ;
        }
    }

    public static void main(String[] a) {
        final TrafficController controller = new TrafficController();
        final CarWorld world = new CarWorld(controller);
        final CarWindow win = new CarWindow(world);

        win.pack();
        win.setVisible(true);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    nap(25);
                    win.repaint();
                }
            }
        }).start();
    }
}
