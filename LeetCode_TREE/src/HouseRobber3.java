import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HouseRobber3 {
	public int rob(TreeNode root) {
		return DFS(root)[0];
	}
	
	public int[] DFS(TreeNode root){
		if(root==null){
			int[] result = {0,0,0};
			return result;
		}	
			int[] leftresult=DFS(root.left);
			int[] rightresult = DFS(root.right);
			int thisCondition = Math.max(leftresult[0]+rightresult[0],leftresult[1]+leftresult[2]+rightresult[1]+rightresult[2]+root.val);
			int[] result = new int[3];
			result[0] = thisCondition;
			result[1] = leftresult[0];
			result[2] = rightresult[0];
		    return result;
	}

}
