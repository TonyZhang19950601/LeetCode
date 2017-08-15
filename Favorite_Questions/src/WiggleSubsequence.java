/**
 * Created by youzhang on 2017/8/15.
 */
/*
* this is a typical greedy problem.
* it is easy to come up with DP solution.
* However, we find out that the maxlength_subarray(0,n) will be either equal to maxlength_subarray(0,n-1) or 1+maxlength_subarray(0,n-1)
* this is because: if nums[n] can construct a wiggle sequence with max_wiggle_sequence(n-1), then maxlength_subarray(0,n) = maxlength_subarray(0,n-1)+1;
*                  else : nums[n] should be able to construct a wiggle sequence whose length = maxlength_subarray(0,n-1) and all the numbers who can construct a wiggle sequence with max_wiggle_sequence end with nums[n-1] should be
 *                  able to construct a wiggle sequence with max_wiggle_sequence ends with nums[n].
* */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length<=1) return nums.length;
        int current = 1;
        int flag = 0;
        for(int i=1;i<nums.length;i++){
            if((flag==1&&nums[i]<nums[i-1])||
                    (flag==-1&&nums[i]>nums[i-1])||
                    (nums[i]==nums[i-1])){
                continue;
            }
            else{
                current++;
                if(nums[i]>nums[i-1]){
                    flag = -1;
                }
                else{
                    flag = 1;
                }
            }
        }
        return current;
    }
}
