package ch.zhaw.ads;

/* interface of Traversal ADT */
public interface Traversal<T> {
  /* traverse elements of tree in preorder */
  public void preorder(Visitor<T> vistor);
  /* traverse elements of tree in inorder */
  public void inorder(Visitor<T> vistor);
  /* traverse elements of tree in postorder */
  public void postorder(Visitor<T> vistor);
  /* traverse elements of tree in levelorder */
  public void levelorder(Visitor<T> vistor);
    /* traverse elements of tree interval */
  public void interval(T min,T max, Visitor<T> vistor);
}
