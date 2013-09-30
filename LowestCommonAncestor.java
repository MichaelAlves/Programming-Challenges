import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
This program finds the lowest common ancestor in a binary search tree
*/
public class LowestCommonAncestor{
	
	public class TreeNode {
		 protected int value;
	     protected TreeNode left;
	     protected TreeNode right;
	     protected TreeNode parent;
	     
	     public TreeNode()
	     {
	    	 this.value = 0;
	    	 left = null;
	    	 right = null;
	    	 parent = null;
	    	 
	     }

	     public TreeNode(int value) {
	           this.value = value;
	           left = null;
	           right = null;
	           parent = null;
	          
	     }
	     
	     public void insert_node(TreeNode new_node)
	     {
	    	 if(new_node.value < this.value)
	    	 {
	    		 if(left==null)
	    			 left = new_node;
	    		 else
	    			 new_node.parent = left;
	    			 left.insert_node(new_node);
	    	 }
	    	 if(new_node.value > this.value)
	    	 {
	    		 if(right==null)
	    			 right = new_node;
	    		 else
	    			 new_node.parent = right;
	    			 right.insert_node(new_node);
	    	 }
	    	 
	     }
	     public void inorder_print()
	     {
	    	 if(left!=null)
	    		 left.inorder_print();
	    	 System.out.println(value + "\n");
	    	 
	    	 if(right!=null)
	    		 right.inorder_print();
	     }
		    	
		    
	}
	
	public class BST extends TreeNode {
		private TreeNode root;
		 
	    public BST() {
	          root = null;
	    }
	    
	    public void insert(int data){
	    	TreeNode new_node = new TreeNode(data);
	    	if(root==null)
	    		root = new_node;
	    	else
	    		root.insert_node(new_node);
	     }
	    public void print_inorder(){
	    	if (root!= null) {
	    		root.inorder_print();
	    	}
	    }
	    
	    public TreeNode findParent(TreeNode theroot, TreeNode child){
	    	if(theroot.left == child || theroot.right == child)
	    		return theroot;
	    	if(child.value < theroot.value) //search left subtree
	    		return findParent(theroot.left, child);
	    	if(child.value > theroot.value)
	    		return findParent(theroot.right, child); //search right subtree
	    	return null;
	    		
	    	
	    }
	   //the idea is to find where node A and node B diverge
	    public TreeNode findLCA(TreeNode rootn, int a, int b){   //finds lowest common ancestor of two nodes
	    	if(rootn == null)
	    		return null;
	    	if(a > rootn.value && b > rootn.value) //node A and B in right subtree
	    		return findLCA(rootn.right, a, b);
	    	if(a < rootn.value && b < rootn.value)	//node A and B in left subtree
	    		return findLCA(rootn.left, a, b);
	    	if((a < rootn.value && rootn.value == b)|| (b > rootn.value && rootn.value==a)) //this algorithm implicitly assumes that a node can be its own ancestor. However, the codeeval problem
	    		return findParent(root, rootn);						//defines ancestor differently. In the case where node A (or B) == value
	    											// and node B(or A) is in a subtree of node A (or B), find the parent of node A (or B)
	    	return rootn;	//return the node where node A and B break off into separate branches
	    }
	   
	}
	    

	   
	
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		LowestCommonAncestor st = new LowestCommonAncestor();
		LowestCommonAncestor.BST tree = st.new BST();
		
		/*Tree appearance 
		 * 
		 * 				30
		 *             /  \
		 * 			  8   52
		 * 			 / \   
		 * 			3	20
		 * 		   	   /  \
		 * 			  10   29
		 * 
		 * 
		 * */
		tree.insert(30);
	    tree.insert(52);
		tree.insert(8);
	    tree.insert(3);
		tree.insert(20);
		tree.insert(10);
		tree.insert(29);
	    String file = args[0];
	    BufferedReader br = null;
	    try {
	        br = new BufferedReader(new FileReader(file));
	        String line = null;
	        while ((line = br.readLine()) != null) {
	            String[] lineArray = line.split("\\s");
	            if (lineArray.length > 0) {
	                System.out.println(tree.findLCA(tree.root, Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1])).value);
	             }
	        }
	    }
	    finally{
	        if (br != null) {
	            br.close();
	            }
	        }
	  }
	}
	   


