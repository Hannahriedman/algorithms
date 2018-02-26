/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: Location.java
 * Student name: Hannah Riedman
 *
 * Location class for the Maze Solver
 */
import java.util.Scanner;

/**
 * The Location class is to create elements that each location will have.
 * The main elements of a Location is row,col and nextDirection.
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
