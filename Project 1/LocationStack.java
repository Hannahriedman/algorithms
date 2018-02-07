/* CMPT 435
 * Project 1 - Maze Solver
 * Filename: LocationStack.java
 * Student name: Hannah Riedman
 *
 * LocationStack class for the Maze Solver
 */

import java.util.ArrayList;

/* Class declaration for a simple stack of Location objects. It is not
 * a difficult class; it can contain only Location objects. It can
 * grow and shrink because it is a linked structure. The class
 * LocationStackNode (below) encapsulates the nodes of the stack.
 *
 * Methods push(), pop(), and getTop() provide standard stack methods.
 * Using assert() to check for problems in these three methods could
 * be useful (hint).  isEmpty() returns true if the stack is empty,
 * false otherwise. isOn() returns true if the given Location is on
 * the stack, otherwise returns false.
 *
 * streamOut() streams out the stack from bottom to top. This method
 * should NOT make a copy of the stack. Instead, it should require two
 * passes over the stack to print the stack. The first pass will
 * traverse the stack to top->bottom, reversing the links of the nodes
 * as it goes. The second pass will traverse from bottom->top,
 * printing each Location as it is visited, and undoing the reversing
 * of the node links.
 *
 * The default constructor initializes the data members as
 * appropriate. The copy constructor is not usable in this class;
 * therefore it is private and will fail on an assert() if called.
 *
 * The data member top is a reference to the top node.
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
	//System.out.println(stackCopy.toString());
	//System.out.println(count);
	// this for loop will iteratite for amount of times we "pop"ed elemnets from stack
	while (count != 0) { 
	  this.push(stackCopy.get(count-1)); // putting the stack back to normal
	  count--;
	}
	//System.out.println("count: " +count);
	 
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

/* Class declaration for a Node on a LocationStack. Each node contains
 * a Location and a link to the next LocationStackNode (the one below
 * it on the stack). 
 *
 * The only constructor that may be used for this class is the one
 * which takes values to initialize its data members. Other
 * constructors may not be called as they are not necessary. These
 * restrictions help prevent us from accidentally making multiple
 * nodes that all point to the same next node.
 *
 * If we set a LocationStack objet to null, this should invoke the garbage
 * collector and delete the nextNode in LocationStackNode, so that deleting
 * the top of the stack has a chaining effect that deletes every node
 * on the stack.
 *
 * The get/set methods for this class are self-explanatory, and are
 * the interface by which you should modify a node as necessary.
 */
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
