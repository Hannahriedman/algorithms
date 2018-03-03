/* CMPT 435
 * Project 3 -- Tree-based encryption and decryption
 * Filename: BST.java
 * Student name: Hannah Riedman
 * 3-1-18
 * includes BSTNode, BST, and EncryptionTree class
 */

import java.util.Scanner;

class BSTNode {
  protected  BSTNode(BSTNode t) { assert(false); }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; left = l; right = r;
  }

  public BSTNode getLeft()  { return left;  }
  public BSTNode getRight()  { return right; }
  public String getData()    { return data;  }

  public void printPreorder() {
    String indent = "";
    System.out.println(indent + data);
    if (this.left == null) {
      System.out.println("  null");
    } else {
      this.left.printPreorder("  ");
    }
    if (this.right == null) {
      System.out.println("  null");
    } else {
      this.right.printPreorder("  ");
    }
  }
  public void printPreorder(String indent) { //overload of method
    if (data == null) {
      System.out.println(indent+"null");
    } else {
      System.out.println(indent + data);
    }
    if (this.left == null) {
      System.out.println(indent+"null");
    } else {
      this.left.printPreorder(indent+"  ");
    }
    if (this.right == null) {
      System.out.println(indent+"null");
    } else {
      this.right.printPreorder(indent+"  ");
    }

  }

  public BSTNode minNode() {
    //assert(root != null);
    BSTNode node = this;
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public BSTNode maxNode() {
    //assert(root != null);
    BSTNode node = this;
    while (node.right != null) {
      node = node.right;
    }
    return node;
  }

  /* professor's implementation of verifySearchOrder(); don't change it */
  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }
}

class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }

  protected BSTNode root;

  public BST() {
    root = null;
  }
  // compareto returns a negitive if the string being compared is greater
  public void insert(String item) {
    BSTNode curNode = this.root;
    boolean flag = true;
    while (flag) {
      int compareItem =curNode.data.compareToIgnoreCase(item);
      System.out.println(curNode.data+" compareto "+item+" ="+compareItem);
      if (compareItem < 0) { // right

        if (curNode.right == null) { // insert item
          BSTNode newNode = new BSTNode(item, null, null);
          curNode.right = newNode;
          System.out.println("Adding "+newNode.data);
          flag = false;
        } else {
          curNode = curNode.right;
        }
        //System.out.print(curNode.data);
      } else if (compareItem > 0) { // left
        if (curNode.left == null) { // insert item
          BSTNode newNode = new BSTNode(item, null, null);
          curNode.left = newNode;
          System.out.println("Adding "+newNode.data);
          flag = false;
        } else {
          curNode = curNode.left;
        }
      }
    }
  }
  public void remove(String item) {
    /**BSTNode<T> parent = root;
    // removing a leaf nodes
    if (parent.left == item) {
      parent.left = null;
    } else {
      parent.right = null;
    }
    item = null;


    // removing ndoe with 1 child
    if (item.left != null) {
      grandchild = item.left;
      item.left = null;
    } else {
      grandchild = item.right;
      item.right = null;
    }
    if (parent.left == item) {
      parent.left = grandchild;
    } else {
      parent.right = grandchild;
    }
    item = null;

    // removing node with 2 children
    BSTNode leftmost = item.right;
    leftmost.parent = item;
    if (leftmost.left != null) {
      while (leftmost.left != null) {
        leftmost.parent = leftmost;
        leftmost = leftmost.left;
      }
      leftmost.left = item.left;
      leftmost.right = item.right;
    }
    leftmost.left = item.left;
    // parent is null? - means its a root
    if (parent.left == item) {
      parent.left = leftmost;
    } else {
      parent.right = leftmost;
    }
    item.left = null;
    item.right = null;
    item = null;*/
  }

  public void printPreorder() { if (root != null) root.printPreorder(); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }

}

class EncryptionTree extends BST {
  public EncryptionTree() {}

  public String encrypt(String item) {
    BSTNode curNode = root;
    boolean found = false;
    String path = "r";

    while ((curNode != null)&& (!found)) {
      int compareItem = curNode.data.compareToIgnoreCase(item);
      //System.out.println(curNode.data+" compareto "+item+" ="+compareItem);
      if (compareItem == 0) {
        found = true;
      } else if (compareItem > 0) { // left path
        curNode = curNode.left;
        path = path+"0";
      } else if (compareItem < 0) { // right path
        curNode = curNode.right;
        path = path+"1";
      }
    }
    if (!found) {
      path = "?";
    }
    return path;
  }
  
  public String decrypt(String path) {
    BSTNode curNode = root;
    String item = "?";
    String direction = "2";

    for (int i=0;i<path.length();i++) {
      direction = path.substring(i,i+1);
      System.out.println("Direction:"+direction);
      //System.out.println(direction.equals("0"));
      //System.out.println(direction.equals("1"));
      if (direction.equals("0")) { // left
        curNode = curNode.left;
        item = curNode.data;
      } else if (direction.equals("1")) { // right
        curNode = curNode.right;
        item = curNode.data;
      } else if (direction.equals("r")) {
        item = root.data;
      }
    }

    return item;
  }
}
