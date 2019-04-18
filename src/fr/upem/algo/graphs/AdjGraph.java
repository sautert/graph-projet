package fr.upem.algo.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;


/**
 * This class implements graph as adjacent list
 * implements the interface Graph
 * The arc are stocked as ArrayList of LinkedList of Edge
 * @author tom
 */
public class AdjGraph implements Graph {
	private final ArrayList<LinkedList<Edge>> adj;
	private final int n;
	private int nbEdge;

	/**
	 * public constructor with the final size of the graph
	 * @param size size of the graph
	 */
	public AdjGraph(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		adj = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			adj.add(new LinkedList<>());
		}
		n = size;
		nbEdge = 0;
	}

	private boolean isLegal(int value, int min, int max) {
		return (value < min || value >= max);
	}

	/**
	 * @return the number of edge of the graph
	 */
	@Override
	public int numberOfEdges() {
		return nbEdge;
	}

	/**
	 * @return the number of vertex of the graph
	 */
	@Override
	public int numberOfVertices() {
		return n;
	}

	/**
	 * This method add an edge from a vertex to an other
	 * @param i the vertex from
	 * @param j the vertex to
	 * @param value the value of the edge to add
	 */
	@Override
	public void addEdge(int i, int j, int value) {
		if (!(isLegal(i, 0, n) && isLegal(j, 0, n) && value > 0)) {
			adj.get(i).add(new Edge(i, j, value));
		}
	}

	/**
	 * check if there is an edge between 2 vertices
	 * @param i vertex from
	 * @param j vertex to
	 * @return true if a edge exist between the vertices
	 */
	@Override
	public boolean isEdge(int i, int j) {
		for (var it = edgeIterator(i); it.hasNext();) {
			if (it.next().getEnd() == j) {
				return true;
			}
		}
		return false;
	}

	/**
	 * To get the weight of an edge between 2 vertices
	 * @param i vertex from
	 * @param j vertex to
	 * @return return the weight of the vertex
	 */
	@Override
	public int getWeight(int i, int j) {
		for (var it = edgeIterator(i); it.hasNext();) {
			var tmp = it.next();
			if (tmp.getEnd() == j) {
				return tmp.getValue();
			}
		}
		return 0;
	}

	/**
	 * Get an Iterator on a vertex
	 * @return return an iterator from the vertex specified in argument
	 */
	@Override
	public Iterator<Edge> edgeIterator(int i) {
		return adj.get(i).iterator();
	}

	/**
	 * Apply a consumer on the following edge from a vertex
	 */
	@Override
	public void forEachEdge(int i, Consumer<Edge> consumer) {
		for (var it = edgeIterator(i); it.hasNext();) {
			consumer.accept(it.next());
		}
	}

	/**
	 * Generate a graph of random vertices and edges
	 * @param vertices number of vertices
	 * @param edges number of edge between the vertices
	 * @return A random graph as specified in the args
	 */
	public static AdjGraph randomGraph(int vertices, int edges) {
		if (edges < 0 || edges > vertices * vertices) {
			throw new IllegalArgumentException();
		}
		AdjGraph graph = new AdjGraph(vertices); 
		for (int i = 0; i < edges;) {
			int from = (int) (Math.random() * (vertices));
			int to = (int) (Math.random() * (vertices));
			if (!graph.isEdge(from, to)) {
				graph.addEdge(from, to, 1);
				i++;
			}
		}
		return graph;
	}

	/**
	 * Generate a string of the vertices and edge in dot format
	 * @return a String which can 
	 */
	public String toGraphviz() {
		StringBuilder s = new StringBuilder();
		s.append("digraph G {\n");
		for (int i = 1; i < n; i++) {
			s.append(i).append(";\n");
			s.append(adj.get(i).stream().map(c -> c.toString()).collect(Collectors.joining("\n"))).append("\n"); // adj.get(i).stream().map(c -> c.toGraphviz(true))
		}
		s.append("}");
		return s.toString();
	}

	public static void main(String args[]) {
		var graph = AdjGraph.randomGraph(10, 25);
		System.out.println(graph.toGraphviz());
	}
}
