package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> temp = new QueueNode<E>(e);
		
		if(last == null)
		{
			last = temp;
			last.next = last;
		}
		else
		{
			temp.next = last.next;
			last.next = temp;
			last = temp;
		}
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last != null)
			return last.next.element;
		else
			return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E temp = this.peek();
		if(this.size < 2)		//if there is 0 or 1 element in the queue.
		{
			size = 0;
			last = null;
		}
	
		else					//if there is at least 2 elements in the queue.
		{
			size--;
			last.next = last.next.next;
		}
		return temp;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	  /**
     * Appends the specified queue to this queue
     * post: all elements from the specified queue are appended
     * to this queue. The specified queue (q) is empty after the call.
     * @param q the queue to append
     * @throws IllegalArgumentException if this queue and q are identical
     */
	public void append(FifoQueue<E> q) {
		if(q == this)
			throw new IllegalArgumentException("queues not allowed to be identical.");
		if(q.last == null)
			return;
		if(this.last == null)
		{
			this.last = q.last;
			this.size = q.size;
		}
			
		QueueNode<E> temp = q.last.next;
		q.last.next = this.last.next;
		this.last.next = temp;
		this.last = q.last;
		q.last = null;		//making sure the specified queue (q) is empty after the call.
		q.size = 0;			//--||--
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	

    private class QueueIterator implements Iterator<E> {
        private QueueNode<E> pos;

        /* Konstruktor */
        private QueueIterator() {
        	if(last != null)
        		this.pos = last.next;
        }
        public boolean hasNext() {
			if(pos == null)
				return false;
			else
				return true;
        }
        public E next() {
			if(!this.hasNext())
				throw new NoSuchElementException("there is no more elements in the queue.");
			
			E temp = pos.element;
			if(pos == last)
				pos = null;
			else
				pos = pos.next;

			return temp;
        	
        }
    }

}
