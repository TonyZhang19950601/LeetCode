/**
 * Created by youzhang on 2017/7/31.
 */
public class VerifyPreorderSerelization {
    /*
    firstly, I think of using a stack. the count represents the stack, every time we encounter a non-null node, we push it to the stack by add count++,
    every time we encounter a #, it means that the left subtree of the node on top of the stack is finished. if it is a binary tree, the stack should be
    null before the last node and the last node should be null. Otherwise it is not a binary tree.
    However, this same code have different explanations. for every non-null node, it will increase the capacity the tree by 1(use 1 it self and provide 2 for left and right leaves),
    for every null node, it will decrease the capacity by 1. Thus, we can initialize the capacity as 1, and the capacity should be 0 at last.(-1 if the initial capacity is 0.)
     */

    /*
    * there's also another idea on leetcode that we can calculate the total in-degrees and total out-degrees. every non-null node will provide two out-degrees and 1 in-degrees
    * every null node will provide 1 in-degree. The total in and out degree should be equal if it is a binary tree.*/
    public boolean isValidSerialization(String preorder) {
        String[] nodeList = preorder.split(",");
        if(nodeList.length==0){
            return true;
        }
        int count = 0;
        for(int i=0;i<nodeList.length-1;i++){
            if(!nodeList[i].equals("#")){
                count++;
            }
            else{
                if(count==0){
                    return false;
                }
                count--;
            }
        }
        if(nodeList[nodeList.length-1].equals("#")&&count==0){
            return true;
        }
        return false;
    }
}
