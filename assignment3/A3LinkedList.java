// Name: Mansahaj Singh Popli
// Student number: v00996968

public class A3LinkedList implements A3List {
	private A3Node head;
	private A3Node tail;
	private int length;
	
	public A3LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void addFront(String s) {
		A3Node n = new A3Node(s);
		if (head == null){
			tail = n;
		}
		else{
			n.next = head;
			head.prev = n;
			n.prev = null;
		}
		head = n;
		length ++;

	}

	public void addBack(String s) {
		A3Node n = new A3Node(s);
		if (head == null){
			head = n;
		}
		else{
			tail.next = n;
			n.prev = tail;
		}
		tail = n;
		length++;
	}
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	public void removeFront() {
		if (head == null || head.next == null){
			head = null;
			tail = null;
		}
		else{
			head = head.next;
			head.prev = null;
		}

		length--;
	}
	
	public void removeBack() {
		if (head == null || head.next == null){
			head = null;
			tail = null;
		}
		else{
			tail = tail.prev;
			tail.next = null;
		}
		length --;
	}
	
	public void insertInto(String s) {
		A3Node n = new A3Node(s);
		if (head == null){
			head = n;
			tail = n;
		}
		A3Node cur = head;
		while(cur.getData().compareTo(s)<0 && cur!=tail){
			cur = cur.next;	
		}
		if(cur == tail && cur.getData().compareTo(s)<0){
			addBack(s);
		}
		else{
		cur = cur.prev;
		n.next = cur.next;
		n.prev = cur;
		cur.next.prev = n;
		cur.next = n;
		length ++;
		}
	}

	public void rotate(int n) {
		A3Node end = tail;
		int i = 0;
		while(i<n){

		end = tail;
		tail.prev.next = null;
		tail = tail.prev;

		end.next = head;
		head.prev = end;
		end.prev = null;
		head = end;
		
		i++;
		}
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from front to back
	 * Parameters: none
	 * Returns: nothing
	 *
	 * USED TO HELP YOU WITH DEBUGGING
	 * DO NOT CHANGE THIS METHOD
	 */
	public String frontToBack() {
		String result = "{";
		A3Node cur = head;
		while (cur != null) {
			result += cur.getData();
			cur = cur.next;
		}
		result += "}";
		return result;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from back to front
	 * Parameters: none
	 * Returns: nothing
	 *
	 * USED TO HELP YOU WITH DEBUGGING
	 * DO NOT CHANGE THIS METHOD
	 */
	public String backToFront() {
		String result = "{";
		A3Node cur = tail;
		while (cur != null) {
			result += cur.getData();
			cur = cur.prev;
		}
		result += "}";
		return result;
	}
}
	