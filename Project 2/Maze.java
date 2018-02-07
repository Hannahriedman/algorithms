/* CMPT 435
 * Project 2 - Shortest-path word-melt solver
 * Filename: Maze.java
 * Student name: Hannah Riedman
 *
 * Maze class for the Word-Melt Solver
 */

import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

/* Changes from Project 1:
 *
 * Data member validLocations was changed from a Location array to a
 * Set<Location>, but it still represents the group of locations that may be
 * visited in the maze.
 *
 * Data member validLocationCount was eliminated (because validLocations is no
 * longer an array).
 *
 * We let the compiler deal with the assignment operator, copy constructor, and
 * for this version of the Maze, you should use the default constructor for the
 * _usual_ and to initally allocate memory for the Set of validLocations using
 * the TreeSet implementation of a Set.
 */

class Maze {
  private Set<Location> validLocations;

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
   * @return boolean will be true if location is valid (if the
   * word is in the Dictionary)
   */
  boolean isValidLocation(Location loc) {
    return validLocations.contains(loc);
  }
  /**
   * isEndLocation method
   * @param loc location that will check if its the end location
   * @return boolean will be true if loaction is the end word
   */
  boolean isEndLocation(Location loc) {
    return loc.isEqual(endLocation);
  }
  /**
   * StreamIn method
   * This will intake Scanner input and set the first int to
   * the number of words in the dictionary. Then it will loop through
   * the dictionary and add them into a set of locations. Finally it
   * will add the last two words into the start and end location
   * variables respectively.
   * @param input from the Scanner
   */
  void streamIn(Scanner input) {
    int dictionaryNum = input.nextInt();
    System.out.println(dictionaryNum);
    for (int i =0; i <= dictionaryNum;i++){
      System.out.println("Count: "+i);
      if (i < dictionaryNum) {
        Location loc = new Location();
        loc.streamIn(input);
        loc.streamOut();
        if (!loc.word.isEmpty()) {
          validLocations.add();
        } else {
          i = 0;
        }
        loc.streamOut();
      } else {
        startLocation = new Location();
        endLocation = new Location();

        startLocation.streamIn(input);
        endLocation.streamIn(input);
      }
    }
  }
}
