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
    Location loc2 = new Location();
    //Location compare = new Location();
	  // stream in the file
	  //newMaze.streamIn(file);

	  //loc = newMaze.getStartLocation(); // start location
	  loc.start(); // inialize the first iterationMode
    //loc = compare;
    loc.streamIn(file);
    loc2.streamIn(file);

    // test the isLess method
    if (loc.isLess(loc2)) {
      System.out.print(loc.word+" is less then "+loc2.word);
    } else {
      System.out.print(loc.word+" is not less then "+loc2.word);
    }
    // test the isEqual and the nextNeighbor method
    while (!loc.isEqual(loc2)){
      loc = loc.nextNeighbor();
      loc.streamOut();
    }

    // what the logic will start to look like once maze is working
    /*while (!loc.isEqual(loc2)){
      compare = loc.nextNeighbor();
      if (newMaze.isValidLocation(compare)) {
        loc = compare;
      }
      compare.streamOut();
    }*/

  }
}
