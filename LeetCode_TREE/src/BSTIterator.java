import java.util.ArrayList;
import java.util.LinkedList;
//this is a solution on leetcode. However, the running time of nect() seems to be O(h), not O(1). cannot figure out what the average complexity of O(1) means.
/*public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    *//** @return whether we have a next smallest number *//*
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    *//** @return the next smallest number *//*
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        for (; node != null; stack.push(node), node = node.left);
    }
}*/

//the following solution doesn't fulfill the O(h) memory requirement. h is the height of the tree.
public class BSTIterator {
	LinkedList<Integer> iterateList;
    public BSTIterator(TreeNode root) {
    	iterateList = new LinkedList<Integer>();
    	inOrderTraversal(root, iterateList);
    	
    }
    public void inOrderTraversal(TreeNode root, LinkedList<Integer> iterateList){
    	if(root == null){
    		return;
    	}
    	else{
    		inOrderTraversal(root.left, iterateList);
    		iterateList.add(root.val);
    		inOrderTraversal(root.right, iterateList);
    	}
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(iterateList.isEmpty()){
        	return false;
        }
        return true;
        
    }

    /** @return the next smallest number */
    public int next() {
    	return iterateList.poll();
        
    }
}
