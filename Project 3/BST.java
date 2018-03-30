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
    if (data == null) {
      return;
    }
    System.out.println(indent + data);
    if (this.left == null) {
      System.out.println("  NULL");
    } else {
      this.left.printPreorder("  ");
    }
    if (this.right == null) {
      System.out.println("  NULL");
    } else {
      this.right.printPreorder("  ");
    }
  }
  public void printPreorder(String indent) { //overload of method
    if (data == null) {
      System.out.println(indent+"  NULL");
    } else {
      System.out.println(indent + data);
    }
    if (this.left == null) {
      System.out.println(indent+"  NULL");
    } else {
      this.left.printPreorder(indent+"  ");
    }
    if (this.right == null) {
      System.out.println(indent+"  NULL");
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
    boolean flag = true;

    if (root == null) { // this is the root item
      BSTNode newNode = new BSTNode(item, null, null);
      root = newNode;
      flag = false;
    }
    BSTNode curNode = this.root;

    while (flag) {
      int compareItem =curNode.data.compareToIgnoreCase(item);
      //System.out.println(curNode.data+" compareto "+item+" ="+compareItem);
      if (compareItem == 0) {
        flag = false;
        return; // do nothing bc you can't add two of the same item
      }
      if (compareItem < 0) { // right

        if (curNode.right == null) { // insert item
          BSTNode newNode = new BSTNode(item, null, null);
          curNode.right = newNode;
          flag = false;
        } else {
          curNode = curNode.right;
        }

      } else if (compareItem > 0) { // left
        if (curNode.left == null) { // insert item
          BSTNode newNode = new BSTNode(item, null, null);
          curNode.left = newNode;
          flag = false;
        } else {
          curNode = curNode.left;
        }
      }
    }
  }
  public void remove(String item) {
    boolean flag = true;
    BSTNode parent = null;
    BSTNode curNode = this.root;
    BSTNode grandchild = null;
    if (root == null) { // if there is nothing to remove
      return;
      // do nothing
    }

    while ((curNode != null)&& (flag)) {
      int compareItem =curNode.data.compareToIgnoreCase(item);
      if (compareItem < 0) { // right
        parent = curNode;
        curNode = curNode.right;
      } else if (compareItem > 0) { // left
        parent = curNode;
        curNode = curNode.left;
      } else if (compareItem == 0) {
        flag = false;
      }
    }
    if (flag) {
      return;
    }
    // removing leaf node
    if (curNode.left == null && curNode.right == null) {
      if (parent == null){
        root = null;
      } else if (parent.left == curNode) {
        parent.left = null;
      } else { //if (parent.right == curNode){
        parent.right = null;
      }
      curNode = null;
      return;
    }// TODO add return here and then make separete if statements
    // removing node with 1 child
    if ((curNode.left == null && curNode.right != null) ||
               (curNode.left != null && curNode.right == null)) {
      if (curNode.left != null) {
        grandchild = curNode.left;
        curNode.left = null;
      } else {
        grandchild = curNode.right;
        curNode.right = null;
      }

      if (parent == null) {
        root = grandchild;
      } else if (parent.left == curNode) {
        parent.left = grandchild;
      } else {
        parent.right = grandchild;
      }
      curNode = null;
      return;
      // TODO add return here and then make separete if statements
    // removing node with 2 children
    }
    if (curNode.left != null && curNode.right != null) {
      BSTNode leftmost = curNode.right;
      BSTNode leftmostParent = curNode;

      if (leftmost.left != null) {
        while (leftmost.left != null) {
          leftmostParent = leftmost;
          leftmost = leftmost.left;
        }
        leftmostParent.left = leftmost.right;
        leftmost.right = curNode.right;
      }
      leftmost.left = curNode.left;

      if (parent == null) {
        root = leftmost;
      } else if (parent.left == curNode) { // if the parent
        parent.left = leftmost;
      } else if (parent.right== curNode){
        parent.right = leftmost;
      }

      curNode.left = null;
      curNode.right = null;
      curNode = null;
      return;
    }
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
    if (root == null){
      path = "?";
      return path;
    }
    //System.out.println("String:"+item);
    while ((curNode != null)&& (!found)) {
      // TODO use compareTo instead of ignorecase to save time
      int compareItem = curNode.data.compareTo(item);
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
    if (root == null){
      item = "?";  // TODO make this one line?
      return item;
    }
    for (int i=0;i<path.length();i++) {
      direction = path.substring(i,i+1);
      if (direction.equals("0")) { // left
        curNode = curNode.left;
        if (curNode == null) {
          item = "?";
          break;
        }
        item = curNode.data;
      } else if (direction.equals("1")) { // right
        curNode = curNode.right;
        if (curNode == null) {
          item = "?";
          break;
        }
        item = curNode.data;
      } else if (direction.equals("r")) {
        item = root.data;
      }
    }
    return item;
  }
}
