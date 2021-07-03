import java.lang.reflect.Array;

public class CircularBuffer<T> implements Buffer<T> {
	private T[] items;
	private int count = 0;
	private int insertPos = 0;
	private int outPos = 0;
	private StringBuffer strBuffer;

	@SuppressWarnings("unchecked")
	public CircularBuffer(Class<T> clazz, int bufferSize) {
		strBuffer = new StringBuffer();
		if (bufferSize <= 1)
			bufferSize = 1;
		this.items = (T[]) Array.newInstance(clazz, bufferSize);
	}

	public boolean put(T item) {
		if (this.full())
			return false;
		items[insertPos] = item;
		insertPos = (insertPos + 1) % items.length;
		count++;
		return true;
	}

	public T get() {
		if (empty())
			return null;
		T item = items[outPos];
		outPos = (outPos + 1) % items.length;
		count--;
		return item;
	}

	public boolean empty() {
		return count == 0;
	}

	public boolean full() {
		return count >= items.length;
	}

	public int count() {
		return count;
	}

	public void print() {
		strBuffer.delete(0, strBuffer.length());

		int i = 0;
		strBuffer.append("||");

		if (full()) {
			for (i = 0; i < items.length; i++)
				strBuffer.append('*');
		} else {
			char c1;
			char c2;
			if (insertPos < outPos) {
				c1 = '*';
				c2 = '-';
			} else {
				c1 = '-';
				c2 = '*';
			}
			for (i = 0; i < outPos && i < insertPos; i++)
				strBuffer.append(c1);
			for (; i < outPos || i < insertPos; i++)
				strBuffer.append(c2);
			for (; i < items.length; i++)
				strBuffer.append(c1);
		}
		strBuffer.append("||    ");

		for (i = 0; i < count; i++)
			strBuffer.append('*');
		for (; i < items.length; i++)
			strBuffer.append('-');
		strBuffer.append("||");
		System.out.println(strBuffer);
	}

	public void print2() {
		System.out.println("Anzahl El im Puffer " + count);
		for (int i = 0; i < count; i++) {
			int index = (outPos + i) % items.length;
			System.out.println("index: " + index + " wert: " + items[index]);
		}
	}

}