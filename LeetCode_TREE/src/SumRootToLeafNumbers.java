import java.util.ArrayList;
import java.util.List;
//very short solution on leetcode
/*public int sumNumbers(TreeNode root) {
	return sum(root, 0);
}

public int sum(TreeNode n, int s){
	if (n == null) return 0;
	if (n.right == null && n.left == null) return s*10 + n.val;
	return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
}*/
public class SumRootToLeafNumbers {
	public int sumNumbers(TreeNode root) {
        List<List<Integer>> result = DFS(root);
        int resultNumber = 0;
        for(int i=0;i<result.size();i++){
            resultNumber+=result.get(i).get(1);
        }
        return resultNumber;
    }
    public List<List<Integer>> DFS(TreeNode root){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null){
			return result;
		}
		if(root.left==null&&root.right==null){
				ArrayList<Integer> resultList = new ArrayList<Integer>();				
				result.add(resultList);
				resultList.add(1);
				resultList.add(root.val);
				return result;
		}
	    List<List<Integer>> left = DFS(root.left);
	    List<List<Integer>> right = DFS(root.right);
		for(int i=0;i<left.size();i++){
			ArrayList<Integer> temp = (ArrayList<Integer>)left.get(i);
			int level = temp.get(0)+1;
			int number = temp.get(1);
			number+=root.val*Math.pow(10, level-1);
			temp.set(0, level);
			temp.set(1, number);
			result.add(temp);
		}
		for(int i=0;i<right.size();i++){
			ArrayList<Integer> temp = (ArrayList<Integer>)right.get(i);
			int level = temp.get(0)+1;
			int number = temp.get(1);
			number+=root.val*Math.pow(10, level-1);
			temp.set(0, level);
			temp.set(1, number);
			result.add(temp);
		}
		return result;
		
		
	}

}
