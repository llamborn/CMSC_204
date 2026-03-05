// Programmer: Luke L

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> 
{

	// fields 
	protected Node firstNode;
	protected Node lastNode;
	protected int numberOfEntries;
	
	
	// constructor
	public BasicDoubleLinkedList()
	{
		initializeDataFields();
	}
	
	// methods below
	public void clear()
	{
		initializeDataFields();
	}
	
	private void initializeDataFields()
	{
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}
	
	public void addToEnd(T newEntry) // adds new entry to End of list
	{
		Node newNode = new Node(newEntry);
		if (isEmpty()) // if empty, make it first
			firstNode = newNode;
		else // add to end of non empty list
			lastNode.setNextNode(newNode); // make last node reference new node
		lastNode = newNode; // last node stuff
		numberOfEntries++;
	}
	
	public void addToFront(T newEntry) // adds new entry to Front of list
	{
		Node newNode = new Node(newEntry);
		if (isEmpty()) // last node stuff
		{
			firstNode = newNode;
			lastNode = newNode;
		}
		else // setting first node
		{
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		numberOfEntries++;
	}
	
	public void add(int givenPosition, T newEntry) // adds new entry to a given position
	{
		Node newNode = new Node(newEntry);
		if (isEmpty()) // last node stuff
		{
			firstNode = newNode;
			lastNode = newNode;
		}
		else if (givenPosition == 1) // setting first node
		{
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		else if (givenPosition == numberOfEntries + 1) // last node stuff
		{
			lastNode.setNextNode(newNode);
			lastNode = newNode;
		}
		else
		{
			Node nodeBefore = getNodeAt(givenPosition - 1);
			Node nodeAfter = nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);
		}
		numberOfEntries++;		
	}
	
	public int getSize() // Returns the number of nodes in the list.
	{
		return numberOfEntries;
	}
	
	public T getFirst() // Returns but does not remove the first element from the list.
	{	
		return firstNode.getData();
	}
	
	public T getLast() // STUB Returns but does not remove the last element from the list.
	{
		return lastNode.getData();
	}
	
	public ListIterator<T> iterator() // This method returns an object of the DoubleLinkedListIterator. (the inner class that implements java's ListIterator interface)
	{
		return new DoubleLinkedListIterator(firstNode);
	}
	
	public Node remove(T targetData, Comparator<T> comparator) // Removes the first instance of the targetData from the list. 
	{
		Node current = firstNode;
		Node removed = null;
		for(int i=0; i<numberOfEntries; i++)
		{
			if (comparator.compare(targetData, current.getData()) == 0) // compares data to see if equal)
			{
				removed = current;
				removeGiven(i+1);
				System.out.println("they match");
			}				
			current =current.getNextNode();
		}		
		return removed;
	}
	
	public void removeGiven(int givenPosition) // removes entry at a given position
	{
		T result = null; // used to return removed value
		if (givenPosition == 1) // Case 1: remove first entry
		{
			result = firstNode.getData(); // save entry to be removed
			firstNode = firstNode.getNextNode(); // remove entry
			if (numberOfEntries == 1) // last node stuff
				lastNode = null;
		}
		else // Case 2: not first entry
		{
			Node nodeBefore = getNodeAt(givenPosition - 1); // identifies node before
			Node nodeToRemove = getNodeAt(givenPosition); // identifies current node to remove
			result = nodeToRemove.getData(); // save entry to remove
			Node nodeAfter = nodeToRemove.getNextNode(); // identifies node after
			nodeBefore.setNextNode(nodeAfter); // removes entry
			if (givenPosition == numberOfEntries) // last node stuff
				lastNode = nodeBefore;
		}
		numberOfEntries--;
	}
	
	public T retrieveFirstElement() // Removes and returns the first element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	{
		T result = null; // used to return removed value
		result = firstNode.getData(); // save entry to be removed
		firstNode = firstNode.getNextNode(); // remove entry
		if (numberOfEntries == 1) // last node stuff
			lastNode = null;
		numberOfEntries--;
		return result;
	}
	
	public T retrieveLastElement() // Removes and returns the last element from the list. If there are no elements the method returns null. Do not implement implement this method using iterators.
	{
		T result = null; // used to return removed value
		Node nodeBefore = getNodeAt(numberOfEntries - 1); // identifies node before
		Node nodeToRemove = getNodeAt(numberOfEntries); // identifies current node to remove
		result = nodeToRemove.getData(); // save entry to remove
		Node nodeAfter = nodeToRemove.getNextNode(); // identifies node after
		nodeBefore.setNextNode(nodeAfter); // removes entry		 
		lastNode = nodeBefore; // last node stuff
		
		numberOfEntries--;
		return result;
	}
	
	public Node getNodeAt(int givenPosition)
	{
		Node currentNode = firstNode;
		for(int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		return currentNode;
	}
	
	public ArrayList<T> toArrayList() // Returns an arraylist of all the items in the Double Linked list
	{		
		ArrayList<T> n = new ArrayList<T>();
		Node current = firstNode;
		
		for(int i=0; i<numberOfEntries; i++)
		{			
			n.add(current.getData());
			current =current.getNextNode();
		}
		return n;		
	}
	
	public boolean isEmpty()
	{
		boolean result;
		if (numberOfEntries == 0)
			result = true;
		else
			result = false;
		return result;
	}
	
	///////////////////// inner class Iterator below
	protected class DoubleLinkedListIterator implements ListIterator<T>
	{
		// fields
		private Node currentNode;
		private Node nextNode;
		private Node previousNode;
	
		// constructor
		private DoubleLinkedListIterator(Node start)
		{
			currentNode = start;
			nextNode = start;
			previousNode = null;
		}
		
		// methods below		
		public T next() //throws NoSuchElementException
		{
			T result;
			if (hasNext())
			{
				result = nextNode.getData(); 
				nextNode = nextNode.getNextNode();
				//previousNode = nextNode;
			}
			else
				throw new NoSuchElementException("Iterator at end of list.");
			return result;
		}
		
		public boolean hasNext()
		{			
			return nextNode != null;
		}
		
		public boolean hasPrevious()
		{
			return previousNode != null;
		}

		public T previous() //throws NoSuchElementException
		{

			/*
			//Node currentNode = firstNode;
			for(int counter = 1; counter < (numberOfEntries); counter++)
				currentNode = currentNode.getNextNode();
			return currentNode.getData();
			*/
			return null;
		}

		// don't do below
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
		
		public void add​(T arg0) 
		{
			throw new UnsupportedOperationException();
		}
		
		public int nextIndex() 
		{
			throw new UnsupportedOperationException();
		}
		
		public int previousIndex() 
		{
			throw new UnsupportedOperationException();
		}
		
		public void set​(T arg0)
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
		}

		@Override
		public void add(T e) {
		}
		
	}
	
	///////////////////// inner class Node below
	class Node 
	{
		// fields
		private T data; // entry in the bag
		private Node next; // link to next node, not sure how this works
		private Node previous; // I added this
		
		// constructors
		public Node(T dataPortion)
		{
			this(dataPortion, null, null);
		}
		
		public Node(T dataPortion, Node nextNode, Node previousNode)
		{
			data = dataPortion;
			next = nextNode;
			previous = previousNode;
		}
		
		// methods
		public T getData()
		{
			return data;
		}
		
		public void setData(T newData)
		{
			data = newData;
		}
		
		public Node getNextNode()
		{
			return next;
		}
		
		public void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
		
		public Node getPreviousNode()
		{
			return previous;
		}
		
		public void setPreviousNode(Node previousNode)
		{
			next = previousNode;
		}
	}


	
}
