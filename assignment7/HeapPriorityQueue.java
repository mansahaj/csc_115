/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeMin is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
public class HeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected T[] storage;
	protected int currentSize;
	
	// Feel free to change rootIndex to 0 if you want to 
	// use 0-based indexing (either option is fine)
	private static final int rootIndex = 1;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	@SuppressWarnings({"unchecked"})
	public HeapPriorityQueue(Class<T> dataType) {
		// Creating generics arrays in Java is not very clean. The following 
		// two lines allocate the generic array for you based on whether you
		// have selected to store the root at index 0 or 1 above.
		if (rootIndex == 0) {
			storage = (T[]) java.lang.reflect.Array.newInstance(dataType, DEFAULT_SIZE);
		} else {
			storage = (T[]) java.lang.reflect.Array.newInstance(dataType, DEFAULT_SIZE+1);
		}
		currentSize = 0;
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	@SuppressWarnings({"unchecked"})
	public HeapPriorityQueue(Class<T> clazz, int size) {
		// Creating generics arrays in Java is not very clean. The following 
		// two lines allocate the generic array for you based on whether you
		// have selected to store the root at index 0 or 1 above.
		if (rootIndex == 0) {
			storage = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
		} else {
			storage = (T[]) java.lang.reflect.Array.newInstance(clazz, size+1);
		}
		currentSize = 0;
	}

	public void insert (T element) throws HeapFullException {
		if(currentSize == 0){
			currentSize++;
			storage[currentSize] = element;
		}
		else if (isFull()){
			throw new HeapFullException();
		}
		else{
			currentSize++;
			storage[currentSize] = element;
			bubbleUp(currentSize);
		}

		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. (By default, the root
		// index is set to 1 for you above. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.		
    }
	
	private void bubbleUp(int index) {
		while(index>1 && storage[index/2].compareTo(storage[index])>0){
				swap(index, index/2);
				index = index/2;
		}

	}

	public void swap(int index_first, int index_second){
		T to_swap = storage[index_first];
		storage[index_first] = storage[index_second];
		storage[index_second] = to_swap;
	}
			
	public T removeMin() throws HeapEmptyException {
		if (isEmpty()) {
			throw new HeapEmptyException();
		}
	
		// Save the minimum element to be returned later
		T minElement = storage[1];
	
		// Move the last element to the root position
		swap(currentSize, 1);
		storage[currentSize] = null;
		currentSize--;
	
		// Ensure the min-heap property by moving the new root down
		bubbleDown(1);
		return minElement;
	}
	
	
	private void bubbleDown(int index) {
		// Defining the root
		int root_index = index;
	
		while (root_index <= currentSize) {
			int left_index = 2 * root_index;
			int right_index = (2 * root_index) + 1;

			if (left_index <= currentSize && storage[left_index].compareTo(storage[root_index]) < 0 
				&& right_index <= currentSize && storage[right_index].compareTo(storage[root_index]) < 0){
				if (storage[left_index].compareTo(storage[right_index])<0){
					swap(left_index, root_index);
					root_index = left_index;
				}
				else{
					swap(right_index, root_index);
					root_index = right_index;
				}
			}
			else if (left_index <= currentSize && storage[left_index].compareTo(storage[root_index]) < 0) {
				swap(left_index, root_index);
				root_index = left_index;
			}
			else if (right_index <= currentSize && storage[right_index].compareTo(storage[root_index]) < 0) {
				swap(right_index, root_index);
				root_index = right_index;
			}
			else{
				break;
			}		
		}
	}
	

	public boolean isEmpty(){
		return (currentSize == 0);
	}
	
	public boolean isFull() {
		return (currentSize >= storage.length-1);
	}
	
	public int size () {
		return currentSize;
	}
	
	// FOR DEBUGGING:
	public String toString() {
		String s = "";
		String sep = "";
		if (rootIndex == 0) {
			for (int i = 0; i < currentSize; i++) {
				s += sep + storage[i];
				sep = " ";				
			}
		} else if (rootIndex == 1) {
			for(int i=1; i<=currentSize; i++) {
				s += sep + storage[i];
				sep = " ";
			}
		}
		return s;
	}
}
