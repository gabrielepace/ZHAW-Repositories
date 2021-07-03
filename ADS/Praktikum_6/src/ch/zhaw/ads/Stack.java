package ch.zhaw.ads;

/**
 * An FIFO abstract data structure.
 *
 * @param <E> The type of object stored in this stack.
 */
public interface Stack<E> {

    /**
     * @param e Item to add to the top of the stack.
     */
    void push(E e);

    /**
     * @return Item removed form the top of the stack.
     */
    E pop();

    /**
     * @return Item at the top of the stack.
     */
    E peek();

    /**
     * @return Whether the stack empty.
     */
    boolean isEmpty();

    /**
     * @return Whether the stack full and nothing more can be added.
     */
    boolean isFull();
}
