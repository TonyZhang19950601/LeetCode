/**
 * Created by youzhang on 2017/8/28.
 */
import java.util.Stack;
/*
* The string is in essence the preorder traversal of the directory tree. We need to find the longest way(count on characters,
* not levels) from root to leaf(a file). Thus, for every directory, we push the length from root to it into the stack, for every file,
* we examine the length from root to it and see whether it's larger than the current max.
* to find a parent of the current dir/file, we only need to find the first dir in the stack whose depth is smaller than the current depth(
* the depth can be counted based on the prefix \t)
* */
public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> depthStack = new Stack<Integer>();
        int max = 0;
        int current = 0;
        boolean isFile = false;
        int currentDepth = 0;
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if(c=='\n') {
                //find the parent dir in the tree
                while(!depthStack.isEmpty()&&depthStack.peek()>=currentDepth){
                    stack.pop();
                    depthStack.pop();
                }
                int lastDepth = 0;
                if(!stack.isEmpty()){
                    lastDepth = stack.peek();
                }
                if(isFile){
                    max = Math.max(lastDepth+current, max);
                }
                else{
                    stack.push(lastDepth+current+1);
                    depthStack.push(currentDepth);
                }
                currentDepth = 0;
                //count the depth in the tree
                while(i+1<input.length()&&input.charAt(i+1)=='\t'){
                    i++;
                    currentDepth++;
                }
                current = 0;
                isFile = false;
            }
            else{
                if(c=='.'){
                    isFile = true;
                }
                current++;
            }
        }
        //deal with the last file in th string
        if(isFile){
            int lastDepth = 0;
            while(!depthStack.isEmpty()&&depthStack.peek()>=currentDepth){
                stack.pop();
                depthStack.pop();
            }
            if(!stack.isEmpty()){
                lastDepth = stack.peek();
            }
            if(lastDepth+current>max){
                max = lastDepth+current;
            }
        }
        return max;
    }
    //a more concise and clear solution come up with when doing this question the second time
    public int betterSolution(String input){
        String[] paths = input.split("\n");
        Stack<Integer> lenStack = new Stack<Integer>();
        Stack<Integer> levelStack = new Stack<Integer>();
        int max = 0;
        for(int i=0;i<paths.length;i++){
            int curLevel = 0;
            for(;curLevel<paths[i].length();curLevel++){
                if(paths[i].charAt(curLevel)!='\t') break;
            }
            int curLength = paths[i].length()-curLevel;
            int len = 0;
            while(!levelStack.isEmpty()&&levelStack.peek()>=curLevel){
                levelStack.pop();
                lenStack.pop();
            }
            if(!lenStack.isEmpty()) len = lenStack.peek();
            if(paths[i].contains(".")){
                max = Math.max(len+curLength, max);
            }
            else{
                lenStack.push(len+curLength+1);
                levelStack.push(curLevel);
            }
        }
        return max;
    }
}
