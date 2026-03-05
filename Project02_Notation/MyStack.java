// Programmer: Luke L

import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> 
{
	// fields
	private T[] stack;
	private int topIndex;
	private int numberOfEntries;
	
	// 2 constructors
	
	// default constructor made from 2nd constructor
	public MyStack()
	{
		this(100); // capacity set to 100
	}
	
	// creates stack with specific capacity
	// @param initialCap The integer capacity
	public MyStack(int initialCap)
	{
		@SuppressWarnings("unchecked") // textbook pg 186
		T[] tempStack = (T[])new Object[initialCap];
		stack = tempStack;
		topIndex = -1;
		numberOfEntries = 0;
	}
	
	// methods below
	
	// detects if the stack is empty
	// @return True if the stack is empty
	public boolean isEmpty()
	{
		return topIndex < 0;
		//return numberOfEntries == 0;
	}
	
	// detects if the stack is full
	// @return True if the stack is full
	public boolean isFull()
	{
		boolean status = false;
		if (numberOfEntries == stack.length)
			status = true;
		return status;
	}
	
	// retrieves top entry from stack, removes it from stack
	// @return The top entry
	// @throws StackUnderflowException if stack is empty
	public T pop() throws StackUnderflowException // textbook example, pg 188, is throw done correctly?
	{
		if (isEmpty())
			throw new StackUnderflowException();
		else
		{
			T temp = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			numberOfEntries--;		
			return temp;
		}

	}
	
	// this is peek()
	// retrieves top entry, does not change stack
	// @return The top entry	
	public T top() throws StackUnderflowException
	{
		if (isEmpty())
			throw new StackUnderflowException();
		else
			return stack[topIndex];
	}
	
	// this is getNumEntries()
	// number of elements in the Stack
	// @return the number of elements in the Stack
	public int size()
	{

		return numberOfEntries;
	}
	
	// Adds an element to the top of the Stack
	// @param e the element to add to the top of the Stack
	// @return true if the add was successful, false if not
	// @throws StackOverflowException if stack is full
	public boolean push(T e) throws StackOverflowException
	{
		boolean status = true;
		
		stack[topIndex + 1] = e; // new String added to index 0, then counts up
		topIndex++;
		numberOfEntries++;

		if (topIndex == stack.length)  // if stack is overflowing, textbook pg 187
		{
			status = false;
			throw new StackOverflowException();			
		}
				
		return status;
	}
	
	// this is stringToArray()
	// send back a String of the array
	// @return The current array as String
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
	
	 /* Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 * */
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
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
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
