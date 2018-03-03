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
    BST newtree2 = new BST();
    EncryptionTree newtree = new EncryptionTree();
    String root = file.nextLine();
    newtree.root = new BSTNode(root,null,null);
    System.out.println("adding the root: "+root);
    newtree.insert((file.nextLine()));
    newtree.insert((file.nextLine()));
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
    System.out.println(newtree.decrypt(newtree.encrypt("and")));
  }
}
