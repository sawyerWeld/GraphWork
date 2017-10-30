/**
 *  Java Program to Implement Binary Heap
 */
 
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
 
/** Class BinaryHeap **/
class BinaryHeap    
{
    /** The number of children each node has **/
    private static final int d = 2;
    private int heapSize;
    private Node[] heap;
 
    /** Constructor **/    
    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new Node[capacity + 1];
        Arrays.fill(heap, null);
    }
 
    public int getCapacity() {
    	return heap.length - 1;
    }
    
    /** Function to check if heap is empty **/
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }
 
    /** Check if heap is full **/
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }
 
    /** Clear heap */
    public void makeEmpty( )
    {
        heapSize = 0;
    }
 
    /** Function to  get index parent of i **/
    private int parent(int i) 
    {
        return (i - 1)/d;
    }
 
    /** Function to get index of k th child of i **/
    private int kthChild(int i, int k) 
    {
        return d * i + k;
    }
 
    /** Function to insert element */
    public void insert(Node x)
    {
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        /** Percolate up **/
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }
 
    /** Function to find least element **/
    public Node findMin( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");           
        return heap[0];
    }
 
    /** Function to delete min element **/
    public Node deleteMin()
    {
        Node keyItem = heap[0];
        delete(0);
        return keyItem;
    }
 
    /** Function to delete element at an index **/
    public Node delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        Node keyItem = heap[ind];
        //heap[ind] = heap[heapSize - 1];
        heap[ind] = null;
        heapSize--;
        heapifyDown(ind);        
        return keyItem;
    }
    
    public void deleteByName(Node in) {
    	for (int i = 0; i < heap.length-1; i++) {
    		if (heap[i].equals(in)) {
    			delete(i);
    			return;
    		}
    	}
    }
 
    public boolean contains(Node in) {
    	for (int i = 0; i < heap.length; i++) {
    		if (heap[i].equals(in)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public Node get(int ind) {
    	if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        Node keyItem = heap[ind];        
        return keyItem;
    }
    
    /** Function heapifyUp  **/
    private void heapifyUp(int childInd)
    {
        Node tmp = heap[childInd];    
        while (childInd > 0 && tmp.compareTo(heap[parent(childInd)])==-1)
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
 
    /** Function heapifyDown **/
    private void heapifyDown(int ind)
    {
        int child;
        Node tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child].compareTo(tmp)==-1)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }
 
    /** Function to get smallest child **/
    private int minChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) 
        {
            if (heap[pos].compareTo(heap[bestChild])==-1) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
 
    public Node[] getArray() {
    	return heap;
    }
    
    /** Function to print heap **/
    public void printHeap()
    {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] +" ");
        System.out.println();
    }     
}
 