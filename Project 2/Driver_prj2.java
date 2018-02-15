/* CMPT 435
 * Project 2 -- Word-Melt Solver
 * Filename: Driver_prj2.java
 * Student name: Hannah Riedman
 * 2-7-18
 * first Driver for the Word-Melt Solver project
 */

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.TreeMap;

public class Driver_prj2 {

  public static void main(String[] args) {
	  Scanner file = new Scanner(System.in);


    Boolean EndLocationFound = false;
	  // initalize Location, and Maze
	  Maze newMaze = new Maze();
	  Location start = new Location();
    ArrayQueue queue1 = new ArrayQueue();
    Location neighbor = new Location();
    ArrayDeque<Location> queue2 = new ArrayDeque<Location>();
    TreeMap<String,String> map1 = new TreeMap<String,String>();
	  // stream in the file
	  newMaze.streamIn(file);

	  start = newMaze.getStartLocation(); // start location
	  start.start(); // inialize the first iterationMode
    neighbor.start();
    System.out.print(queue1.getFront());
    //queue1.add(start);
    queue2.add(start);
    queue2.peek().streamOut();

    //while ((queue1.getFront() != null) && EndLocationFound) {
    while ((queue2.peek() != null) && !(EndLocationFound)) {
      Location currentloc = new Location();
      currentloc.start();
      currentloc = queue2.peek();
      //queue2.remove();

      do {
        neighbor = currentloc.nextNeighbor();
        //neighbor.streamOut();
        //currentloc.streamOut();
      } while (!(newMaze.isValidLocation(neighbor)));
      System.out.println("The neighbor:");
      neighbor.streamOut();
      queue2.add(neighbor);

      map1.put(currentloc.word,neighbor.word);
      if (newMaze.isEndLocation(neighbor)) { // test if the loc is the end
        EndLocationFound = true;
      }
      queue2.remove();
      queue2.peek().streamOut();
    }




    //test isValid and isEnd methods
    /*
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

    /**queue1.add(start);
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
