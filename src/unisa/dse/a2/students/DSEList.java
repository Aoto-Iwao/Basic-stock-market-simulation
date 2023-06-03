package unisa.dse.a2.students;

import static org.junit.Assert.fail;

import org.jcp.xml.dsig.internal.dom.DOMSubTreeData;

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
	
	/**
	 * Remove the object at the specified index from the list, if it exists. 
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *	
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	//remove the String at the parameter's index
	public String remove(int index) {
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
		
		
	
	
	/**
	 * Returns the first index of the specified object, or -1 if the object does not exist
	 * in the list. 
	 * @param token
	 * @return The index of the specified token, or -1 if it is not contained in the list. 
	 */
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
	
	
	/**
	 * Get the object at the specified index, if it exists. 
	 * @param index The index to retrieve 
	 * @return The object at the specified index, if it exists, and null if it does not exist
	 */
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
	
	
	/**
	 * Returns true if this list contains no elements. 
	 * @return True if the list is empty. 
	 */
	//checks if there is a list
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of elements in this list. 
	 * @return The number of elements in this list. 
	 */
	//return the size of the list
	public int size() {
		return size;
	}
	
	/**
	 * Returns a string containing the toString() 
	 * 	for each object in this list. 
	 * @return The concatenated toString() for each element in this list
	 */
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

	
	/**
	 * Appends the specified element to the end of this list.
	 * @param obj The object to add. 
	 * @return True if the object has been added to the list. 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
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
	
	//add the parameter String at of the end of the list
		/**
		 * Inserts the specified element at the specified position in this list. 
		 *  Shifts the element currently at that position (if any) and any subsequent 
		 *  elements to the right (adds one to their indices).
		 * @param index Index at which to add
		 * @param obj The object to add
		 * @return True if insertion was successful
		 * 
		 * @throws NullPointerException if the given object is null
		 * @throws IndexOutOfBoundsException if the index is out of range
		 */
	
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
	
	/**
	 * Returns true iff the given object is contained in the list. 
	 * 
	 * @param obj The object whose presence is to be tested
	 * @return True if the list contains the given object
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		
		Node currentNode = head;
		while (currentNode!= null) {
			if(currentNode.getString().equals(obj)) {
				return true;
			}
			currentNode = currentNode.next;
			
		}
	}

	/**
	 * Remove the first instance of the given object from the list, if it exists
	 * @param obj The object to remove
	 * @return True if the object was removed 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	//removes the parameter's String form the list
	public boolean remove(String obj) {
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
	
	/**
	 * Returns the hashCode for this list. 
	 * (This method must satisfy the constraint that if List l1.equals(List l2), 
	 * 	then l1.hashCode() == l2.hashCode() must also be true. 
	 * @return The hashCode of this list. 
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	
	/**
	 * Compares this list with the specified object for equality. 
	 * The equality comparison must be value-based rather than the default 
	 * 	(reference based). 
	 * 
	 * @param obj The object to compare against. 
	 * @return True if the specified object is value-comparatively equal to this list
	 */
	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
