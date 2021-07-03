package ch.zhaw.ads;

/* interface of Tree ADT */
public interface Tree<T> {
  /* add an element to the tree */
  void add(T o);
  /* remove an element; returns the element if found else return null */
  T remove(T o);
  /* test if tree is empty */
  boolean isEmpty();
  /* returns instance of class that implements traversal interface */
  Traversal<T> traversal();
  /* number of elements */
  int size();
  /* height of the tree */
  int height();
  /* is the tree balanced */
  boolean balanced();
}