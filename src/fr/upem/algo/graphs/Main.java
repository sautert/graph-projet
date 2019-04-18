package fr.upem.algo.graphs;

import java.util.ArrayList;

import fr.upem.algo.parse.ParseGraph;

public class Main {


	public static void main(String[] args) {
		
		Graph graph = ParseGraph.parseGr(args[0]);
		int[][] table = ParseGraph.parseCo("./ressources/test.co");
		int first_node=Integer.parseInt(args[2]);
		int last_node=Integer.parseInt(args[3]);
		ArrayList dijkstraList= dijkstra.getShortestPath(graph, table, first_node, last_node);
		System.out.println("Dijkstra:");
		System.out.println(printPath(dijkstraList, first_node, last_node));
		ArrayList astarList= astar.getShortestPath(graph, table, first_node, last_node);
		System.out.println("Astar:");
		System.out.println(printPath(astarList, first_node, last_node));
	}

	private static String printPath(ArrayList list, int first_node, int last_node) {
		StringBuilder sb=new StringBuilder("Chemin de lg. ");
		sb.append(list.get(0));
		sb.append(" allant de ");
		sb.append(first_node);
		sb.append(" à ");
		sb.append(last_node);
		sb.append(" = ");
		for(int i=1; i<list.size();i++) {
			sb.append(list.get(i));
			sb.append(" -> ");			
		}
		sb.substring(0 , sb.length()-4);
		return sb.toString();
		
	}

}
