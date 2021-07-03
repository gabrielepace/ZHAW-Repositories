package ch.zhaw.ads;

import ch.zhaw.ads.CommandExecutor;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * ADS FS2019
 * 
 * Praktikum 7
 * 
 * Aufgabe 3: Erzeugen des Graphen
 * Aufgabe 4: Kürzeste Strecke
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class RouteServer implements CommandExecutor {

	@Override
	public String execute(String file) throws Exception {
		String[] rows = file.split("\n");
		Graph<DijkstraNode, Edge> graph = new AdjListGraph(DijkstraNode.class, Edge.class);
		for (int i = 0; i < rows.length; i++) {
			String[] parts = rows[i].split(" ");
			String from = parts[0];
			String to = parts[1];
			String distance = parts[2];
			try {
				graph.addEdge(from, to, Double.valueOf(distance));
				graph.addEdge(to, from, Double.valueOf(distance));
			} catch (Throwable t) {
				System.out.println("Fehler");
			}
		}

		breadthFirstSearch(graph.findNode("Winterthur"), graph.findNode("Lugano"));
		DijkstraNode current = graph.findNode("Lugano").prev;
		System.out.println("Von Lugano nach ");
		while (true) {
			if (!current.name.equals("Winterthur")) {
				System.out.println(current.name + " nach ");
				current = current.prev;
			} else {
				System.out.println(current.name);
				break;
			}
		}
		return String.valueOf(graph.findNode("Lugano").getDist());
	}

	private void breadthFirstSearch(DijkstraNode startNode, DijkstraNode goal) {
		PriorityQueue q = new PriorityQueue<DijkstraNode>();
		startNode.dist = 0;
		q.add(startNode);
		while (!q.isEmpty()) {
			DijkstraNode current = (DijkstraNode) q.poll();
			current.setMark(true);
			if (current == goal) {
				return;
			}
			Iterator<Edge> i = current.getEdges().iterator();
			while (i.hasNext()) {
				Edge currentEdge = i.next();
				DijkstraNode node = (DijkstraNode) currentEdge.getDest();
				if (!(node.getMark())) {
					Double dist = currentEdge.getWeight() + current.getDist();
					if ((node.prev == null) || (dist < node.dist)) {
						node.dist = dist;
						node.prev = current;
						q.add(node);
					}
				}
			}
		}
	}
}