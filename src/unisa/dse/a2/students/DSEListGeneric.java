package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head;
	private NodeGeneric<T> tail;

	private int size;
	
	public DSEListGeneric() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
		this.head = head_;
		//tail is the head initially.
		this.tail = head;
		
		int size = 0;
		
		NodeGeneric<T> currentNode =head;
		while (currentNode != null) {
			size++;
			if (currentNode.next!= null) {
				this.tail = currentNode.next;
			}
			currentNode = currentNode.next;
		}
		this.size = size;
		
		//this.tail = tail;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		// Copy constructor. 
		this.head = other.head;
		this.tail = other.tail;
		this.size = other.size;
		
	}

	//Remove the object at the specified index from the list, if it exists. 
	public T remove(int index) {
		//if the specified index is out of range.
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// Starting at the head of the list
		NodeGeneric<T> currentNode = head;
		int currentIndex = 0; //set the Index
		
		// Iterate through the list until the desired index is reached.
		while (currentNode!= null) {
			if (currentIndex == index) {
				T removeString = currentNode.get();
				
				// Checking if there is a node before the current one
				if (currentNode.prev != null) {
					//if currentNode.prev exists, its next will be currentNode.next
					currentNode.prev.next = currentNode.next;	
				} 
				// If currentNode.prev is null, removing the head of the list.
				else {
					head = currentNode.next;
				}
				// Checking if there is a node after the current one
				if (currentNode.next != null) {
					//if currentNode.next exists, its prev will be currentNode.prev.
					currentNode.next.prev = currentNode.prev;
				}
				else {
					//current tail will delete so currentNode.prev will be a tail.
					tail = currentNode.prev;
				}
				size --;
				return removeString;
			}
			currentIndex++;
			currentNode = currentNode.next;
			}
		return null;
	}
		
		
	//returns the index of the String parameter 
	public int indexOf(T node) {
		NodeGeneric<T> currentNode = head;
		int index = 0;
		while (currentNode != null) {
			if (currentNode.get().equals(node)) {
				//The index of the specified node
				return index;
			}
			currentNode = currentNode.next;
			index++;
		}
		// -1 if the object does not exist
		return -1;
	}
	
	//returns item at parameter's index
	public T get(int index) {
		if (index < 0|| index>= size) {
			return null;
		}
		int i = 0;
		NodeGeneric<T> currentNode = head;
		
		while( i < index) {
			currentNode = currentNode.next;
			i++;
		}

		
		return currentNode.get();
		
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string
	@Override
	public String toString() {
		StringBuilder sBuilder =new StringBuilder();
		
		NodeGeneric<T> current = head;
		while (current!= null) {
			sBuilder.append(current.get()).append(" ");
			current = current.next;	
		}
		return sBuilder.toString().trim();
	}

	
	public boolean add(T obj){
		if (obj == null) {
			throw new NullPointerException("Specified object is null");
		}
		NodeGeneric<T> newNode = new NodeGeneric<T>(null, null, obj);
		//Appends the specified element to the end of this list.
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
		return true;
		//@throws NullPointerException if the specified object is null
		
	}


	public boolean add(int index, T obj) {
	
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (obj == null) {
			throw new NullPointerException("The specified object is null");
		}
		
		
		NodeGeneric<T> newNode = new NodeGeneric<T>(null, null, obj);
		
		
		//when index is the tail.
		if (index == size) {
			return add(obj);
			
		}
		//if index == 0 and it's null, head and tail become newNode.
		else if (index == 0) {
			newNode.next = head;
	        if (head != null) { 
	            head.prev = newNode;
	        } else { 
	            tail = newNode;
	        }
	        head = newNode; 
	        
	    } 
		
		else {
			int i = 0;
			NodeGeneric<T> tempNode = head;
			//identify the position of index
			while (i < index) {
				tempNode = tempNode.next;
				i++;
		
			}
			//once the desired position is reached, insert the new node
			newNode.next = tempNode;// the new node's 'next' pointer points to the current (tempNode) node.
			newNode.prev = tempNode.prev;// the new node's 'prev' pointer points to the node before the current node (tempNode).
			tempNode.prev.next = newNode;// the previous node's 'next' pointer now points to the new node.
			tempNode.prev = newNode;// the current node's 'prev' pointer now points to the new node.
		}
		size++;
		return true;
	}


	public boolean contains(T obj) {
	
		if (obj == null) {
			throw new NullPointerException();
		}
		NodeGeneric<T> currentNode = head;
		int index = 0;
		while (currentNode!= null) {
			if(currentNode.get().equals(obj)) {
				return true;
			}
			currentNode = currentNode.next;
			index++;		
		}
		return false;
	}

	
	public boolean remove(T obj){
		if (obj == null) {
			throw new NullPointerException();
		}
		
		NodeGeneric<T> currentNode = head;
		while (currentNode!= null) {
			if (currentNode.get().equals(obj)) {
				if (currentNode.prev != null) {
					currentNode.prev.next = currentNode.next;
				} else {
					head = currentNode.next;
				}
				
				if (currentNode.next != null) {
					currentNode.next.prev = currentNode.prev;
				}
				else {
					tail = currentNode.prev;
				}
				size --;
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	
	@Override
	public int hashCode() {
	    int hash = 7;
	    NodeGeneric<T> currentNode = head;
	    while(currentNode != null){
	        hash = 31 * hash + (currentNode.get() == null ? 0 : currentNode.get().hashCode());
	        currentNode = currentNode.next;
	    }
	    return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		else return false;
		
		
	}
	
}
