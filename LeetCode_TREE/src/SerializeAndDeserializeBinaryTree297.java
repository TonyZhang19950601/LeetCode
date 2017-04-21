import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;
//思路是记录树中每个节点的值和其父节点的位置，按前序遍历的方法遍历整个树
public class SerializeAndDeserializeBinaryTree297 {
// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	ArrayList<String> seHb = new ArrayList<String>();
    	seHb.add("0");  //首位加零是为了使树的首个节点从1开始，方便计算
    	preTraversal(root,0, true,seHb);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<seHb.size();i++){
    	    sb.append(seHb.get(i)+",");
    	}
    	System.out.println(sb);
    	return sb.toString();
    }
//pIndex传入父节点在遍历中的次序，LR true表示该节点是左节点，false表示该节点是右节点
    public void preTraversal(TreeNode root, int pIndex, Boolean LR, ArrayList<String> SeHb){
    	if(root==null){
    		return;
    	}
    	if(LR){
    		SeHb.add(pIndex+"n"+root.val);
    	}
    	else{
    		SeHb.add(-pIndex+"n"+root.val);
    	}
    	int cIndex = SeHb.size()-1;
    	preTraversal(root.left, cIndex,true, SeHb);
    	preTraversal(root.right, cIndex,false, SeHb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	StringTokenizer st = new StringTokenizer(data,",");
        if(st.countTokens()==1){
            return null;
        }
        Hashtable<Integer, String> seHb = new Hashtable<Integer, String>();
        st.nextToken();
        int i=1;
        //解析字符串并将每个节点放入hashtable中，以父节点的index为key，value为该节点的index和值
        while(st.hasMoreTokens()){
        	StringTokenizer st1 = new StringTokenizer(st.nextToken(),"n");
        	seHb.put(Integer.parseInt(st1.nextToken()), i+"n"+st1.nextToken());
        	i++;
        }
        TreeNode root = new TreeNode(Integer.parseInt(seHb.get(0).split("n")[1]));
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> indexStack = new Stack<Integer>();
        nodeStack.add(root);
        indexStack.add(1);
        int count = 0;
        //当stack不为空时，pop stack，并为该节点添加左右孩子，并将它的左右孩子压入stack
        while(!nodeStack.isEmpty()){
        	TreeNode temp = nodeStack.pop();
        	int index = indexStack.pop();
        	if(seHb.get(index)!=null){
        		StringTokenizer tempSt  = new StringTokenizer(seHb.get(index),"n");
        		indexStack.add(Integer.parseInt(tempSt.nextToken()));
        		temp.left = new TreeNode(Integer.parseInt(tempSt.nextToken()));
        		nodeStack.push(temp.left);        		
        	}
        	if(seHb.get(-index)!=null){
        		StringTokenizer tempSt  = new StringTokenizer(seHb.get(-index),"n");
        		indexStack.add(Integer.parseInt(tempSt.nextToken()));
        		temp.right = new TreeNode(Integer.parseInt(tempSt.nextToken()));
        		nodeStack.push(temp.right);     		
        	}
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));