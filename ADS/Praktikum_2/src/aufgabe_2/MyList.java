package aufgabe_2;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class MyList<E> extends AbstractList<E> {
    protected final Node adsnode = new Node();
    private int size = 0;

    public MyList() {
        super();
    }

    public MyList(final Collection<? extends E> collection) {
        this();
        this.addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(final E e) {
        new Node(getTail(), e);
        return true;
    }

    @Override
    public void add(final int index, E e) {
        if (size() == index) {
            add(e);
        } else {
            new Node(e, getNode(index));
        }
    }

    @Override
    public void clear() {
    	adsnode.next = adsnode;
    	adsnode.previous = adsnode;
        size = 0;
        ++modCount;
    }

    @Override
    public E set(final int index, final E e) {
        final Node node = getNode(index);
        final E oldItem = node.item;
        node.item = e;
        return oldItem;
    }

    @Override
    public E get(final int index) {
        return getNode(index).item;
    }

    @Override
    public E remove(final int index) {
        return getNode(index).delete();
    }

    @Override
    public boolean remove(final Object object) {
        final Function<E, Boolean> removalCondition = (object == null) ? Objects::isNull : object::equals;

        Node currentNode = getHead();
        boolean removed = false;

        while (!removed && currentNode != adsnode) {
            if (removalCondition.apply(currentNode.item)) {
                currentNode.delete();
                removed = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return removed;
    }

    protected Node getHead() {
        return adsnode.next;
    }

    private Node getTail() {
        return adsnode.previous;
    }

    private int maxIndex() {
        return size() - 1;
    }

    private void incrementSize() {
        ++size;
    }

    private void decrementSize() {
        --size;
    }

    private MyList<E>.Node getNode(final int index) {
        if (index < 0 || maxIndex() < index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node currentNode;
        if (index < size() / 2) {
            currentNode = getHead();
            for (int currentIndex = 0; currentIndex < index; ++currentIndex) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = getTail();
            for (int currentIndex = maxIndex(); currentIndex > index; --currentIndex) {
                currentNode = currentNode.previous;
            }
        }

        return currentNode;
    }

    public class Node {
        public MyList<E>.Node next;
        public MyList<E>.Node previous;
        public E item;

        private Node() {
            this.next = this;
            this.previous = this;
        }

        public Node(final E item, final Node next) {
            final Node previous = next.previous;
            this.next = next;
            this.previous = previous;
            this.item = item;

            next.previous = this;
            previous.next = this;

            MyList.this.incrementSize();
            ++MyList.this.modCount;
        }

        private Node(final Node previous, final E item) {
            this(item, previous.next);
        }

        private E delete() {
            next.previous = this.previous;
            previous.next = this.next;

            MyList.this.decrementSize();
            ++MyList.this.modCount;

            return this.item;
        }
    }
}
