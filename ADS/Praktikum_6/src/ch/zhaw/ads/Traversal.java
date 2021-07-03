package ch.zhaw.ads;

/**
 * interface of Traversal ADT
 */
public interface Traversal<E> {
    /**
     * traverse elements of tree in preOrder
     */
    public void preOrder(Visitor<E> vistor);

    /**
     * traverse elements of tree in inOrder
     */
    public void inOrder(Visitor<E> vistor);

    /**
     * traverse elements of tree in postOrder
     */
    public void postOrder(Visitor<E> vistor);

    /**
     * traverse elements of tree in levelOrder
     */
    public void levelOrder(Visitor<E> vistor);
}
