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
import java.util.Stack;

public class Driver_prj2 {

  public static void main(String[] args) {
    Scanner file = new Scanner(System.in);

    String valid = "";
    String end = "";
    boolean flag = true;
    Boolean EndLocationFound = false;
    // initalize start location, Maze, ArrayQueue, and TreeMap
    Maze newMaze = new Maze();
    Location start = new Location();
    ArrayQueue queue1 = new ArrayQueue();
    Stack<String> path = new Stack<String>();
    //my ArrayQueue doesn't work right now so temporaily using ArrayDeque.
    ArrayDeque<Location> queue2 = new ArrayDeque<Location>();
    TreeMap<String,String> map1 = new TreeMap<String,String>();

    // stream in the file
    newMaze.streamIn(file);

    start = newMaze.getStartLocation(); // start location
    start.start(); // inialize the first iterationMode

    // add to queue and map
    queue2.add(start);
    map1.put(start.word,start.word);

    if (newMaze.isEndLocation(start)) {
      EndLocationFound = true;
    }
    while (flag) {
      if (EndLocationFound) { // if we found the location,there is a solution
        flag =false;
        valid = "valid";
      } else if (queue2.isEmpty()) { // if queue is empty then no solution
        flag=false;
        valid = "invalid";
      } else { // else, keep searching for a next word.
        Location currentloc = new Location();
        currentloc.start();
        currentloc = queue2.peek();
        // reset iteration, letter, and indextochange
        currentloc.iterationMode = 0;
        currentloc.nextLetter = 'a';
        currentloc.indexToChange = 0;

        Location neighbor = new Location();
        neighbor.start();
        do {
          neighbor = currentloc.nextNeighbor();
          if (newMaze.isValidLocation(neighbor)) {
            if (map1.get(neighbor.word) == null) {
              // if the map doesn't have a value for a key of the
              // neighbor then we have not placed this on the map.
              queue2.add(neighbor);
              map1.put(neighbor.word,currentloc.word);
            }
          }
          if (newMaze.isEndLocation(neighbor)){
            EndLocationFound = true;
            end = neighbor.word;
          }
        } while (!neighbor.isDone());
        queue2.remove();
      }
    }
    // if statement to print valid and invlaid messages
    if (valid == "valid"){
      System.out.println("Solution found:");
      // print out map to stack
      if (newMaze.isEndLocation(start)) {
        path.push(start.word);
      } else {
        String key = "key";
        key = end;
        while (!key.equals(map1.get(key))) {
          String value = map1.get(key);
          path.push(key);
          key = value;
        }
        path.push(map1.get(key));
      }

      // print path in correct order
      while (!path.isEmpty()) {
        System.out.println(path.pop());
      }
    } else {
      System.out.println("No solution found");
    }
  }
}
