import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LowestCommonAncestorofBinaryTree {
	//optimize solution on leetcode
	/*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    if (root == null || root == p || root == q) return root;
	    TreeNode left = lowestCommonAncestor(root.left, p, q);
	    TreeNode right = lowestCommonAncestor(root.right, p, q);
	    return left == null ? right : right == null ? left : root;
	}
*/	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	LinkedList<TreeNode> pList = new LinkedList<TreeNode>();
    deepFind(root, p, pList);
    pList.addFirst(root);
    LinkedList<TreeNode> qList = new LinkedList<TreeNode>();
    deepFind(root,q,qList);
    qList.addFirst(root);
    TreeNode result = null;
    for(int i=0;i<pList.size()&&i<qList.size();i++){
    		if(pList.get(i).equals(qList.get(i))){
    			result = pList.get(i);
    		}
    		else{
    			return result;
    		}
    }
    return result;
}
public Boolean deepFind(TreeNode root,TreeNode p, LinkedList<TreeNode> pList){
	if(root == null){
		return false;
	}
	else if(root.equals(p)){
        return true;
    }
    else{
        if(deepFind(root.left, p,pList)){
        		pList.addFirst(root.left);
        		return true;
        }
        if(deepFind(root.right, p, pList)){
        		pList.addFirst(root.right);
        		return true;
        }
        return false;
    }
}
}
