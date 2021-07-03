package ch.zhaw.ads;
/**
 * interface of Tree ADT
 */
public interface Tree<E> {
    /**
     * add an element to the tree
     */
    void add(E e);

    /**
     * remove an element; returns the element if found else return null
     */
    E remove(E e);

    /**
     * remove last element of the tree
     */
    E removeLast();

    /**
     * test if tree is empty
     */
    boolean isEmpty();

    /**
     * returns instance of class that implements traversal interface
     */
    Traversal<E> traversal();

    /**
     * number of elements
     */
    int size();

    /**
     * height of the tree
     */
    int height();

    /**
     * is the tree balanced
     */
    boolean balanced();
}
