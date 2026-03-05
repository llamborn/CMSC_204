// Programmer: Luke L
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
	// fields
	TreeNode<String> root;
	
	// constructor
	MorseCodeTree()
	{
		buildTree();
	}
	
	// methods
	// Returns a reference to the root
	// @return reference to root
	public TreeNode<String> getRoot()
	{
		return root;
	}
	
	//sets the root of the Tree
	//@param newNode a TreeNode<T> that will be the new root
	public void setRoot(TreeNode<String> newNode)
	{
		 root = newNode;
	}
	
	 //Adds result to the correct position in the tree based on the code
	 //This method will call the recursive method addNode
	 //@param code the code for the new node to be added
	 public void insert(String code, String letter)
	 {
		 addNode(root, code, letter);
	 }
	
	// This is a recursive method that adds element to the correct position in the tree based on the code.
	// @param root the root of the tree for this particular recursive instance of addNode
	// @param code the code for this particular recursive instance of addNode
	// @param letter the data of the new TreeNode to be added
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		TreeNode<String> node = root;
		TreeNode<String> let = new TreeNode<>(letter);

		for(int i=0; i<code.length(); i++)
		{

			if(code.charAt(i)=='.')
			{
				if(node != null)
				{
					node.setLeftChild(let);
				}
				node = node.getLeftChild();

				//System.out.println("left gotten");

			}
			if(code.charAt(i)=='-')
			{
				node = node.getRightChild();
				//node.setRightChild(let);
				//System.out.println("right gotten");
			}
		}
		node = new TreeNode<String>(letter);
		System.out.println(node.getData());
	}
	 
	// Fetch the data in the tree based on the code
	// This method will call the recursive method fetchNode
	// @param code the code that describes the traversals within the tree
	// @return the result that corresponds to the code
	public String fetch(String code)
	{
		return fetchNode(root, code);
	}
	
	// This is the recursive method that fetches the data of the TreeNode that corresponds with the code
	// @param root the root of the tree for this particular recursive instance of addNode
	// @param code the code for this particular recursive instance of fetchNode
	// @return the data corresponding to the code
	public String fetchNode(TreeNode<String> root, String code)
	{
		TreeNode<String> node = root;
		for(int i=0; i<code.length(); i++)
		{
			if(code.charAt(i)=='.')
				node = node.getLeftChild();
			if(code.charAt(i)=='-')
				node = node.getRightChild();
		}
		return node.getData();
	}
	
	// This method builds the LinkedConverterTree by inserting TreeNodes<T> into their proper locations
	public void buildTree()
	{
		TreeNode<String> first = new TreeNode<>("");
		root = first; // root -> first				
		TreeNode<String> e = new TreeNode<>("e");
		TreeNode<String> t = new TreeNode<>("t");
		TreeNode<String> i = new TreeNode<>("i");
		TreeNode<String> a = new TreeNode<>("a");
		TreeNode<String> n = new TreeNode<>("n");
		TreeNode<String> m = new TreeNode<>("m");
		TreeNode<String> s = new TreeNode<>("s");
		TreeNode<String> u = new TreeNode<>("u");
		TreeNode<String> r = new TreeNode<>("r");
		TreeNode<String> w = new TreeNode<>("w");
		TreeNode<String> d = new TreeNode<>("d");
		TreeNode<String> k = new TreeNode<>("k");
		TreeNode<String> g = new TreeNode<>("g");
		TreeNode<String> o = new TreeNode<>("o");
		TreeNode<String> h = new TreeNode<>("h");
		TreeNode<String> v = new TreeNode<>("v");
		TreeNode<String> f = new TreeNode<>("f");
		TreeNode<String> l = new TreeNode<>("l");
		TreeNode<String> p = new TreeNode<>("p");
		TreeNode<String> j = new TreeNode<>("j");
		TreeNode<String> b = new TreeNode<>("b");
		TreeNode<String> x = new TreeNode<>("x");
		TreeNode<String> c = new TreeNode<>("c");
		TreeNode<String> y = new TreeNode<>("y");
		TreeNode<String> z = new TreeNode<>("z");
		TreeNode<String> q = new TreeNode<>("q");	
		root = first; // root -> first
		first.left = e; 
		first.right = t; // e <- first -> t
		e.left = i;
		e.right = a; // i <- e -> a
		t.left = n;
		t.right = m; // n <- t -> m
		i.left = s;
		i.right = u; // s <- i -> u
		a.left = r;
		a.right = w; // s <- a -> u
		n.left = d;
		n.right = k; // d <- n -> k
		m.left = g;
		m.right = o; // g <- m -> o
		s.left = h;
		s.right = v; // h <- s -> v
		u.left = f; 
		r.left = l; // l <- r -> 
		w.left = p;
		w.right = j; // p <- w -> j
		d.left = b;
		d.right = x; // b <- d -> x
		k.left = c;
		k.right = y; // c <- k -> y
		g.left = z;
		g.right = q; // z <- g -> q
		
	}
	
	// Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	// Used for testing to make sure tree is built correctly
	// @return an ArrayList of the items in the linked Tree
	public ArrayList<String> toArrayList()
	{
		ArrayList<String> result = new ArrayList<>(); 
		
		return result;
	}
	 
	// The recursive method to put the contents of the linked converter tree in an ArrayList<T> LNR (Inorder)
	// @param root the root of the tree for this particular recursive instance
	// @param list the ArrayList that will hold the contents of the tree in LNR order
	public void LNRoutputTraversal(TreeNode<String> r, ArrayList<String> list)
	{
		if(r != null)
		{
			LNRoutputTraversal(r.getLeftChild(), list);
			list.add(r.getData());
			//System.out.print(r.getData());
			LNRoutputTraversal(r.getRightChild(), list);
		}
	}
	
	// gets the number of nodes in the tree
	// @return The number of nodes as an int
	public int getNumberOfNodes()
	{
		int numberOfNodes = 0;
		if(root != null)
			numberOfNodes = root.getNumberOfNodes();
		return numberOfNodes;
	}
	
	// calls private inorderTraverse below
	public void inorderTraverse()
	{
		inorderTraverse(root);
	}
	
	// prints an inorder traversal of the tree
	private void inorderTraverse(TreeNode<String> node)
	{
		if(node != null)
		{
			inorderTraverse(node.getLeftChild());
			System.out.print(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}
	
	// gets the height of the tree
	// @return The height of tree as an int
	public int getHeight()
	{
		int height = 0;
		if(root != null)
			height = root.getHeight();
		return height;
	}
	
	// This operation is not supported for a LinkedConverterTree
	public LinkedConverterTreeInterface<String> delete(String data) //throws UnsupportedOperationException
	{
		 return null; // Stub
	}

	// This operation is not supported for a LinkedConverterTree
	public LinkedConverterTreeInterface<String> update() //throws UnsupportedOperationException
	{
		 return null; // Stub
	}
}
