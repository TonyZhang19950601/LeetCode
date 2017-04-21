import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class postOrderTraversal {
	//后续遍历顺序为左-右-中，因此进行中-右-左的前序遍历，每次的结果通过linkedlist的addfirst()方法加到结果的头部，即可得到后续遍历的结果。
	public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        TreeNode temp  = root;
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        while(temp!=null){
        		if(temp.left!=null){
        			nodeStack.add(temp.left);
        		}
        		result.addFirst(temp.val);
        		temp = temp.right;
        }
        while(!nodeStack.isEmpty()){
        		TreeNode tempNode = nodeStack.pop();
        		result.addFirst(tempNode.val);
        		if(tempNode.left!=null){
        			nodeStack.push(tempNode.left);
        		}
        		tempNode = tempNode.right;
        		while(tempNode!=null){
        			if(tempNode.left!=null){
        				nodeStack.push(tempNode.left);
        			}
        			result.addFirst(tempNode.val);
        			tempNode = tempNode.right;
        		}
        }
        return result;
    }
}
