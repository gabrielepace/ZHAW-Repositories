package ch.zhaw.ads;
public class Edge<N>
{
    protected N dest;  // Target node of the edge
    protected double weight;  // the weight of the node 

    public Edge() {}
    
    public Edge(N dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }
	
	public void setDest(N node) {this.dest = node;}
	public N getDest() {return dest;}
	
	public void setWeight(double w) {this.weight = w;}
    double getWeight() {return weight;}
}