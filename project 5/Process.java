import java.util.Scanner;

class Process {
  private int id;
  private int deadline;
  private int requiredTime;
  private String information;

  public Process(int theId) {
    this.id = theId;
    this.deadline = 0;
    this.requiredTime = 0;
    this.information = "";
  }

  public int run(int currentSystemTime) {
    if(canComplete(currentSystemTime)){
      //currentSystemTime = currentSystemTime;
      System.out.println("running process id "+id+" at "+currentSystemTime);
      System.out.println(information);
      //System.out.println(requiredTime);
      currentSystemTime = currentSystemTime+requiredTime;
      //System.out.println(currentSystemTime);
    } else {

      System.out.println("skipping process id "+id+" at "+currentSystemTime);
      currentSystemTime = currentSystemTime+1;
    }
    return currentSystemTime;
  }

  public boolean canComplete(int currentSystemTime) {
    int timeToComplete = currentSystemTime+requiredTime;
    if (timeToComplete <= deadline) {
      return true;
    } else {
      return false;
    }
  }

  public int getId() {
    return this.id;
  }


  public boolean isLess(Process p) {
    if (deadline < p.deadline) {
      return true;
    } else if (deadline == p.deadline) {
      if (requiredTime < p.requiredTime){
        return true;
      } else if (requiredTime == p.requiredTime){
        if (requiredTime < p.requiredTime) {
          return true;
        }
      }
    }
    return false;
  }


  public void streamIn(Scanner input) {
    String l = input.nextLine();

    if (l.equals("")){
      //System.out.println("Another line");
      l = input.nextLine();
    }
    //System.out.println("line:"+l);
    String[] line = l.split(" ",4);
    //this.requiredTime = Integer.valueOf(line[0]);
    this.deadline = Integer.valueOf(line[1]);
    this.requiredTime = Integer.valueOf(line[2]);
    this.information = line[3];
    //System.out.println(requiredTime);
    //System.out.println(deadline);
    //System.out.println(information);

  }
}
