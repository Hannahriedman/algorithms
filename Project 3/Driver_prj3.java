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
    //BST newtree2 = new BST();
    EncryptionTree newtree = new EncryptionTree();
    //String root = file.nextLine();
    //newtree.root = new BSTNode(root,null,null);
    //System.out.println("adding the root: "+root);
    //newtree.insert((file.nextLine()));
    //newtree.printPreorder();
    //String currentLine = file.nextLine();
    String command = "";
    String content = "";
    String result = "";
    Boolean flag = false;
    while (file.hasNext()){
      String currentLine = file.nextLine();
      //System.out.println(currentLine);
      command = currentLine.substring(0,1);
      if (flag){ // if user has invoked quit
        break;
      }
      //System.out.println("Command:"+command);
      //System.out.println("Content:"+content);
      switch (command) {
        case "i": // insert word
          content = currentLine.substring(2);
          newtree.insert(content);
          break;
        case "r": // remove word
          content = currentLine.substring(2);
          newtree.remove(content);
          break;
        case "e": // encrypt message
          content = currentLine.substring(3);
          while (!content.isEmpty()) {
            int space = content.indexOf(' ');
            //System.out.println("index:"+space);
            if (space == -1){ // reached end of string
              space = content.indexOf("'"); // must now use the end ' as index
            }
            String word = content.substring(0,space);
            //System.out.println("word:"+word);
            result = result+newtree.encrypt(word)+" ";
            content = content.substring(space+1);
          }
          System.out.println(result.trim());
          result = "";
          break;
        case "d": // decrypt message
          content = currentLine.substring(3);
          //System.out.println(content);
          while (!content.isEmpty()) {
            int space = content.indexOf(' ');
            if (space == -1){ // reached end of string
              space = content.indexOf("'"); // must now use the end ' as index
            }
            String code = content.substring(0,space);
            result = result+newtree.decrypt(code)+" ";
            //System.out.println(result);
            content = content.substring(space+1);
          }
          System.out.println(result.trim());
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
    /**newtree.insert((file.nextLine()));
    newtree.insert((file.nextLine()));
    newtree.insert((file.nextLine()));
    newtree.insert((file.nextLine()));
    newtree.verifySearchOrder();
    newtree.printPreorder();
    System.out.println(newtree.encrypt("years"));
    System.out.println(newtree.encrypt("seven"));
    System.out.println(newtree.encrypt("four"));
    System.out.println(newtree.encrypt("score"));
    System.out.println(newtree.encrypt("ago"));
    System.out.println(newtree.encrypt("and"));
    System.out.println(newtree.encrypt("hannah"));
    System.out.println(newtree.decrypt(newtree.encrypt("years")));
    System.out.println(newtree.decrypt(newtree.encrypt("seven")));
    System.out.println(newtree.decrypt(newtree.encrypt("four")));
    System.out.println(newtree.decrypt(newtree.encrypt("score")));
    System.out.println(newtree.decrypt(newtree.encrypt("ago")));
    System.out.println(newtree.decrypt(newtree.encrypt("and")));*/
  }
}
