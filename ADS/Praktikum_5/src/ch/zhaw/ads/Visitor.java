package ch.zhaw.ads;

/* interface of visitor ADT */
public interface Visitor<T> {
  /* called for each element in the tree */
  public void visit(T obj);
}