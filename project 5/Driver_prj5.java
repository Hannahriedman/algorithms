/* CMPT 435
 * Project 5 -- Real-time batch operating system simulator
 * Filename: Driver_prj5.java
 * Student name: Hannah Riedman
 * 4-11-18
 * Driver for Real-time batch operating system simulator
 */
import java.util.Scanner;

public class Driver_prj5 {

  public static void main(String[] args) {
    Scanner file = new Scanner(System.in);
    ArrayHeap heap = new ArrayHeap();
    int systemClock = 0;
    int nextClock = 0;
    int numOfProcesses = file.nextInt();
    int numOfPSkipped = 0;
    int numOfPRun = 0;

    //file.nextLine();
    //System.out.println("Number of Processes:"+numOfProcesses);
    for (int i=0;i < numOfProcesses;i++) {
      nextClock = file.nextInt();
      if (nextClock > systemClock) {
        systemClock = nextClock;
      }
      Process curProcess = new Process(i);
      curProcess.streamIn(file);
      heap.insert(curProcess);
      if (curProcess.canComplete(systemClock)) {
        numOfPRun++;

      } else {
        numOfPSkipped++;
      }
      systemClock = curProcess.run(systemClock);
    }
    System.out.println("final clock is                 "+systemClock);
    System.out.println("number of processes run is     "+numOfPRun);
    System.out.println("number of processes skipped is "+numOfPSkipped);
  }
}
