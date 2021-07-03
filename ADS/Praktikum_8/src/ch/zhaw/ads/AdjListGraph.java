package ch.zhaw.ads;
import java.util.LinkedList;
import java.util.List;

public class AdjListGraph<N extends Node,E extends Edge> 
		implements Graph<N, E> {
	private final List<N> nodes = new LinkedList<N>();
	private final Class nodeClazz;
	private final Class edgeClazz;

	public AdjListGraph(Class nodeClazz, Class edgeClazz) {
		this.nodeClazz = nodeClazz;
		this.edgeClazz = edgeClazz;
	}
  
	// f�ge Knoten hinzu, gebe alten zur�ck falls Knoten schon existiert
	public N addNode(String name) throws Throwable {
		N node = findNode(name);
		if (node == null) {
			node = (N) nodeClazz.newInstance();
			node.setName(name);
			nodes.add(node);
		}
		return node;
	}

	// f�ge gerichtete Kante hinzu
	public void addEdge(String source, String dest, double weight) throws Throwable {
		N src = addNode(source);
		N dst = addNode(dest);

		try {
			E edge = (E) edgeClazz.newInstance();
			edge.setDest(dst);
			edge.setWeight(weight);
			src.addEdge(edge);
		} catch (Exception e) {}
	}  	
  
	// finde den Knoten anhand seines Namens
	public N findNode(String name) {
		for (N node : nodes) {
			if (node.getName().equals(name)) {
				return node;
			}		
		}
		return null;
	}

	// Iterator �ber alle Knoten
	public Iterable<N> getNodes() {
		return nodes;
	}
}