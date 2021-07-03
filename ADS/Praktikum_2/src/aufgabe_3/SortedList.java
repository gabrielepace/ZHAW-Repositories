package aufgabe_3;

import java.util.Comparator;

import aufgabe_2.MyList;


/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class SortedList<E> extends MyList<E> {
    private final Comparator<? super E> comparator;

    public SortedList(final Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(final E e) {
        Node currentNode = getHead();
        boolean added = false;

        while (!added) {
            if (currentNode == adsnode || comparator.compare(e, currentNode.item) < 0) {
                new Node(e, currentNode);
                added = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return true;
    }

    @Override
    public void add(final int index, final E e) {
        throw new UnsupportedOperationException("error : Unsupported Operation Exception");
    }
}