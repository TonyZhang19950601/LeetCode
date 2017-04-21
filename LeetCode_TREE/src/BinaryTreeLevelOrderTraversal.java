import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null){
            return result;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        LinkedList<Integer> levelQueue = new LinkedList<Integer>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        tempList.add(root.val);
        result.add(tempList);
        levelQueue.add(1);
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
        	TreeNode temp = nodeQueue.poll();
        	int level = levelQueue.poll()+1;
        	ArrayList<Integer> singleList = null;
        	if(level-1<result.size()){
        		singleList = (ArrayList<Integer>) result.get(level-1);
        	}
        	else{
        		singleList = new ArrayList<Integer>();
        		result.add(singleList);
        	}
        	if(temp.left!=null){		
        		singleList.add(temp.left.val);
        		nodeQueue.add(temp.left);
        		levelQueue.add(level);
        	}
        	if(temp.right!=null){
        		singleList.add(temp.right.val);
        		nodeQueue.add(temp.right);
        		levelQueue.add(level);
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
