package fr.upem.algo.graphs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public interface Graph {
	/**
	 * Le nombre d'arêtes du graphe.
	 * @return le nombre d'arêtes du graphe
	 */
	int numberOfEdges();
	
	/**
	 * Le nombre de sommets du graphe.
	 * @return le nombre de sommets du graphe
	 */
	int numberOfVertices();

	/**
	 * Permet d'ajouter une arête orientée et pondérée au graphe.
	 * @param i la première extrémité de l'arête
	 * @param j la seconde extrémité de l'arête
	 * @param value le poids de l'arête
	 * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
	 * @throws IllegalArgumentException si value vaut 0 ou si il existe déjà une arête entre i et j
	 */
	void addEdge(int i, int j, int value);

	/**
	 * Teste l'existence d'une arête donnée
	 * @param i la première extrémité de l'arête
	 * @param j la seconde extrémité de l'arête
	 * @return true s'il existe une arête entre i et j; false sinon
	 * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
	 */
	boolean isEdge(int i, int j);

	/**
	 * Renvoie le poids d'une arête donnée.
	 * @param i la première extrémité de l'arête
	 * @param j la seconde extrémité de l'arête
	 * @return le poids de l'arête entre i et j
	 * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
	 */
	int getWeight(int i, int j);

	/**
	 * Un itérateur sur tous les voisins d'un sommet donné.
	 * @param i le sommet à partir duquel partent les arêtes fournies par l'itérateur
	 * @return un itérateur sur tous les voisins du sommet i
	 * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
	 */
	Iterator<Edge> edgeIterator(int i);

	/**
	 * Effectue une action sur tous les voisins (les arêtes) d'un sommet donné.
	 * @param i le sommet à partir duquel partent les arêtes traitées
	 * @param consumer l'acction effectuée sur toutes les arêtes voisines de i
	 * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
	 */
	void forEachEdge(int i, Consumer<Edge> consumer);

	/**
	 * Fournit une réprésentation du graphe au format .dot
	 * @return une réprésentation du graphe au format .dot
	 */
	default String toGraphviz() { 
		return null; // TODO à implémenter 
	}
	
	/**
	 * Création d'un graphe aléatoire avec un nombre de sommets et d'arêtes fixé
	 * @param n nombre de sommets
	 * @param nbEdges nombre d'arêtes
	 * @param wmax poids maximal (en valeur absolue) sur les arêtes
	 * @param factory une méthode qui étant donné un nombre de sommets n, fabrique et renvoie yun graphe vide à n sommets
	 * @return un graphe aléatoire construit à l'aide de factory yant exactement n sommets et nbEdges arêtes
	 * @throws IllegalArgumentException si le nombre d'arêtes est trop élevé par rapport au nombre de sommets
	 */
	public static Graph makeRandomGraph(int n, int nbEdges, int wmax, IntFunction<Graph> factory){
		return null; // TODO à implémenter 
	}

	/**
	 * Création d'un graphe à partir d'un fichier contenant le nombre de sommets et sa matrice
	 * @param path le chemin du fichier contenant la matrice du graphe
	 * @param factory une méthode qui étant donné un nombre de sommet n, fabrique et renvoie yun graphe vide à n sommet
	 * @return un graphe construit à l'aide de factory et dont les arêtes sont données dans le fihier indiqué dans path
	 * @throws IOException
	 */
	public static Graph makeGraphFromMatrixFile(Path path, IntFunction<Graph> factory) throws IOException {
		return null; // TODO à implémenter 
	}
}
