// --== CS400 File Header Information ==--
// Name: <Khyati Gupta>
// Email: <kgupta44@wisc.edu>
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the frontend of my application Doogle Maps and
 * interacts with the backend interface. It takes in user prompts and performs
 * necessary actions to ensure full functionality of Doogle Maps.
 * 
 * @author khyat
 *
 */
public class Frontend {

	// takes the external text file as an input
	static Backend backend = new Backend("DoogleMapsDataSet.txt");
	// scanner object to read user's command
	static Scanner scan = new Scanner(System.in);

	/**
	 * This method prints out a list of commands available to the user of Doogle
	 * Maps.
	 */
	public static void listCommands() {
		System.out.println(
				"\nLIST OF COMMANDS \nD: Display Cities \nR: Run Program \nL: List Commands \nA: About \nH: Help \nQ: Quit");
	}

	public static void main(String[] args) {

		// creates and initialises CS400Graph graph of type String using backend command
		CS400Graph<String> graph = backend.makeGraph();
		// creates an enum from the vertices in hashTable to create an arrayList of the
		// cities in the database
		Enumeration<CS400Graph<String>.Vertex> verticeList = graph.vertices.elements();
		// creates an arrayList of type String
		ArrayList<String> listOfCities = new ArrayList<String>();

		// while loop that adds cities to the arrayList
		while (verticeList.hasMoreElements()) {
			listOfCities.add(verticeList.nextElement().data);
		}

		System.out.println("Welcome to Doogle Maps! Find the shortest path between two geographical locations "
				+ "presented in the selection under Display Cities. Have fun exploring!");
		listCommands();

		while (true) {

			String userInput = scan.next();

			// if condition for displaying "List of Commands" when 'l' or 'L' key is pressed
			if (userInput.equalsIgnoreCase("L")) {
				listCommands();
			}

			// if condition for displaying "About" when 'a' or 'A' key is pressed
			if (userInput.equalsIgnoreCase("A")) {
				System.out.println(
						"Doogle Maps allow users to find the shortest path between a starting and ending point on the map. "
								+ "\nUsing Dijkstra, the user will be able to know what is the least weighted path through a list of "
								+ "geographic points between two destinations \nand if a path is present between two points in the map.");
				listCommands();
			}

			// if condition for displaying the cities available when 'd' or 'D' key is
			// pressed
			if (userInput.equalsIgnoreCase("D")) {

				// initialising an integer 'i' for providing index to the list of cities
				int i = 0;

				// loop for displaying cities stored in the arrayList with an index
				for (String s : listOfCities) {
					System.out.println(i + " " + s);
					i++;
				}
				listCommands();
			}

			// if condition for displaying "Help" when 'h' or 'H' key is pressed
			if (userInput.equalsIgnoreCase("H")) {
				System.out.println(
						"Press D to display the cities. \nPress R to run the application. \nPress L to list the available commands. "
								+ "\nPress Q to quit the application.");
				listCommands();
			}

			// if condition for running the algorithm when 'r' or 'R' key is pressed
			if (userInput.equalsIgnoreCase("R")) {

				System.out.println("Enter the number of starting location: ");
				int sourceNum = scan.nextInt();

				// while loop to take valid input for sourceNum
				while (sourceNum < 0 || sourceNum > 8) {
					System.out.println("Invalid input. Please try again.");
					sourceNum = scan.nextInt();
				}

				System.out.println("Enter the number of ending location: ");
				int targetNum = scan.nextInt();

				// while loop to take valid input for targetNum
				while (targetNum < 0 || targetNum > 8) {
					System.out.println("Invalid input. Please try again.");
					targetNum = scan.nextInt();
				}

				// try and catch block to find NoSuchElementException
				try {
					System.out.println("The shortest path between these locations is "
							+ graph.shortestPath(listOfCities.get(sourceNum), listOfCities.get(targetNum)) + ".");
					System.out.println("The shortest distance between these locations is "
							+ graph.getPathCost(listOfCities.get(sourceNum), listOfCities.get(targetNum)) + " miles.");
				} catch (NoSuchElementException e) {
					System.out.println("Invalid input. Please try again.");
				}
				listCommands();
			}

			// if condition for quitting the algorithm when 'q' or 'Q' key is pressed
			if (userInput.equalsIgnoreCase("Q")) {
				System.out.println("Thank you for using Doogle Maps!");
				break;
			}
		}
	}
}