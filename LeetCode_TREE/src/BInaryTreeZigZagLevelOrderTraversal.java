import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//two stack, the node stack records the nodes in the current level, the nextstack records the nodes in the next level,
//when the nodestack is empty, make the nextstack to be the current stack and make the nextstack empty.
public class BInaryTreeZigZagLevelOrderTraversal {
	public static void main(String args[]){
		System.out.println(!false);
	}
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null){
			return result;
		}
		LinkedList<TreeNode> nodeStack = new LinkedList<TreeNode>();
		LinkedList<TreeNode> nextStack = new LinkedList<TreeNode>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        tempList.add(root.val);
        result.add(tempList);
        nodeStack.addFirst(root);
        int level = 2;
        while(!nodeStack.isEmpty()){
        		TreeNode temp = nodeStack.poll(); 
            	ArrayList<Integer> singleList = null;
            	if(level-1<result.size()){
            		singleList = (ArrayList<Integer>) result.get(level-1);
            	}
            	else{
            		singleList = new ArrayList<Integer>();
            		result.add(singleList);
            	}
            	if(level%2!=0){
	            	if(temp.left!=null){		
	            		singleList.add(temp.left.val);
	            		nextStack.addFirst(temp.left);
	            	}
	            	if(temp.right!=null){
	            		singleList.add(temp.right.val);
	            		nextStack.addFirst(temp.right);
	            	}
            	}
            	else{
            		if(temp.right!=null){
	            		singleList.add(temp.right.val);
	            		nextStack.addFirst(temp.right);
	            	}
            		if(temp.left!=null){		
	            		singleList.add(temp.left.val);
	            		nextStack.addFirst(temp.left);
	            	}
            	}
            	if(nodeStack.isEmpty()){
        			nodeStack = nextStack;
        			nextStack = new LinkedList<TreeNode>();
        			level++;
        		}
        }
        for(int i = result.size()-1;i>=0;i--){
	        	if(result.get(i).isEmpty()){
	        		result.remove(i);
	        	}
	        	else{
	        		break;
	        	}
        }
		return result;        
    }

}
