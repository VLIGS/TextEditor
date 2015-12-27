package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
        head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        head.next = null;
        tail.prev = null;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {
        if(element==null){                  //can not add null element to list
            return false;
        }
        else {
            LLNode<E> myNode = new LLNode<E>(element);
            if(emptyList()){                //if first element of the list
                myNode.next = tail;
                myNode.prev = head;
                head.next = myNode;
                tail.prev = myNode;
            }
            else{
                myNode.next = tail;
                myNode.prev = tail.prev;
				tail.prev.next = myNode;
                tail.prev = myNode;
            }
            size++;
            return true;
        }
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
        LLNode<E> myReturnNode = head;
        if(emptyList()){
            throw new IndexOutOfBoundsException("Index exceeds size of the list");
        }
        else if ((index > size -1)^(index<0)) {
            throw new IndexOutOfBoundsException("Index exceeds size of the list");
        }
        else {
            for (int i = 0; i<=index; i++){
                myReturnNode = myReturnNode.next;
            }
        }
		return myReturnNode.data;
	}
    private boolean emptyList()
    {
        if(size==0) {
            return true;
        }
        else {
            return false;
        }
    }
	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
        //size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(size == 0){
			return null;
		}
		if(index>size){

		}
		// TODO: Implement this method
		size--;
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
