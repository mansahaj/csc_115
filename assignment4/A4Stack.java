public class A4Stack<T> implements Stack<T> {
	private A4Node<T> head;

	public A4Stack() {
		head = null;
	}

	public void push(T value){
		A4Node<T> n = new A4Node<T>(value);

		if(head == null){
			head = n;
		}
		else{
			n.next = head;
			head = n;
		}
		
	}

	public T pop(){
		if (head == null){
			return null;
		}
		T val = head.getData();
		head = head.next;
		return val;
	}

	public boolean isEmpty(){
		return head == null;
	}
	
	public T top(){
		if(head == null){
			return null;
		}
		return head.getData();
	}

	public void popAll(){
		head = null;
	}
}