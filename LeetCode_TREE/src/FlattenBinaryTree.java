//the general theory of this question is to do a preorder traversal on the tree. put the left subtree at root.right, and concatenate rightsubtree to the leftsubtree
//meanwhile, we get the leftsubtree's last node to put the rightsubtree's first node at right child of leftsubtree's last node.
public class FlattenBinaryTree {
	public TreeNode flatten(TreeNode root) {
        if(root==null){
            return root;
        }
        if(root.left==null&&root.right==null){
			return root;
		}
		TreeNode left = root.left;
		TreeNode right = root.right;
		TreeNode leftLast = null,rightLast = null;
		if(left!=null){
            leftLast = flatten(left);
            root.right  = left;
            root.left = null;
		}
		else{
		    leftLast = root;
		}
		if(right!=null){
		    rightLast = flatten(right);
		}
		else{
		    rightLast  = leftLast;
		}
		//concatenate the right subtree to the end of left subtree
        leftLast.right = right;
        leftLast.left = null;
        if(rightLast==null){
            return root.right;
        }
        return rightLast;
        
    }
}
