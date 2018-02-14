/* CMPT 435
 * Project 2 - Shortest-path word-melt solver
 * Filename: ArrayQueue.java
 * Student name: Hannah Riedman
 *
 * ArrayQueue class for the Word-Melt Solver.
 * Modifed code from Professor Rivas.
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;

  private void doubleCapacity() {
    // when you try to add something and its full evoke this
    // copy items in the way they were thought of
    System.out.println("hello");
    ArrayQueue doubleCap = new ArrayQueue();

  }

  ArrayQueue() {
    this.data = (Location[]) new Location[1];
    this.length = 0;
    this.capacity = 1;
    this.front = 0;
  }
  ArrayQueue(ArrayQueue q) {
    // -
  }
  /**
   * add method
   * This method takes in a location to add to the queue and first checks to
   * see if it is going to stay within the capacity and if not, it will double
   * capacity. Then it asks if the length is 0 because if so, it can add
   * an element to the front, otherwise we need to increase the length and add
   * an element.
   * @param Location loc is the location you are adding to the queue.
   */
  void add(Location loc) {
    // adds location and then moves front if front isn't 0
    System.out.println("The loc to add: ");
    loc.streamOut();
    System.out.println("front:"+front+"length:"+length+"capacity"+capacity);
    //length++ // you need to increase the length
    // if the length is greater then capacity you need to doubleCapacity

    if (length >= capacity) {
      doubleCapacity();
    }
    if (!(length >= capacity)) {
      if (length == 0) { // there is nothing in the queue
        data[front] = loc;
        length++;
      } else { //
        data[front+length] = loc;
        length++;
      }
    }
    System.out.println("front:"+front+" length:"+length+" capacity"+capacity);
  }

  /**
   * remove method
   * This method removes the head of the queue and and resets any
   * varibles that change when something is removed.
   */
  void remove() {
    data[front] = null; // remove the current head
    front ++;
    length--;
  }
  /**
   * getFront method
   * This method retrives the location thats in the front of queue.
   * @return location thats in the front.
   */
  Location getFront() {
    return data[front];
  }
  /**
   * getLength method
   * This method retrives the length of the queue.
   * @return int length of the length of queue.
   */
  int getLength()  {
    return length;
  }

  ArrayQueue copyFrom(ArrayQueue q) {
  // -
    // make sure the queue is empty before we copy
    if (this == q) {
      return this;
    } else {
      this.capacity = q.capacity;
      this.length = q.length;
      this.front = this.front;
      this.data = null;
    }
  ///Location[] copyQueue = (Location[]) new Location[2*capacity];

		//copy items
		for(int i = front; i <= (length-front); i ++) {
			this.data[i-front] = q.data[i%capacity];
    }
		//data = copyQueue;
		front = 0;
		//back = front-1;
		capacity *= 2;
    return this;
  }
}
