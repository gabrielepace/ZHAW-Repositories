package ch.zhaw.ads;
import java.util.LinkedList;
import java.util.List;

/**
 * A stack using a {@link List} as a basis.
 */
public class ListStack<E> implements Stack<E> {

    private final List<E> list;

    /**
     * Creates a new stack using a {@link LinkedList} as a basis.
     */
    public ListStack() {
        this(new LinkedList<>());
    }

    /**
     * Creates a new stack.
     *
     * @param list The list to use as a basis.
     */
    public ListStack(List<E> list) {
        this.list = list;
    }

    @Override
    public void push(E e) {
        list.add(e);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The stack is empty.");
        }

        final int headPosition = list.size() - 1;

        final E head = list.get(headPosition);
        list.remove(headPosition);

        return head;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The stack is empty.");
        }

        return list.get(list.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
