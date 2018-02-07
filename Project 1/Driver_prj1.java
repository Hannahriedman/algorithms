/* CMPT 435
 * Project 1 -- Maze Solver
 * Filename: Driver_prj1.java
 * Student name: Hannah Riedman
 * 1-31-18
 * Final Driver for the Maze Solver project
 */

import java.util.Scanner;

public class Driver_prj1 {
  /*
   * main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   *  
   *  In this main function, we will take in a file input, a test maze,
   *  and get the number of valid locations, cordinates of valid locations,
   *  and the start and end locations. This is done using the streamIn method
   *  in the Maze class. Then we will inialize the first location and push it 
   *  to the stack. The While loop will run through the maze until either the
   *  stack is empty and the location is done OR the end location is found.
   *  Depending on the while loop outcome, the last if statement will print
   *  Soultion Found or No Soultion Found.
	 */

  public static void main(String[] args) {
	  Scanner file = new Scanner(System.in);
	  
	  // inialize variables
	  String valid = "no";
	  boolean flag = true;
	  
	  // initalize Location, Maze and Stack 
	  Maze newMaze = new Maze();
	  LocationStack stack = new LocationStack();
	  Location loc = new Location();

	  // stream in the file
	  newMaze.streamIn(file);
	  
	  loc = newMaze.getStartLocation(); // start location
	  stack.push(loc); // push start to stack
	  loc.start(); // inialize the first direction

	  // while the flag is true, it will run through the logic
	  while(flag) {
		
        if(loc.isDone() && stack.isEmpty()) { // if there is no solution
          flag = false;
          valid = "no";
        } else if (newMaze.isEndLocation(loc)){ // if a solution is found
          flag = false;
          valid = "Valid";
        } else { // if the location is not done or the end location,
        // and the stack still has locations, continue.
          Location neighbor = loc.nextNeighbor(); // create a neighbor location 
         
          if (newMaze.isValidLocation(neighbor) && !(stack.isOn(neighbor))) {
        	// if neighbor is a valid location and is not on stack then push it
            stack.push(neighbor);
            if (newMaze.isEndLocation(neighbor)) { 
            // if its the end location then break out of loop
              valid = "Valid"; // soltuion found 
              flag = false;
            } else {
              loc = neighbor;
              loc.start();
            } 
          } else if (loc.isDone()) {  // if location is done 

        	stack.pop(); // take it off the stack
        	if (stack.isEmpty()) { 
        	// if the stack is empty then break out of loop 
              flag = false; 
              valid = "no"; // no soltuion found
        	} else {
              // set the new top of stack to the location.
              loc = stack.getTop(); 
            }
          }
        }
	  }
	  
	  // checks if soltuion was found and prints accurate message.
	  if (valid.equals("Valid")) {
	    System.out.println("Solution found:");
	    stack.streamOut(stack);
	  } else {
		System.out.println("No solution found"); 
	  }

  }
}

