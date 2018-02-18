/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: LocationStack.java
 * Student name: Hannah Riedman
 *
 * LocationStack class for the Maze Solver
 */
import java.util.ArrayList;

/**
 * The LocationStack is a Stack created to hold locations. Also in
 * this file is the LocationStackNode which is used in conjunction
 * with the location stack.
 */
class LocationStack {
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;

  LocationStack() {
    this.top = null;
  }
  /**
   * Push method
   * @param loc: Object that is the current location index
   */
  void push(Location loc) {
    LocationStackNode newNode = new LocationStackNode(loc, this.top);
	  this.top = newNode;
  }
  /**
  * Pop method
  * this is used with getNextNode method to
  * grab the last node
  */
  void pop() {
    this.top = top.getNextNode();
  }
  /**
  * Top method
  * @return Location Object that represnts
  * location on top of stack
  */
  Location getTop() {
    if(isEmpty() == false) {
	    return top.getLocation();
	  } else {
	    return null;
	  }
  }
  /**
   * IsEmpty method
   * @return boolean that is true if the
   * stack is empty.
  */
  boolean isEmpty() {
    return (this.top == null);
  }
  /**
   * isOn method
   * This method checks to see if the parameter entered
   * equals any location in the stack.
   * @param loc that will be compared to elements in the stack
   * @return boolean that returns true if the parameter
   * was on the stack.
   */
  boolean isOn(Location loc) {
	  boolean itsOnStack = false; // boolean we will return
	  // array list to hold elements on the stack while we traverse through it
    ArrayList<Location> stackCopy = new ArrayList<Location>();
    int count = 0; // count variable to track how deep in stack we are

    // while the stack is not empty, we will compare the passed in location to
    // elements on the stack.
	  while (this.isEmpty() != true)  {
	    Location comparison = getTop();
	    if(loc.toString().equals(comparison.toString())) {
	      itsOnStack = true;
	      break; // break out of the loop if we have found a match
	    } else {
		    stackCopy.add(comparison); // add the current top of stack to arraylist
		    this.pop(); // remove top of stack so we can compare next element
		    count++;
	    }
	  }
	  // this for loop will iteratite for amount of times we "pop"ed elemnets from stack
	  while (count != 0) {
	    this.push(stackCopy.get(count-1)); // putting the stack back to normal
	    count--;
	  }
    return itsOnStack; // return boolean
  }
  /**
   * streamOut method
   * This will take in a parameter and traverse through the stack
   * adding its elements to a new stack. Then it will print out the
   * "reverse" stack so the elements of the stack are in the correct
   * order.
   * @param s is a location stack.
   */
  void streamOut(LocationStack s) {
    LocationStack reverse = new LocationStack();
    Location topOfStack;
	  while (s.isEmpty() != true)  {
	    topOfStack = s.getTop();
      reverse.push(topOfStack);
      s.pop();
    }
    while (reverse.isEmpty() != true) {
      topOfStack = reverse.getTop();
      System.out.println(topOfStack);
      reverse.pop();
    }
  }
}

class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  LocationStackNode(Location loc, LocationStackNode next) {
    this.location = loc;
    this.nextNode = next;
  }

  Location getLocation() {
    return location;
  }
  LocationStackNode getNextNode() {
    return nextNode;
  }
  void setNextNode(LocationStackNode next) {
    LocationStackNode newNode = new LocationStackNode(location, next);
	  next = newNode;
  }
}
