import java.util.*;
import java.util.concurrent.locks.*;

// ForkManager manages the resources (=forks), used by the philosophers
class ForkManager {

	enum ForkState {
		free, awaited, occupied
	}

	class Fork {
		public ForkState forkState;
		public Condition cond;

		public Fork(Lock m) {
			cond = m.newCondition();
			forkState = ForkState.free;
		}
	}

	private int nrForks;
	private Fork[] forks;
	private Lock mutex;

	public ForkManager(int nrForks) {
		mutex = new ReentrantLock();
		this.nrForks = nrForks;
		forks = new Fork[nrForks];
		for (int i = 0; i < nrForks; i++)
			forks[i] = new Fork(mutex);
	}

	// erwerben
	public void acquireFork(int l, int r) {
		try {
			mutex.lock();
			while (forks[l].forkState == ForkState.occupied || forks[r].forkState == ForkState.occupied) // belegt
				forks[l].cond.await();
			forks[l].forkState = ForkState.occupied;
			forks[r].forkState = ForkState.occupied;
		} catch (InterruptedException e) {
		} finally {
			mutex.unlock();
		}
	}

	// freigeben
	public void releaseFork(int i) {
		try {
			mutex.lock();
			forks[i].forkState = ForkState.free;
			forks[i].cond.signal();
		} finally {
			mutex.unlock();
		}
	}

	public int left(int i) {
		return (nrForks + i - 1) % nrForks;
	}

	public int right(int i) {
		return (i + 1) % nrForks;
	}
}

enum PhiloState {
	thinking, hungry, eating;
}

class Philosopher extends Thread {
	private PhilosopherTable table;
	private PhiloState philoState = PhiloState.thinking;
	private int id;

	public Philosopher(PhilosopherTable table, int id) {
		this.id = id;
		this.table = table;
	}

	public PhiloState getPhiloState() {
		return philoState;
	}

	public long getId() {
		return id;
	}

	public int getIdOfLeftNeighbour() {
		return table.left(id);
	}

	public int getIdOfRightNeighbour() {
		return table.right(id);
	}

	private void think() {
		try {
			philoState = PhiloState.thinking;
			table.notifyStateChange(this);
			int time = 5;
			sleep((int) (Math.random() * time * 1000));
		} catch (InterruptedException e) {
		}
	}

	private void eat() {
		try {
			philoState = PhiloState.eating;
			table.notifyStateChange(this);
			int time = 1;
			sleep((int) (Math.random() * time * 1000));
		} catch (InterruptedException e) {
		}
	}

	private void takeForks() {
		philoState = PhiloState.hungry;
		table.notifyStateChange(this);

		ForkManager mgr = table.getForkManager();
		mgr.acquireFork(id, mgr.right(id));
		try {
			Thread.sleep(500); // Aufgabe 2a: Nimmt zuerst eine Gabel und dann wartet auf die zweite Gabel
		} catch (InterruptedException e) {
		}
		;
	}

	private void putForks() {
		ForkManager mgr = table.getForkManager();
		mgr.releaseFork(id);
		mgr.releaseFork(mgr.right(id));

	}

	public void run() {
		yield();
		while (true) {
			think();
			takeForks();
			eat();
			putForks();
		}
	}
}

class PhilosopherTable extends Observable {
	private int philoCount;
	private Philosopher[] philosophers;
	private ForkManager forkManager;

	public PhilosopherTable(int philoCount) {
		System.out.println("creating table ...");
		this.philoCount = philoCount;
		philosophers = new Philosopher[philoCount];
		forkManager = new ForkManager(philoCount);

		for (int i = philoCount - 1; i >= 0; i--) {
			philosophers[i] = new Philosopher(this, i);
		}
	}

	public ForkManager getForkManager() {
		return forkManager;
	}

	public void notifyStateChange(Philosopher sender) {
		setChanged();
		notifyObservers(sender);
	}

	public void start() {
		notifyStateChange(null);
		for (int i = philoCount - 1; i >= 0; i--) {
			philosophers[i].start();
			philosophers[i].setPriority(Thread.MIN_PRIORITY);
		}
	}

	public Philosopher getPhilo(int i) {
		return philosophers[i];
	}

	public int left(int i) {
		return (philoCount + i - 1) % philoCount;
	}

	public int right(int i) {
		return (i + 1) % philoCount;
	}

}