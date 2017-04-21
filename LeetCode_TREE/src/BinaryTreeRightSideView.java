import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//below is a simple recursive solution on leetcode
/*public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    rightView(root, result, 0);
    return result;
}

public void rightView(TreeNode curr, List<Integer> result, int currDepth){
    if(curr == null){
        return;
    }
    if(currDepth == result.size()){
        result.add(curr.val);
    }
    
    rightView(curr.right, result, currDepth + 1);
    rightView(curr.left, result, currDepth + 1);
    
}*/
public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		Stack<Integer> levelStack = new Stack<Integer>();
		TreeNode temp = root;
		int level = 0;
		while(temp!=null){
			nodeStack.push(temp);
			level++;
			levelStack.push(level);
			result.add(temp.val);
			temp = temp.right;
		}
		while(!nodeStack.isEmpty()){
			TreeNode tempNode = nodeStack.pop();
			int thisLevel = levelStack.pop();
			if(tempNode.left!=null){
			    tempNode = tempNode.left;
				nodeStack.add(tempNode);
				if(thisLevel+1>level){
					result.add(tempNode.val);
					level++;
				}
				levelStack.add(thisLevel+1);
				tempNode = tempNode.right;
				int tempLevel = thisLevel+1;
				while(tempNode!=null){
					nodeStack.push(tempNode);
					tempLevel++;
					levelStack.push(tempLevel);
					if(tempLevel>level){
						result.add(tempNode.val);
						level++;
					}
					tempNode = tempNode.right;
				}
				
			}
			
		}
		return result;      

    }

}
