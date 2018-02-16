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
import java.util.Set;
import java.util.Map;

public class Driver_prj2 {

  public static void main(String[] args) {
	  Scanner file = new Scanner(System.in);

    String valid = "";
	  boolean flag = true;
    Boolean EndLocationFound = false;
	  // initalize Location, and Maze
	  Maze newMaze = new Maze();
	  Location start = new Location();
    ArrayQueue queue1 = new ArrayQueue();
    //Location neighbor = new Location();
    ArrayDeque<Location> queue2 = new ArrayDeque<Location>();
    TreeMap<String,String> map1 = new TreeMap<String,String>();
	  // stream in the file
	  newMaze.streamIn(file);

	  start = newMaze.getStartLocation(); // start location
	  start.start(); // inialize the first iterationMode
    //neighbor.start();
    //System.out.print(queue1.getFront());
    //queue1.add(start);
    queue2.add(start);
    queue2.peek().streamOut();
    System.out.println("peek:"+ queue2.peek().word);
    map1.put("a",(queue2.peek().word));

    while (flag) {
      if (EndLocationFound) {
        flag =false;
        valid = "valid";
      } else if (queue2.isEmpty()) {
        flag=false;
        valid = "invalid";
      } else {
        Location currentloc = new Location();
        currentloc.start();
        currentloc = queue2.peek();
        //queue2.remove();
        Location neighbor = new Location();
        neighbor.start();
        do {
          neighbor = currentloc.nextNeighbor();
          neighbor.streamOut();
          if (newMaze.isValidLocation(neighbor)) {
            if (map1.containsValue(neighbor.word) &&
                map1.get(neighbor.word) == currentloc.word){
              // if the map contains the neighbor as a value and the key is
              // the current loc, then we have already placed this on the map.
              System.out.println("trouble word:"+neighbor.word);
            } else {
              System.out.println("Putting on map:"+neighbor.word);
              queue2.add(neighbor);
              map1.put(currentloc.word,neighbor.word);
            }

          }
          if (newMaze.isEndLocation(neighbor)){
            EndLocationFound = true;
          }
          //currentloc.streamOut();
        } while (!neighbor.isDone());
        System.out.println("the next currentloc:"+queue2.peek().word);
        queue2.remove();
        System.out.println("H");
        queue2.peek().streamOut();
        System.out.print("H");
      }


    }
    if (valid == "valid"){
      System.out.println("Solution Found:");
      for(Map.Entry<String,String> words: map1.entrySet()) {
        System.out.println(words.getValue());
      }
      // print out map
    } else {
      System.out.println("No Soultion Found");
    }


    //while ((queue1.getFront() != null) && EndLocationFound) {
  //  while ((queue2.peek() != null) && (!EndLocationFound)) {

      //System.out.println("The neighbor:");
      //neighbor.streamOut();
      //queue2.add(neighbor);
      //System.out.println(neighbor.isDone());
      //map1.put(currentloc.word,neighbor.word);

      //queue2.peek().streamOut();
  //  }




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
