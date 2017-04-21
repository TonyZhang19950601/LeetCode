
public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		return DFS(root, 0, sum);
    }
	
	public Boolean DFS(TreeNode root, int parentSum, int sum){
		if(root==null){
			return false;
		}
	    int thisSum = parentSum+root.val;
	    if(root.left==null&&root.right==null){
	    	if(thisSum==sum){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
	    }
	    Boolean leftFlag = DFS(root.left, parentSum, sum);
	    Boolean rightFlag = DFS(root.right,parentSum, sum);
	    return leftFlag||rightFlag;
		
	}

}
