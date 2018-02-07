/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: Hannah Riedman
 *
 * First project to determine if files are valid or invalid
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  In this main function, it will read a line from the input file,
   *  isolate the function name in the line and if the function is called
   *  it adds the function name to the stack, if the function is returned
   *  it confirms that there is something in the stack and then compares 
   *  the topOfStack and function to see if they are equal, Which it will 
   *  then pop the function off of the stack. It reads every line until 
   *  there are no more lines in the file or unti it encounters an error. 
   *  Then the results are printed with details if the input is invalid.
   */
  public static void main(String[] args) {
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line;

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.  Otherwise, it will return true and the 
    // loop will continue.
    int lineNumber = 0;
    int maximum_depth = 0;
    int current_depth = 0;
    String function = "";
    String topOfStack= "";
    int index = 0;
    int errorCode = 0; // variable that holds the error codes
    int error_empty_stack = 1; // error when stack is empty and a function is returned
    int error_stack_mismatch = 2; // error when the returned function doesn't match top of stack
    
    while (input.hasNext()) {
      line = input.nextLine();
      lineNumber++;
      //System.out.println(line);
      index = line.indexOf(" ");
      function = line.substring(index).trim();
      if ( line.indexOf("call") != -1) {  
        callStack.push(function); // adds the function to the top of stack
        current_depth++;
        if (current_depth > maximum_depth) {
          maximum_depth++; // adds depth to the call stack  
        }
        
      } else if ( line.indexOf("return") != -1) {
          if (callStack.isEmpty()) { 
            // if the stack is empty then the function was not called
        	errorCode = error_empty_stack;
            break;
    	  } else {
    	    topOfStack = callStack.peek(); // holds what is currently in the top of stack
    	    if (!topOfStack.equals(function)) { 
    	      // if the function in the top of the stack doesn't equal the current function  
    	      errorCode = error_stack_mismatch;
    	      break;
    	    } else if (topOfStack.equals(function)) {
    	      // if the function in the top of the stack equals the current function
    	      callStack.pop(); 
    	      current_depth--;
    	    }  
    	  }
    	
      }
      
      
    }
    if (callStack.isEmpty() && errorCode == 0) { 
      // the input will be valid if the stack is empty and there are no errors
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth);
    } else { // the input must be invalid 
      System.out.println("Invalid trace at line " + lineNumber); 
      if (errorCode == 1){ 
        System.out.println("Returning from " + function + " which was not called");
        System.out.println("Stack trace:");
        // prints out the stack trace but if this error is called, there shouldn't be anything in stack
        while(!callStack.isEmpty()) { 
      	  topOfStack = callStack.peek();
      	  System.out.println(topOfStack);  
      	  callStack.pop();
      	}
      } else if (errorCode == 2) {
    	System.out.println("Returning from "+ function + " instead of " + topOfStack);
    	System.out.println("Stack trace:");
    	while(!callStack.isEmpty()) {
        	  topOfStack = callStack.peek();
        	  System.out.println(topOfStack);  
        	  callStack.pop();
        }
      } else {
    	System.out.println("Not all functions returned");
    	System.out.println("Stack trace:");
    	while(!callStack.isEmpty()) {
    	  topOfStack = callStack.peek();
    	  System.out.println(topOfStack);  
    	  callStack.pop();
    	}
      }
    } 
  }
}


