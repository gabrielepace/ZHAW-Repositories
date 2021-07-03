// Template for Lab P02 Excercise 1
class TrafficLight {
	private boolean red;

	public TrafficLight() {
		red = true;
	}

	public synchronized void passby() {
		// ToDo: wait as long the light is red
		while (red) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void switchToRed() {
		// ToDo: set light to red
		red = true;
	}

	public synchronized void switchToGreen() {
		// ToDo: set light to green
		// waiting cars can now pass by
		red = false;
		notifyAll();
	}
}

/*
 * 3c) Es müssen alle Fahrzeuge bzw. Threads informiert werden und daher
 * notifyAll()
 */