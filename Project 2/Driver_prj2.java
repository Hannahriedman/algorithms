/* CMPT 435
 * Project 2 -- Word-Melt Solver
 * Filename: Driver_prj2.java
 * Student name: Hannah Riedman
 * 2-7-18
 * first Driver for the Word-Melt Solver project
 */

import java.util.Scanner;

public class Driver_prj2 {

  public static void main(String[] args) {
	  Scanner file = new Scanner(System.in);

	  // initalize Location, and Maze
	  Maze newMaze = new Maze();
	  Location loc = new Location();

	  // stream in the file
	  newMaze.streamIn(file);

	  loc = newMaze.getStartLocation(); // start location
	  loc.start(); // inialize the first iterationMode
    loc.streamOut();

  }
}
