// Programmer: Luke L

import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> 
{
	// fields
	private T[] stack;
	private int topIndex;
	private int bottomIndex;
	private int numberOfEntries;
	
	// 2 constructors
	
	// default constructor made from 2nd constructor
	public MyQueue()
	{
		this(100); // capacity set to 100
	}
	
	// creates stack with specific capacity
	// @param initialCap The integer capacity
	public MyQueue(int initialCap)
	{
		@SuppressWarnings("unchecked") // textbook pg 186
		T[] tempStack = (T[])new Object[initialCap];
		stack = tempStack;
		topIndex = -1;
		bottomIndex = 0;
		numberOfEntries = 0;
	}
	
	// methods below
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty()
	{
		return topIndex < 0;
		//return numberOfEntries == 0;
	}
	
	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull()
	{
		boolean status = false;
		if (numberOfEntries == stack.length)
			status = true;
		return status;
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException
	{
		if (isEmpty())
			throw new QueueUnderflowException();
		else
		{
			T temp = stack[bottomIndex];
			stack[bottomIndex] = null;
			topIndex--;
			numberOfEntries--;	
			bottomIndex++;
			return temp;
		}
	}
	
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size()
	{
		return numberOfEntries;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException
	{
		boolean status = true;
		
		stack[topIndex + 1] = e; // new String added to index 0, then counts up
		topIndex++;
		numberOfEntries++;

		if (topIndex == stack.length)  // if stack is overflowing, textbook pg 187
		{
			status = false;
			throw new QueueOverflowException();			
		}
				
		return status;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString()
	{
		String result = "";
		int length = numberOfEntries;
		for(int i=0; i<length; i++)
		{
			result += stack[i];
		}
		return result;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter)
	{
		String result = "";
		int length = numberOfEntries;
		for(int i=0; i<length; i++)
		{
			result += stack[i];
			if (i<(length-1))
				result += delimiter;			
		}
		return result;
	}
	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full	 
	  */
	public void fill(ArrayList<T> list) //throws StackOverflowException
	{
		ArrayList<T> templist = new ArrayList<T>();
		templist = list;
		
		for (int i=0; i<templist.size(); i++)
		{
			stack[topIndex + 1] = templist.get(i); // new String added to index 0, then counts up
			topIndex++;
			numberOfEntries++;
		}
	}
	
}
