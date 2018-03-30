/* CMPT 435
 * Project 3 -- Tree-based encryption and decryption
 * Filename: Driver_prj3.java
 * Student name: Hannah Riedman
 * 3-1-18
 * Tree-based encryption and decryption Driver.
 */
import java.util.Scanner;

public class Driver_prj3 {

  public static void main(String[] args) {
    Scanner file = new Scanner(System.in);

    EncryptionTree newtree = new EncryptionTree();
    String command = "";
    String content = "";
    String[] content1;
    String result = "";
    String[] splitContents;
    int space = 0;
    Boolean flag = false;
    while (file.hasNext()){
      String currentLine = file.nextLine();
      command = currentLine.substring(0,1);
      if (flag){ // if user has invoked quit
        break;
      }

      switch (command) {
        case "i": // insert word
          content = currentLine.substring(2); //.trim();
          newtree.insert(content);
          break;
        case "r": // remove word
          content = currentLine.substring(2);
          newtree.remove(content);
          break;
        case "e": // encrypt message
         // TODO fix encrypt for the first word is becoming question mark
          content1 = currentLine.split("'");
          splitContents = content1[1].split(" ");
          for (int i=0;i < splitContents.length; i++) {
            //System.out.println("word:"+splitContents[i]);
            String word = splitContents[i];
            if (!word.equals(" ")) {
              if (i == splitContents.length-1) { // if its the last case it wont
                result = result+newtree.encrypt(word); // add a space at end to
              } else {  // increase efficency and make it easier
                result = result+newtree.encrypt(word)+" ";
              }
            }
          }
          System.out.println(result);
          result = "";
          break;
        case "d": // decrypt message
          content1 = currentLine.split("'");
          splitContents = content1[1].split(" ");
          for (int i=0;i < splitContents.length; i++) {
            //System.out.println("word:"+splitContents[i]);
            String code = splitContents[i];
            if (!code.equals(" ")) {
              if (i == splitContents.length-1) { // if its the last case it wont
                result = result+newtree.decrypt(code); // add a space at end to
              } else {  // increase efficency and make it easier
                result = result+newtree.decrypt(code)+" ";
              }
            }
          }
          System.out.println(result);
          result = "";
          break;
        case "p": // print the codebook
          newtree.printPreorder();
          break;
        case "q": // quit the program
          flag = true;
          break;
        default:
          break;
      }
    }
  }
}
