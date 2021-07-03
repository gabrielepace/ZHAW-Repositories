import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GuardedCircularBuffer<T> extends CircularBuffer<T> {
	private final Lock mutex = new ReentrantLock();
	private final Condition notEmpty = mutex.newCondition();
	private final Condition notFull = mutex.newCondition();

	public GuardedCircularBuffer(Class<T> clazz, int bufferSize) {
		super(clazz, bufferSize);
	}

	@Override
	public T get() {
		mutex.lock();
		T item = null;
		try {
			while (super.empty()) {
				notEmpty.await();
			}
			item = super.get();
			notFull.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.unlock();
		}
		return item;
	}

	@Override
	public boolean put(T item) {
		mutex.lock();
		boolean succeed = false;
		try {
			while (super.full()) {
				notFull.await();
			}
			succeed = super.put(item);
			notEmpty.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.unlock();
		}
		return succeed;
	}
}