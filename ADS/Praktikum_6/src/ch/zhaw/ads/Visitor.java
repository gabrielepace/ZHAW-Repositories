package ch.zhaw.ads;

/**
 * interface of visitor ADT
 */
@FunctionalInterface
public interface Visitor<E> {
    /**
     * called for each element in the tree
     */
    public void visit(E e);
}
