public interface Buffer<T> {
	boolean put(T element) throws InterruptedException;

	T get() throws InterruptedException;

	boolean empty();

	boolean full();

	int count();

	void print();

	void print2();
}
