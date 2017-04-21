
public class balancedBinaryTree110 {
	public boolean isBalanced(TreeNode root){
		if(helper(root)==-1){
			return false;
		}
		return true;
	}
	public int helper(TreeNode root){
		if(root ==null){
			return 0;
		}
		if(root.left==null&&root.right==null){
			return 1;
		}
		int leftLevel = helper(root.left);
		int rightLevel = helper(root.right);
		if(leftLevel==-1||rightLevel==-1){
			return -1;
		}
		if(Math.abs(leftLevel-rightLevel)>1){
			return -1;
		}
		return 1+Math.max(rightLevel, leftLevel);
	}

}
