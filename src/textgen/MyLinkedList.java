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
		if(index>size || index<0){
			throw new IndexOutOfBoundsException("Index exceeds size of the list");
		}
		LLNode<E> myNode = new LLNode<E>(element);
		if(element!=null){
			   //case at the end
			if(index==size){
				add(element);
			} //case at the start of non empty list
			else if(index==0 && size!=0){
				myNode.next = head.next;
				myNode.prev = myNode.next.prev;
				myNode.next.prev = myNode;
				head.next = myNode;
			} //case in the middle
			else{
				LLNode<E> myCurrentNode;
				myCurrentNode=head;
				for (int i = 0; i<index; i++){
					myCurrentNode = myCurrentNode.next;
				}
				myNode.next = myCurrentNode.next;
				myNode.prev = myCurrentNode;
				myCurrentNode.next.prev  = myNode;
				myCurrentNode.next = myNode;
			}
			size++;
		}

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
			throw new IndexOutOfBoundsException("Index exceeds size of the list");
		}
		else{
			E myReturnItem;
			//case at the end of list
			if(index==size-1){
				myReturnItem = tail.prev.data;
				tail.prev.prev.next = tail;
				tail.prev = tail.prev.prev;
			}
			//case at the start of the list
			else if(index==0){
				myReturnItem = head.next.data;
				head.next = head.next.next;
				head.next.prev = head;
			}
			//case at the middle of the list
			else{
				LLNode<E> myCurrentNode=head;
				for (int i = 0; i<=index; i++){
					myCurrentNode = myCurrentNode.next;
				}
				myReturnItem = myCurrentNode.data;
				myCurrentNode.prev.next = myCurrentNode.next;
				myCurrentNode.next.prev = myCurrentNode.prev;
			}
			size--;
			return myReturnItem;
		}
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
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// E.g. you might want to add another constructor
	public LLNode(){
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
