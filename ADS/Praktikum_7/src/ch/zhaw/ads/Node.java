package ch.zhaw.ads;


import java.util.*;


public class Node<E> {
	protected String name; // Name
	protected List<E> edges; // Kanten   
	
	public Node() {
		edges = new LinkedList<E>(); 
	} 
	  	
	public Node(String name) {
		super();
		this.name = name;                
	}
	  
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	  
	public Iterable<E> getEdges() {
		return edges;
	}
    
	public void addEdge(E edge) {
		edges.add(edge);
	} 
 
}
