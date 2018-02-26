/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: Maze.java
 * Student name: Hannah Riedman
 *
 * Maze class for the Maze Solver
 */
import java.util.Scanner;

/**
 * The Maze class will hold all the attrubutes of a Maze. Most information
 * will be taken from an input file and the streamIn method will organize
 * this data into validLocations, endLocation, and the startLocation.
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
    // this.validLocations = null;
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
  // }
}
