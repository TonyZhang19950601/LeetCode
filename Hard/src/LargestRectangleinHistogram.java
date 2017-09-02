/**
 * Created by youzhang on 2017/9/2.
 */
import java.util.Stack;
public class LargestRectangleinHistogram {
    /*
    * This is a solution based on the idea from http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
    * for detail explanation of this algorithm, please refer to that url*/
    public int largestRectangleArea(int[] heights) {
        if(heights==null||heights.length==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int max = 0;
        for(int i=1;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                int current = stack.pop();
                int height = heights[current];
                int left = current;
                if(stack.isEmpty()){
                    left = 0;
                }
                else{
                    left = stack.peek()+1;
                }
                max = Math.max(max, height*(i-left));
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int current = stack.pop();
            //System.out.println(heights[current]);
            int left = current;
            if(stack.isEmpty()){
                left = 0;
            }
            else{
                left = stack.peek()+1;
            }
            max = Math.max(max, (heights.length-left)*heights[current]);
        }
        return max;
    }
}
