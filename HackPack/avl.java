import java.util.*;

class Node{
	
	int data, height;
	Node left, right;
	
	Node(int data) {
		this.data = data;
		left = right = null;
		height = 1;
	}
}

public class avl {

	Node root;
	boolean balance;

	 int height(Node node) 
    {
        if (node == null)
            return 0;
  
        return node.height;
    }
  

	boolean isBalanced(Node node)
	{
			int leftHeight , rightHeight;

			if (node == null)
				return true;

			leftHeight = height(node.left);
			rightHeight = height(node.right);

			if(Math.abs(leftHeight - rightHeight) <= 1 && 
			   isBalanced(node.left) &&
			   isBalanced(node.right))
			   		return true;

			 return false;
	}
	
	void insert (int x) {
		root = insertRecursive(root, x);
	}

	Node insertRecursive(Node root, int x) {
        
        if (root == null)  {
            root = new Node(x);
            return root;
        }
 
        
        if (x < root.data)
            root.left = insertRecursive(root.left, x);
        else if (x > root.data)
            root.right = insertRecursive(root.right, x);
        
        root.height = 1 + Math.max( height(root.left), height(root.right));


 			
 		return root;
	}
	
	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		int trees = in.nextInt();
		
		for(int loop = 0; loop < trees; loop++) {
			
			int inserts = in.nextInt();
			avl tree = new avl();
			int treeNum = loop + 1;

			for(int i = 0; i < inserts; i++)
			{
				tree.insert(in.nextInt());
				if(!tree.isBalanced(tree.root) )
				{
					System.out.printf("Tree #%d: REMOVE", treeNum);
					break;
				}
			}

			if(tree.isBalanced(tree.root) )
				System.out.printf("Tree #%d: KEEP", treeNum);
		}

	}
}