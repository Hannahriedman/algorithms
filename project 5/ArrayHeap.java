import java.lang.Math;

class ArrayHeap {
  private Process[] data;
  private int[] heapAndFreeStack;

  private int numItems;
  private int capacity;


  private void doubleCapacity() {
    //System.out.println("old cap:"+capacity);
    int oldcap = this.capacity;
    this.capacity = this.capacity*2;
    //System.out.println("New cap:"+capacity);
    Process[] newData = new Process[this.capacity];
    int[] newHeap = new int[this.capacity];
    for (int i= 0; i < oldcap; i++) {
      if (this.data[i] != null) {
        newData[i] = this.data[i];
      }
      //System.out.println(newData[i].getId());
    }
    for (int j= 0; j < oldcap; j++) {
      if (this.data[j] != null) {
        newHeap[j] = this.heapAndFreeStack[j];
      }
      //newHeap[j] = this.heapAndFreeStack[j];
      //System.out.println(newHeap[j]);
    }
    this.data = newData;
    this.heapAndFreeStack = newHeap;


  }

  private void bubbleUp(int ndx) {
    if (ndx == 0) {
      return;
    }
    int parent = (int) Math.floor(ndx/2);
    if (heapAndFreeStack[ndx] < heapAndFreeStack[parent]) { // swap
      int temp = heapAndFreeStack[ndx];
      heapAndFreeStack[ndx] = heapAndFreeStack[parent];
      heapAndFreeStack[parent] = temp;
      bubbleUp(parent);
    }
  }

  private void bubbleDown(int ndx) {
    int child1 = (2*ndx)-1;
    int child2 = 2*ndx;
    if (child1 < numItems) {
      int lesserChild = child1;
      if ((child2 < numItems) &&
      (heapAndFreeStack[child2] < heapAndFreeStack[child1])){
        lesserChild = child2;
      }
      if (heapAndFreeStack[ndx] < lesserChild) { // swap
        int temp1 = heapAndFreeStack[ndx];
        heapAndFreeStack[ndx] = heapAndFreeStack[lesserChild];
        heapAndFreeStack[lesserChild] = temp1;
        bubbleDown(lesserChild);
      }
    }
  }


  public ArrayHeap() {
    this.numItems = 0;
    this.capacity = 1;
    this.data = new Process[capacity];
    this.heapAndFreeStack = new int[capacity];
  }
  public ArrayHeap(ArrayHeap h) {
    h.capacity = this.capacity;
    h.data = new Process[capacity];
    h.heapAndFreeStack = new int[capacity];
    for(int i=0;i<capacity;i++){
      h.data[i]= this.data[i];
    }
    for(int j=0;j<numItems;j++){
      h.heapAndFreeStack[j]= this.heapAndFreeStack[j];
    }
  }

  public void insert(Process item) {
    this.numItems++; // increase num of itmes
    if (numItems > capacity) {
      //System.out.println("doubling from"+capacity);
      doubleCapacity();
    }
    for(int i=0;i<capacity;i++){
      if (this.data[i]== null){
        this.data[i] = item;
        this.heapAndFreeStack[numItems-1] = i;
        bubbleUp(numItems-1);
      }
    }
  }

  public void removeMinItem() {
    data[heapAndFreeStack[0]] = null;
    heapAndFreeStack[0] = heapAndFreeStack[numItems];
    this.numItems--;
    bubbleDown(0);
  }

  public Process getMinItem() {
    return data[heapAndFreeStack[0]];
  }

  public int getNumItems() {
    return numItems;
  }

}
