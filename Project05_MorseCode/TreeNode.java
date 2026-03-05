// Programmer: Luke L
public class TreeNode<T>
{
	// fields
	public TreeNode<T> left;
	public TreeNode<T> right;
	private T data; // holds data
	
	// constructors
	// constructor stores int in data variable
	public TreeNode(T data)
	{
		this.data = data;
		left = right = null;

	}
	
	public TreeNode(TreeNode<T> node)
	{
		data = node.getData();
	}
	
	// getter and setter methods below
	public TreeNode<T> getLeftChild()
	{
		return left;
	}
	
	public TreeNode<T> getRightChild()
	{
		return right;
	}
	
	public T getData()
	{
		return data;
	}
	
	public boolean isData()
	{
		boolean status=false;
		if(data==null)
			status = false;
		if(data!=null)
			status = true;
		return status;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	// sets this node's left child to a given node
	// @param newLeftChild  A node that will be the left child
	public void setLeftChild(TreeNode<T> newLeftChild)
	{
		left = newLeftChild;
	}
	
	// sets this node's right child to a given node
	// @param newRightChild  A node that will be the right child
	public void setRightChild(TreeNode<T> newRightChild)
	{
		right = newRightChild;
	}
	
	// gets the number of nodes in the tree
	// @return The number of nodes as an int
	public int getNumberOfNodes()
	{
		int leftNumber=0;
		int rightNumber=0;
		if(left != null) // base case to stop recursion at leafs
			leftNumber = left.getNumberOfNodes();
		if(right != null) // base case to stop recursion at leafs
			rightNumber = right.getNumberOfNodes();
		return 1 + leftNumber + rightNumber;
	}
	
	// gets the height of the tree
	// @return The height of tree as an int
	public int getHeight()
	{
		return getHeight(this); // call private getHeight method below
	}
	
	// private getHeight method
	// @return The height of tree as an int
	// @param the root node to base the height from
	private int getHeight(TreeNode<T> node)
	{
		int height = 0;
		if(node != null)
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		return height;
	}
	
	// copies the subtree rooted at this node
	// @return The root of a copy of the subtree
	public TreeNode<T> copy() // Stub
	{
		TreeNode<T> newRoot = new TreeNode(data);
		if(left != null)
			newRoot.setLeftChild(left.copy());
		if(right != null)
			newRoot.setRightChild(right.copy());
		return newRoot;
	}
}
