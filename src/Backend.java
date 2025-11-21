// --== CS400 File Header Information ==--
// Name: <Khyati Gupta>
// Email: <kgupta44@wisc.edu>
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents the backend of my application Doogle Maps and interacts
 * with the front end interface. Additionally, it loads data from an external
 * text file which provides two geographical locations and the miles between
 * them such that Dijkstra's shortest path algorithm finds the least weighted
 * path between them.
 * 
 * @author khyat
 *
 */
public class Backend extends CS400Graph {

	// initialises file
	File file;
	// creates and initialises CS400Graph graph of type String
	CS400Graph<String> graph = new CS400Graph<String>();

	/**
	 * This is a constructor for file which passes an input of type String.
	 * 
	 * @param input
	 */
	public Backend(String input) {

		file = new File(input);
	}

	/**
	 * This method creates a graph using data points from an external text file.
	 * 
	 * @return this.graph
	 */
	public CS400Graph<String> makeGraph() {

		Scanner scan;

		// try and catch block to find FileNotFoundException
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {

				// creates an array of type String to store cities by traversing a line and
				// uses spaces to configure each individual city
				String[] data = scan.nextLine().split(" ");
				// stores the distance in miles (given in text file) as weigth of two edges
				Integer weight = Integer.parseInt(data[2]);

				// insert vertices and edges to create the relative graph
				graph.insertVertex(data[0]);
				graph.insertVertex(data[1]);
				graph.insertEdge(data[0], data[1], weight);

			}

		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return this.graph;
		}

		// returns the graph created with given vertices and edges
		return this.graph;
	}

}
