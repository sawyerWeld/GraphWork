import java.util.ArrayList;

public class Node implements Comparable<Object>{
	private int x;
	private int y;
	private double dist;
	private Node previous;
	private ArrayList<Node> neighbors = new ArrayList<Node>();
	
	public Node(int x_, int y_) {
		x = x_;
		y = y_;
		dist = 0;
		previous = null;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public double getDist() {
		return dist;
	}
	
	public void setDist(double d) {
		dist = d;
	}
	
	public Node getPrev() {
		return previous;
	}
	
	public void setPrev(Node n) {
		previous = n;
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	
	public void printDepth() {
		System.out.printf("%s\n",toString());
		for (Node n : neighbors) {
			System.out.printf("\t%s\n",n);
		}
	}
	
	@Override
	public String toString() {
		return String.format("<%d, %d>", x, y);
	}
	
	
	public boolean equals(Node other) {
		return (x == other.getX() && y == other.getY());
	}
	
	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		Node n = (Node) other;
		if (n.getDist() > dist) {
			return 1;
		} else if (n.getDist() < dist) {
			return -1;
		}
		return 0;
	}
}
