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
	  Location start = new Location();
    ArrayQueue queue1 = new ArrayQueue();
    Location neighbor = new Location();
	  // stream in the file
	  newMaze.streamIn(file);

	  start = newMaze.getStartLocation(); // start location
	  start.start(); // inialize the first iterationMode

    //test isValid and isEnd methods
    if (newMaze.isValidLocation(start)) {
      System.out.println(start.word +" is valid! :) " );
    } else {
      System.out.println(start.word +" is not valid. :( ");
    }

    if (newMaze.isEndLocation(start)) {
      System.out.println(start.word +" is the end! :) " );
    } else {
      System.out.println(start.word +" is not the end. :( ");
    }

    queue1.add(start);
    queue1.getFront().streamOut();
    neighbor = start.nextNeighbor();
    neighbor.streamOut();
    queue1.add(neighbor);
    queue1.getFront().streamOut();
    neighbor = neighbor.nextNeighbor();
    queue1.add(neighbor);
    queue1.getFront().streamOut();
    // test the isLess method
    /*if (start.isLess(loc2)) {
      System.out.print(loc.word+" is less then "+loc2.word);
    } else {
      System.out.print(loc.word+" is not less then "+loc2.word);
    }*/
    // test the isEqual and the nextNeighbor method
  /*  while (!loc.isEqual(loc2)){
      loc = loc.nextNeighbor();
      loc.streamOut();
    }*/

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
