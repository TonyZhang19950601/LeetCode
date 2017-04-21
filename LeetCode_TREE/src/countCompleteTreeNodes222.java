
public class countCompleteTreeNodes222 {
	public int countNodes(TreeNode root) {
		TreeNode temp = root;
		int level = 0;
        while(temp!=null){
        	temp = temp.left;
        	level++;
        }
        TreeNode tempRoot = root;
        int tempLevel = level;
        double lastAmount = 0;
        while(tempRoot!=null){
        	int level1 = 1;
        	temp = tempRoot.left;
        	if(temp!=null){
        		level1++;
        		temp = temp.right;
        		while(temp!=null){
        			temp = temp.right;
        			level1++;
        		}
        	}
        	if(tempLevel==level1){
        		lastAmount+=Math.pow(2, tempLevel-2);
        		tempRoot = tempRoot.right;
        		tempLevel--;       		
        	}
        	else{
        		tempRoot = tempRoot.left;
        		tempLevel--;
        	}
        }
        return (int)(Math.pow(2,level-1)-1+Math.ceil(lastAmount));
    }
}
