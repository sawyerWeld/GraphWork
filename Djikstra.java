package project;

import java.util.ArrayList;
import java.util.Collections;

public class Djikstra {

	ArrayList<Node> nodes = new ArrayList<Node>();
	double pathLength = 0;
	int edges = 0;
	int distanceCount = 0;
	int sortCount = 0;

	// CONNECTED GRAPH
	public void generateGraph(int size, int avgDegree) {
		Node prev = new Node((int) (Math.random() * 101), (int) (Math.random() * 101));
		nodes.add(prev);

		// Make a path of <size> nodes
		for (int i = 0; i < size - 1; i++) {
			int x = (int) (Math.random() * 101);
			int y = (int) (Math.random() * 101);
			Node n = new Node(x, y);
			nodes.add(n);
			n.addNeighbor(prev);
			prev.addNeighbor(n);
			prev = n;
		}

		// Make the graph connected to avgDegree
		// currently just adds size*avgDegree edges
		for (int i = 0; i < (size * (avgDegree-2))+1; i++) {
			int x = (int) (Math.random() * (nodes.size()));
			int y = (int) (Math.random() * (nodes.size()));
			Node a = nodes.get(x);
			Node b = nodes.get(y);
			if (!a.getNeighbors().contains(b) && !a.equals(b)) {
				a.addNeighbor(b);
				b.addNeighbor(a);
			}
		}
		edges = avgDegree * size / 2;
	}

	public void printGraph() {
		System.out.println("      Graph\n-----------------");
		for (Node n : nodes) {
			n.printDepth();
		}
	}
	
	/*
	 * Finds the distance between 2 nodes
	 */
	private double distance(Node a, Node b) {
		distanceCount++;
		
		double dX2 = Math.pow(b.getX() - a.getX(), 2);
		double dY2 = Math.pow(b.getY() - a.getY(), 2);
		return Math.sqrt(dX2 + dY2);
	}

	public Node pathFind(int s, int g) {
		Node source = nodes.get(0);
		Node goal = nodes.get(g);

		ArrayList<Node> Q = new ArrayList<Node>(); // create vertex set Q

		for (Node v : nodes) {
			v.setDist(Integer.MAX_VALUE);
			v.setPrev(null);
			Q.add(v);
		}

		source.setDist(0);

		while (!Q.isEmpty()) {
			Node u = findLowest(Q); // u ← vertex in Q with min dist[u]

			Q.remove(u);

			if (u.equals(goal)) {
				return goal;
			}

			ArrayList<Node> neighbors = getNeighborsinSet(u, Q);

			for (Node v : neighbors) { // for each neighbor v of u where v is
										// still in Q
				double alt = u.getDist() + distance(u, v);
				if (alt < v.getDist()) {
					v.setDist(alt);
					v.setPrev(u);
				}
			}
		}

		return null;
	}

	public ArrayList<Node> getNeighborsinSet(Node n, ArrayList<Node> set) {
		ArrayList<Node> ret = new ArrayList<Node>();
		for (Node neighbor : n.getNeighbors()) {
			if (set.contains(neighbor)) {
				ret.add(neighbor);
			}
		}
		return ret;
	}

	public ArrayList<Node> getPath(Node n) {
		double len = 0;
		ArrayList<Node> path = new ArrayList<Node>();
		while (n.getPrev() != null) {
			path.add(n);
			len += distance(n,n.getPrev());
			n = n.getPrev();
		}
		path.add(n);
		Collections.reverse(path);
		pathLength = len;
		return path;
	}

	private Node findLowest(ArrayList<Node> nodes) {
		sortCount++;
		
		Collections.sort(nodes);
		Collections.reverse(nodes);
		return nodes.get(0);
	}

	public void graphStats() {
		System.out.printf("\tVertices: %d Edges: %d Path: %.2f Sorts: %d Distances: %d\n",nodes.size(),edges,pathLength,sortCount,distanceCount);
	}
	
	public static void main(String args[]) {
		Djikstra djik = new Djikstra();
		djik.generateGraph(100, 3);
		djik.printGraph();
		Node goal = djik.pathFind(0, 99);
		System.out.println(djik.getPath(goal));
		djik.graphStats();
	}
}
