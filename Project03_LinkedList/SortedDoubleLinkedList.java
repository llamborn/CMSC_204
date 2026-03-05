// Programmer: Luke L
import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>
{
	// fields	
	protected Comparator<T> comparator = null;
	
	// constructor
	public SortedDoubleLinkedList(Comparator<T> compareableObject)
	{
		comparator = compareableObject;
	}
	
	// methods 
	public void add(T newEntry) // Inserts the specified element at the correct position in the sorted list. Must be sorted.
	{
		Node newNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);
		
		if (isEmpty() || (nodeBefore==null)) // add at beginning
		{
			newNode.setNextNode(firstNode);
			firstNode = newNode;
			lastNode = newNode; // added
		}
		else
		{
			Node nodeAfter = nodeBefore.getNextNode(); // add after nodeBefore
			
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);
			lastNode = newNode; // added
		}
		numberOfEntries++;
	}
	
	private Node getNodeBefore(T anEntry) // gets the Node before entry
	{
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while ((currentNode != null) && (comparator.compare(anEntry, currentNode.getData())) > 0)
		{
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		return nodeBefore;
		
	}

	public void addToEnd​(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public void addToFront(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public ListIterator<T> iterator() // Implements the iterator by calling the super class iterator method.
	{
		return super.iterator();
	}
	public Node remove(T targetData, Comparator<T> comparator) // Implements the remove operation by calling the super class remove method.
	{		
		return super.remove(targetData, comparator);
	}

}
