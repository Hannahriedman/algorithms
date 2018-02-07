/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: Maze.java
 * Student name: Hannah Riedman
 *
 * Maze class for the Maze Solver
 */

import java.util.Scanner;

/* A Maze object contains three things: the starting location, the
 * ending location, and a list of valid locations. It does not contain
 * any logic for actually solving the maze.
 *
 * The Maze constructor initializes all the data members that it can,
 * but the validLocationCount will not yet be known when the Maze is
 * constructed, so use a sensible value here.
 *
 * getStartLocation() returns the starting location of the maze.
 * isEndLocation() returns true if the given Location is the end of
 * the maze, otherwise false.  isValidLocation() returns true if the
 * given Location is in the list of valid Locations, otherwise false.
 *
 * streamIn() provides a means of streaming in a Maze object.  In
 * this version, streamIn() does not do any error checking.  We
 * assume that the entered data is perfect. Consider the following
 * when writing this function: what if we stream in a Maze object with
 * this function, and later call the same function on the same object?
 *
 * validLocationCount keeps the number of valid locations, which will
 * not be known until the object is streamed in. This is the length of
 * the validLocations array.
 *
 * validLocations is an array that contains all the locations that may
 * be used to solve the maze.
 *
 * startLocation holds the starting location of the maze, and
 * endLocation holds the target location of the maze.
 *
 * In this class we make the copy constructor private and illegal to
 * call, because it is not needed in this project, and we don't
 * want the compiler to provide them for us (since this class uses
 * dynamically allocated memory).  If these methods are called, they
 * will intentionally crash the program by the call to assert(false).
 */
class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    this.startLocation = null;
    this.endLocation = null;
    this.validLocations = null;
  }
  /**
   * GetStartLocation method
   * This will just return the starting location
   * @return startLocation the start of the maze.
   */
  Location getStartLocation() {
    return startLocation;
  }
  /**
   * isValidLoation method
   * @param loc location that will check if its a valid location
   * @return boolean will be true if location is valid 
   */
  boolean isValidLocation(Location loc) {
	boolean valid = false; // boolean that returns result
    for (int i=0; i < validLocations.length;i++) {
      if (loc.isEqual(validLocations[i])) {
        valid= true;
      }
    }
    return valid;
  }
  /**
   * isEndLocation method
   * @param loc location that will check if its the end location
   * @return boolean will be true if loaction is the end location
   */
  boolean isEndLocation(Location loc) {
    if (loc.isEqual(endLocation)) {
      return true;
    } else {
      return false; 
    }
  }
  /**
   * StreamIn method
   * This will intake Scanner input and set the first int to 
   * the number of valid locations. Then it will loop through 
   * the validlocations and add them into an array of locations.
   * Finally it will add the last two cordinates into the start 
   * and end location variables respectively.
   * @param input from the Scanner 
   */
  void streamIn(Scanner input) {
    validLocationCount = input.nextInt();
    validLocations = new Location[validLocationCount];
    
    for (int i =0; i <= validLocationCount;i++) {
      // adds all the valid loactions to the array
      if (i < validLocationCount) {
        Location loc = new Location();
    	loc.streamIn(input);
        validLocations[i] = loc; 
      } else { 
    	startLocation = new Location();
    	endLocation = new Location();
    	  
    	startLocation.streamIn(input);
    	endLocation.streamIn(input);
      }      
    } 
  }
}
