/* CMPT 435
 * Project 3 -- Tree-based encryption and decryption
 * Filename: Driver_prj3.java
 * Student name: Hannah Riedman
 * 3-30-18
 * Tree-based encryption and decryption Driver.
 */
import java.util.Scanner;

public class Driver_prj3 {
  public static void main(String[] args) {
    Scanner file = new Scanner(System.in);

    EncryptionTree newtree = new EncryptionTree();
    String command = ""; // holds the first letter or "command" on each line
    String[] content; // holds the content of a line
    String result = ""; // holds the results of encypt/decrypt
    String[] splitContents; // holds the messages that need to be encypt/decrypt
    Boolean quit = false; // if the user wants to quit
    while (file.hasNext()){
      String currentLine = file.nextLine();
      command = currentLine.substring(0,1);

      if (quit){ // if user has invoked quit
        break;
      }

      switch (command) {
        case "i": // insert word
          newtree.insert(currentLine.substring(2));
          break;
        case "r": // remove word
          newtree.remove(currentLine.substring(2));
          break;
        case "e": // encrypt message
          content = currentLine.split("'");
          splitContents = content[1].split(" ");
          // for each item, encypt it and add to result.
          for (int i=0;i < splitContents.length; i++) {
            String word = splitContents[i];
            if (!word.equals(" ")) {
              // if we have reached the last case then we don't need an extra ""
              if (i == splitContents.length-1) {
                result = result+newtree.encrypt(word);
              } else {
                result = result+newtree.encrypt(word)+" ";
              }
            }
          }
          System.out.println(result);
          result = ""; // reset the result string to empty
          break;
        case "d": // decrypt message
          content = currentLine.split("'");
          splitContents = content[1].split(" ");
          // for each item, decrypt it and add to result.
          for (int i=0;i < splitContents.length; i++) {
            String code = splitContents[i];
            if (!code.equals(" ")) {
              // if we have reached the last case then we don't need an extra ""
              if (i == splitContents.length-1) {
                result = result+newtree.decrypt(code);
              } else {
                result = result+newtree.decrypt(code)+" ";
              }
            }
          }
          System.out.println(result);
          result = ""; // reset the result string to empty
          break;
        case "p": // print the codebook
          newtree.printPreorder();
          break;
        case "q": // quit the program
          quit = true;
          break;
        default:
          break;
      }
    }
  }
}
