import java.util.ArrayList;
import java.util.LinkedList;

public class LowestCommonAncestorofBinarySearchTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode temp = root;
		LinkedList<TreeNode> pList = new LinkedList<TreeNode>();
		while(temp!=p&&temp!=null){
			pList.add(temp);
			if(temp.val>p.val){
				temp = temp.left;
			}
			else{
				temp = temp.right;
			}
		}
		pList.add(temp);
		temp = root;
		LinkedList<TreeNode> qList = new LinkedList<TreeNode>();
		while(temp!=q&&temp!=null){
			qList.add(temp);
			if(temp.val>q.val){
				temp = temp.left;
			}
			else {
				temp = temp.right;
			}		
		}
		qList.add(temp);
		TreeNode LA = null;
		while(!pList.isEmpty()&&!qList.isEmpty()){
			if(pList.get(0).equals(qList.get(0))){
				LA = pList.getFirst();
				pList.poll();
				qList.poll();
			}
			else{
				return LA;
			}
		}
		return LA;
    }

}

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}