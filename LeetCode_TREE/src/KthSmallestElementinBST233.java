import java.util.Stack;

public class KthSmallestElementinBST233 {
	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        nodeStack.push(root);
        while(root.left!=null){
        	nodeStack.push(root.left);
        	root = root.left;
        }
        for(int i=1;i<k;i++){
        	TreeNode temp = nodeStack.pop();
        	if(temp.right!=null){
        		temp = temp.right;
        		nodeStack.push(temp);
        		temp = temp.left;
        		while(temp!=null){
        		nodeStack.push(temp);
        		temp = temp.left;
        		}
        	}
        }
        return nodeStack.pop().val;
    }

}
