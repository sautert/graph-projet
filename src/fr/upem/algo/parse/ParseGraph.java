package fr.upem.algo.parse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import fr.upem.algo.graphs.AdjGraph;
import fr.upem.algo.graphs.Graph;

/**
 * This class serves as a storage class for the parse methods and their tools methods 
 * @author tom
 */
public class ParseGraph {
	/**
	 * Private method to check the extension of a file
	 * @param file The name of the file
	 * @param extension The extension to check
	 * @return true if the file have the extension asked
	 */
	private static boolean checkExtension(String file, String extension) {
		return (file.endsWith(extension));
	}

	/**
	 * Private method to display errors in the file. it don't throw exceptions
	 * @param problem The string to explain the problem
	 * @param numLine the number of the line where it's happening
	 * @param line The line where the problem was discover
	 */
	private static void putError(String problem, int numLine, String line) {
		System.out.println(problem + " on line (" + line + ") : "+ line);
	}
	
	public static Graph parseGr(String file) {
		if (!checkExtension(file, ".gr")) {
			throw new IllegalArgumentException("The files don't have the right extensions");
		}
		Graph graph = null;
		int numLine = 0;
		String currentLine;
		System.out.println("\n--- Starting parse the .gr file ---\n");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
				var str = currentLine.split(" ");
				switch (str[0]) {
				case "c" :
					if (currentLine.length() > 2) {
						//System.out.println(currentLine.substring(2, currentLine.length()));
					}
					break;
				case "p" :
					if (str.length != 4 || !str[1].equals("sp")) {
						putError("p should be always followed by \"sp\"", numLine, currentLine);
						break;
					} else if (graph != null) {
						putError("The token \"p sp\" should be unique", numLine, currentLine);
						break;
					}
					graph = new AdjGraph(Integer.parseInt(str[2]) + 1);
					break;
				case "a" :
					if (str.length != 4 && graph == null) {
						putError("The first command have to be \"p sp\"", numLine, currentLine);
						break;
					}
					graph.addEdge(Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
					break;
				default :
					putError("Can't recognize the token", numLine, currentLine);
					break;
				}
				numLine++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			putError("Bad number ", numLine, "");
		}
		return graph;
	}

	/**
	 * Parse the .co file as described here : http://users.diag.uniroma1.it/challenge9/format.shtml and return the coordinate with a int[][]
	 * <! Important !> The id of the arc are considered as numerical value only
	 * @param file the file should be on format .co
	 * @return int[][] which contains all coordinate associated with the id
	 */
	public static int[][] parseCo(String file) {
		if (!checkExtension(file, ".co")) {
			throw new IllegalArgumentException("The files don't have the right extensions");
		}
		System.out.println("\n--- Starting parse the .co file ---\n");
		int numLine = 0;
		String currentLine;
		int[][] tab = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
				var str = currentLine.split(" ");
				switch (str[0]) {
				case "c" :
					if (currentLine.length() > 2) {
						//System.out.println(currentLine.substring(2, currentLine.length()));
					}
					break;
				case "p" :
					if (str.length != 5 && !str[1].equals("aux") && !str[2].equals("sp") && !str[3].equals("co")) {
						putError("p should be always followed by \"aux sp co\"", numLine, currentLine);
						break;
					} else if (tab != null) {
						putError("The token \"p sp\" should be unique", numLine, currentLine);
						break;
					}
					tab = new int[Integer.parseInt(str[4] + 1)][2];
					break;
				case "v" :
					if (str.length != 4 && tab == null) {
						putError("The first command have to be \"p sp\"", numLine, currentLine);
						break;
					}
					tab[Integer.parseInt(str[1])][0] = Integer.parseInt(str[2]);
					tab[Integer.parseInt(str[1])][1] = Integer.parseInt(str[3]);
					break;
				default :
					putError("Can't recognize the token", numLine, currentLine);
					break;
				}
				numLine++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			putError("Bad number ", numLine, "");
		}
		return tab;
	}

	public static void main(String args[]) {
		Graph graph = ParseGraph.parseGr("./ressources/test.gr");
		System.out.println(graph.toGraphviz());
		int[][] tab = ParseGraph.parseCo("./ressources/test.co");
		for (int i = 1; i < graph.numberOfVertices(); i++) {
			System.out.println("x:" + tab[i][0] + "-y:" + tab[i][1]);
		}
	}
}
