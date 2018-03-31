/* CMPT 435
 * Project 3 -- Tree-based encryption and decryption
 * Filename: BST.java
 * Student name: Hannah Riedman
 * 3-30-18
 * includes BSTNode, BST, and EncryptionTree class
 */

import java.util.Scanner;
/**
 * BSTNode class
 * In the BSTNode class there are 3 perameters(data, left, right) for every
 * Binary Search Tree Node. We also have functions getleft(), getRight(),
 * getData(), printPreorder(), minNode(), maxNode(), and verifySearchOrder().
 * This class will be used in the BST class.
 */
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
  /**
   * printPreorder method
   * This method, will recursivly print the Binary Search Tree and print NULL
   * for every node that is NULL. There are two methods, one is overloaded with
   * a parameter so we can print out the tree with correct indentation.
   */
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
    BSTNode node = this;
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public BSTNode maxNode() {
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
/**
 * BST Class
 * THe BST Class holds only one parameter, the root (a BSTNode), and functions
 * isEqual, insert(item), and remove(item).
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }

  protected BSTNode root;

  public BST() {
    root = null;
  }
  /**
   * insert method
   * @param item is the item you want to add to the BST.
   * The method will first ask if the root is empty so then we know we are
   * adding a root item. If not, we will loop through comparing the item
   * passed in, to the nodes of the tree. If we find a node that is equal to
   * the item then we return becuase you can't have two of the same item.
   * Otherwise we split the tree in half every loop to search for the right
   * place to insert it.
   */
  public void insert(String item) {
    boolean flag = true;

    if (root == null) { // this is the root item
      BSTNode newNode = new BSTNode(item, null, null);
      root = newNode;
      flag = false;
    }
    BSTNode curNode = this.root;

    while (flag) {
      int compareItem =curNode.data.compareTo(item);
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
  /**
   * remove method
   * @param item is the item you want to remove from the BST.
   * This method will remove in 4 different ways. If the the item is not in
   * the tree (root is null or no item match), then it will do nothing.
   * Once we have found the item in the BST, we will remove differently if it is
   * a leaf node, a node with 1 child, or a node with 2 children.
   */
  public void remove(String item) {
    boolean notFound = true;
    BSTNode parent = null;
    BSTNode curNode = this.root;
    BSTNode grandchild = null;
    if (root == null) { // if there is nothing to remove
      return; // do nothing
    }
    // search for the node
    while ((curNode != null)&& (notFound)) {
      int compareItem =curNode.data.compareTo(item);
      if (compareItem < 0) { // right
        parent = curNode;
        curNode = curNode.right;
      } else if (compareItem > 0) { // left
        parent = curNode;
        curNode = curNode.left;
      } else if (compareItem == 0) {
        notFound = false;
      }
    }
    if (notFound) { // if the item is not in tree.
      return;
    }
    // removing leaf node
    if (curNode.left == null && curNode.right == null) {
      if (parent == null){
        root = null;
      } else if (parent.left == curNode) {
        parent.left = null;
      } else {
        parent.right = null;
      }
      curNode = null;
      return;
    }
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
    }
    // removing node with 2 children
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
/**
 * EncryptionTree Class
 * This class is an extension of the BST class, and it also has more functions,
 * encrypt(item), and decrypt(path). These two functions decode/encode strings
 * using the elments in the Binary Search Tree.
 */
class EncryptionTree extends BST {
  public EncryptionTree() {}
  /**
   * encrypt method
   * @param  item the item you want to encypt.
   * @return path, the path that you will take to get to that item. If item
   * is not in BST then '?' will be the path.
   * This method will take in an item and search and record the path from the
   * root to that item.
   */
  public String encrypt(String item) {
    BSTNode curNode = root;
    boolean found = false;
    String path = "r";
    if (root == null){
      path = "?";
      return path;
    }
    while ((curNode != null)&& (!found)) {
      int compareItem = curNode.data.compareTo(item);
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
  /**
   * decrypt method
   * @param  path the path is the path to an item in the tree.
   * @return item, that is found from the path. If the path leads to no item, a
   * '?' is returned.
   * The method will split the tree in half every loop, following the path, '1'
   * is right and '0' is left. 
   */
  public String decrypt(String path) {
    BSTNode curNode = root;
    String item = "?";
    String direction = "2";
    if (root == null){
      item = "?";
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
