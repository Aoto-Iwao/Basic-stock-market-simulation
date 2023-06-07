package unisa.dse.a2.students;

import static org.junit.Assert.fail;

import java.security.cert.CRLReason;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;
	
	private int size;

	public DSEList() {
		this.head = null;
		this.tail = null;
	}
	
	//Create a list with the given head node and calculate the size of the list.
	public DSEList(Node head_) {
		this.head = head_;
		//tail is the head initially.
		this.tail = head;
		
		int size = 0;
		Node currentNode =head;
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
	public DSEList(DSEList other) {
		this.head = other.head;
		this.tail = other.tail;
		this.size = other.size;
		// Copy constructor. 
	}
	
	//remove the String at the parameter's index
	public String remove(int index) {
		//IndexOutOfBoundsException if the specified index is out of range.
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node currentNode = head;
		int currentIndex = 0;
		
		while (currentNode!= null) {
			if (currentIndex == index) {
				String removeString = currentNode.getString();
				
				
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
				return removeString;
			}
			currentIndex++;
			currentNode = currentNode.next;
			}
		return null;
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node currentNode = head;
		int index = 0;
		while (currentNode != null) {
			if (currentNode.getString().equals(obj)) {
				return index;
			}
			currentNode = currentNode.next;
			index++;
		}
		return -1;
		
	}
	

	//returns String at parameter's index
	public String get(int index) {
		if (index < 0|| index>= size) {
			return null;
		}
		int i = 0;
		Node currentNode = head;
		
		while( i < index) {
			currentNode = currentNode.next;
			i++;
		}
		return currentNode.getString();
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
		
		Node current = head;
		while (current!= null) {
			sBuilder.append(current.getString()).append(" ");
			current = current.next;	
		}
		return sBuilder.toString().trim();
	}

	public boolean add(String obj) {
		if (obj == null) {
			throw new NullPointerException("Specified object is null");
		}
		Node newNode = new Node(null, null, obj);
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
	
	//add String at parameter's index
	public boolean add(int index, String obj) {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (obj == null) {
			throw new NullPointerException("The specified object is null");
		}
		
		
		Node newNode = new Node(null, null, obj);
		
		
		//when index is the tail.
		if (index == size) {
			return add(obj);
			
		}
		else if (index == 0) {
			newNode.next = head;
	        if (head != null) { 
	            //newNode.next = head;
	            head.prev = newNode;
	        } else { 
	            tail = newNode;
	        }
	        head = newNode; 
	        
	    } 
		
		else {
			int i = 0;
			Node tempNode = head;
			while (i < index) {
				tempNode = tempNode.next;
				i++;
		
			}
			newNode.next = tempNode;
			newNode.prev = tempNode.prev;
			tempNode.prev.next = newNode;
			tempNode.prev = newNode;
		}
		size++;
		return true;
	}
	

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		Node currentNode = head;
		int index = 0;
		while (currentNode!= null) {
			if(currentNode.getString().equals(obj)) {
				return true;
			}
			currentNode = currentNode.next;
			index++;		
		}
		return false;
	}


	//removes the parameter's String form the list
	public boolean remove(String obj) {
		
		if (obj == null) {
			throw new NullPointerException();
		}
		
		Node currentNode = head;
		while (currentNode!= null) {
			if (currentNode.getString().equals(obj)) {
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
	    Node currentNode = head;
	    while(currentNode != null){
	        hash = 31 * hash + (currentNode.getString() == null ? 0 : currentNode.getString().hashCode());
	        currentNode = currentNode.next;
	    }
	    return hash;	
	}
		
	

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
}
