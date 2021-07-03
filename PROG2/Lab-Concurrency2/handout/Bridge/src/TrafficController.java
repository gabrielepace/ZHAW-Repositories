import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
 * Controls the traffic passing the bridge
 */
public class TrafficController {

	private boolean bridgeOccupied = false;
	private Lock mutex = new ReentrantLock();
	private Condition enterBridge = mutex.newCondition();

	/* Called when a car wants to enter the bridge form the left side */
	public void enterLeft() {
		mutex.lock();
		try {
			while (bridgeOccupied) {
				enterBridge.await();
			}
			bridgeOccupied = true;
		} catch (InterruptedException e) {
			System.err.println("Interrupt: " + e.getMessage());
		} finally {
			mutex.unlock();
		}
	}

	/* Called when a wants to enter the bridge form the right side */
	public void enterRight() {
		mutex.lock();
		try {
			while (bridgeOccupied) {
				enterBridge.await();
			}
			bridgeOccupied = true;
		} catch (InterruptedException e) {
			System.err.println("Interrupt: " + e.getMessage());
		} finally {
			mutex.unlock();
		}
	}

	/* Called when the car leaves the bridge on the left side */
	public void leaveLeft() {
		mutex.lock();
		try {
			bridgeOccupied = false;
			enterBridge.signal();
		} finally {
			mutex.unlock();
		}
	}

	/* Called when the car leaves the bridge on the right side */
	public void leaveRight() {
		mutex.lock();
		try {
			bridgeOccupied = false;
			enterBridge.signal();
		} finally {
			mutex.unlock();
		}
	}
}