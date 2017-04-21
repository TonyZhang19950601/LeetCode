import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum2 {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return DFS(root, 0, sum);
    }
	public List<List<Integer>> DFS(TreeNode root, int parentSum, int sum){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null){
			return result;
		}
		int thisSum = parentSum+root.val;
		if(root.left==null&&root.right==null){
			if(thisSum==sum){
				LinkedList<Integer> resultList = new LinkedList<Integer>();				
				result.add(resultList);
				resultList.addFirst(root.val);
				return result;
			}
			else{
				return result;
			}
		}
	    List<List<Integer>> left = DFS(root.left,thisSum, sum);
	    List<List<Integer>> right = DFS(root.right, thisSum, sum);
		for(int i=0;i<left.size();i++){
			LinkedList<Integer> temp = (LinkedList<Integer>)left.get(i);
			temp.addFirst(root.val);
			result.add(temp);
		}
		for(int i=0;i<right.size();i++){
			LinkedList<Integer> temp = (LinkedList<Integer>)right.get(i);
			temp.addFirst(root.val);
			result.add(temp);
		}
		return result;
		
		
	}

}
