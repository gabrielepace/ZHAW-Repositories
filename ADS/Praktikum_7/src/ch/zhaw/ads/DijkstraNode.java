package ch.zhaw.ads;

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

class DijkstraNode<E> extends Node<E> implements Comparable<DijkstraNode>  {
	boolean mark;
	DijkstraNode<E> prev;
	double dist;

    public double getDist (){
    	return dist;
    }
    
    public void setDist(double dist) {
    	this.dist = dist;
    }
    
    public void setMark(boolean m) {
      mark = m;
    }

    public boolean getMark() {
      return mark;
    }

    public void setPrev(DijkstraNode<E> p) {
       prev = p;
    }

    public DijkstraNode<E> getPrev() {
       return prev;
    }
    
    public int compareTo(DijkstraNode n) {
    	// TODO: implement
    	if(this.getDist() > n.getDist()) {
            return 1;
        } else if (this.getDist() < n.getDist()) {
            return -1;
        } else return 0;
    }
}