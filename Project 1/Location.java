/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: Location.java
 * Student name: Hannah Riedman
 *
 * Location class for the Maze Solver
 */

import java.util.Scanner;

/* An object of the Location class represents a location, and also the
 * ability to find out all the possible "neighbors" of this location
 * through an iteration interface.
 *
 * The coordinates inside the Location are hidden.  A client may
 * stream in a Location object, or stream it out, but may not view its
 * internals directly.  However, a client may test to see whether two
 * Location objects are equal (which is true only if their data
 * members row and col are equal).
 *
 * A Location object acts as an iterator over all neighbor locations.
 * Two Locations are neighbors if their rows are the same and their
 * columns differ by 1, or if their columns are the same and their
 * rows differ by 1. A client should be able to find all the neighbors
 * of a Location loc the following way:
 *
 *   loc.start();
 *   while (! loc.isDone()) {
 *       Location neighbor = loc.nextNeighbor();
 *       System.out.println(neighbor);
 *   }
 *
 * The start() method initializes the iteration capability of a
 * Location object.  The nextNeighbor() method does several things --
 * it creates a copy of the current Location object, modifies the copy
 * so that it represents a different location (either in row or
 * column), it advances its iterator state (so the next time it's
 * called it will do return a different neighbor), and it returns the
 * neighbor it created.  The isDone() method returns true when the all
 * the neighbors of a Location have been iterated over.
 *
 * Iteration must proceed in a well-defined way for this project. The
 * first neighbor visited is to the right of the current location,
 * then down, then to the left, then up. The nextNeighbor() method
 * should never return the same Location that it was called on (i.e. a
 * Location is never a neighbor of itself).
 *
 * A Location object keeps track of where it is in the iteration
 * sequence with the data member nextDirection, which should take the
 * value RIGHT, DOWN, LEFT, UP, or DONE. These constant values are
 * defined in the enum in the class.  Notice that this data member is
 * marked "mutable." This means it may be changed inside of a const
 * member method (while row and col may not).  Why do we make this
 * member mutable?
 *
 * The reason is that the iteration interface is not a part of the
 * *logical* representation of a Location. The logical representation
 * is just the coordinates the Location contains.  In other words, two
 * Locations are considered equal if their row and col data members
 * are the same, regardless of their iteration status. Also, when a
 * Location object is printed, only the row and column data members
 * are printed; not the iteration status. And when a Location object
 * is streamed in, only the row and col data members are affected.
 *
 * Therefore, start() and nextNeighbor() methods may be marked "const"
 * even though they change the iteration status. This is the
 * difference between "logical constness" and "physical constness."
 * Physical constness means that no bits are changed. Logical
 * constness means that nothing appears to change from the client's
 * point of view -- this is what we have here.
 *
 * The method isEqual() provides a means of comparing two Location
 * objects. Two Location objects are equal if they represent the same
 * row and col (again, regardless of each one's iteration status).
 *
 * The functions streamOut() and streamIn() provide a means
 * of streaming a Location object in or out. Method streamOut() should
 * not write an endline; let the caller determine whether it wants a
 * return.  
 *
 * The constructor should initialize the data members to reasonable
 * values.
 */
class Location {
  final int RIGHT = 0;
  final int DOWN  = 1;
  final int LEFT  = 2;
  final int UP    = 3;
  final int DONE  = 4;

  private int row;
  private int col;
  int nextDirection;   // mutable

  Location() {
    row = 0;
    col = 0;
    nextDirection = DONE;
  }
  /**
   * start method
   * This will set the nextDirection variable to RIGHT
   * to initialize it.
   */
  void start() {  // const
    nextDirection = RIGHT;
  }
  /**
   * NextNeighbor method
   * @return Location of the neighbor of the current locatinon
   */
  Location nextNeighbor() {  // const
    Location currentLoc = new Location();
    currentLoc.row = row;
    currentLoc.col = col;
    
    switch(nextDirection) {
      case RIGHT:
    	currentLoc.col = (col+1);
        break;
      case DOWN:
    	currentLoc.row = (row+1);
    	break;
      case LEFT:
    	currentLoc.col = (col-1);
        break;
      case UP:
    	currentLoc.row = (row-1);
        break;
      case DONE:
        return currentLoc;
      default:
    	return currentLoc;
    }
    nextDirection++;
    return currentLoc;
    
  }
  /**
   * isDone method
   * @return boolean will be true if nextDirection is done and
   * we have reached the end of the directions.
   */
  boolean isDone() {  // const
    return nextDirection == DONE;
  }
  /**
   * isEqual method
   * @param loc location that will check if it is eaual to this location.
   * @return boolean will be true if location matches.
   */
  boolean isEqual(Location loc) {  // const
    if (row == loc.row && col == loc.col) {
	  return true;
	} else {
	  return false;
	}
		  
  }
  /**
   * SteramOut method
   * This wil print out the row and col for the location.
   */
  void streamOut() {
    System.out.print(row + " " + col);
  }
  /**
   * streamIn method
   * This will set the frist two integers as the row and col
   * for the location.
   * @param input from the scanner
   */
  void streamIn(Scanner input) {
    row = input.nextInt();
    col = input.nextInt();
  }
  
  @Override
  public String toString() {
	return row + " " + col;  
  }
  
}
