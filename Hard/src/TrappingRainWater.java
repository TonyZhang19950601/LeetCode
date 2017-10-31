
/**
 * Created by youzhang on 2017/10/30.
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if(height==null||height.length==0) return 0;
        int leftIndex = 0,res = 0;
        for(int i = 1;i<height.length;i++) {
            if(height[i]>=height[leftIndex]) {
                for(int j =  i-1;j>leftIndex;j--) {
                    res+=height[leftIndex]-height[j];
                }
                leftIndex = i;
            }
        }
        int rightIndex = height.length-1;
        for(int i = rightIndex-1;i>leftIndex;i--) {
            if(height[i]<height[rightIndex])
                res+=height[rightIndex]-height[i];
            else
                rightIndex = i;
        }
        return res;
    }
}
