package fr.upem.algo.parse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Arrays;

import fr.upem.algo.graphs.AdjGraph;
import fr.upem.algo.graphs.Graph;

public class ParseGraph {

	private static boolean checkExtension(String file, String extension) {
		return (file.endsWith(extension));
	}

	public static Graph parseGr(String file) {
		if (checkExtension(file, ".gr")) {
			throw new IllegalArgumentException("The files don't have the right extensions");
		}
		Graph graph = new AdjGraph(0);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (String currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
				var str = currentLine.split(" ");
				switch (str[0]) {

				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graph;
	}

	public static int[][] parseCo(String file) {
		if (checkExtension(file, ".gr")) {
			throw new IllegalArgumentException("The files don't have the right extensions");
		}


		return null;
	}
}
