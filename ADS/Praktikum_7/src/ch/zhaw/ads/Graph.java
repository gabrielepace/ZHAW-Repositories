package ch.zhaw.ads;

import java.util.*;

public interface Graph<N,E> {

  // f�ge Knoten hinzu, tue nichts, falls Knoten schon existiert
  N addNode (String name) throws Throwable;
  
  // finde den Knoten anhand seines Namens
  N findNode(String name);

  // Iterator �ber alle Knoten des Graphen
  Iterable<N> getNodes();
  
  // f�ge gerichtete und gewichtete Kante hinzu
  void addEdge(String source, String dest, double weight) throws Throwable ;


}