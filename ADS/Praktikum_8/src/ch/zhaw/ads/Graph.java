package ch.zhaw.ads;
public interface Graph<N,E> {

  N addNode (String name) throws Throwable;
  
 
  N findNode(String name);

 
  Iterable<N> getNodes();

  void addEdge(String source, String dest, double weight) throws Throwable ;


}