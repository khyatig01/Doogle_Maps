// --== CS400 File Header Information ==--
// Name: <Khyati Gupta>
// Email: <kgupta44@wisc.edu>
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * 
 * Tests the implementation of CS400Graph for the application Doogle Maps that
 * uses Dijsktra's Shortest Path algorithm.
 * 
 * @author khyat
 * 
 */

public class graphTest {

	private CS400Graph<String> testGraph;

	@BeforeEach
	/**
	 * Instantiate graph from the application.
	 */
	public void createGraph() {

		Backend backend = new Backend("DoogleMapsDataSet.txt");
		testGraph = backend.makeGraph();
		Enumeration<CS400Graph<String>.Vertex> verticeList = testGraph.vertices.elements();
		ArrayList<String> listOfCities = new ArrayList<String>();

	}

	/**
	 * 
	 * Tests to see if exception is thrown when either the start or
	 * end vertex is non-existent in the graph.
	 * 
	 */
	@Test
	public void testNonExistentVertex() {
		assertThrows(Exception.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				testGraph.shortestPath(null, "Milwaukee");
			}
		});

		assertThrows(Exception.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				testGraph.shortestPath("Oshkosh", "Madison");
			}
		});
	}

	/**
	 * 
	 * Tests to see if no such element exception is thrown when no path exists from
	 * the given start and end vertices.
	 * 
	 */
	@Test
	public void testNonExistentPath() {
		assertThrows(NoSuchElementException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				testGraph.shortestPath("Fitchburg", "Door-County");
			}
		});
	}

	/**
	 * 
	 * Test method that checks for the correct shortest path between the starting
	 * node and the end node.
	 * 
	 */
	@Test
	public void testPathContents() {
		assertTrue(
				testGraph.shortestPath("Green-Bay", "Fitchburg").toString().equals("[Green-Bay, Madison, Fitchburg]"));
		assertTrue(testGraph.shortestPath("Fitchburg", "Madison").toString().equals("[Fitchburg, Madison]"));
	}

	/**
	 * 
	 * Test method that checks for the correct distance/ weight of the path
	 * traversed between the starting node and the end node.
	 * 
	 */
	@Test
	public void testPathCosts() {
		assertTrue(testGraph.getPathCost("Milwaukee", "Fitchburg") == 86);
		assertTrue(testGraph.getPathCost("Eau-Claire", "Madison") == 158);
	}

	/**
	 * 
	 * Test method to confirm the sequence of values along the path from source node
	 * to furthest end node. The test also confirms the cumulative weight of this
	 * shortest path.
	 * 
	 */
	@Test
	public void testLongestShortestPath() {

		assertTrue(testGraph.shortestPath("Milwaukee", "La-Crosse").toString()
				.equals("[Milwaukee, Madison, Wisconsin-Dells, La-Crosse]"));
		assertTrue(testGraph.getPathCost("Milwaukee", "La-Crosse") == 194);
	}
}