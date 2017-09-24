/**
 * Created by youzhang on 2017/9/22.
 */
public class FirstMissingPositive {
    //O(n) time complexity and O(n) space complexity with bucket sort.
    public static int firstMissingPositive(int[] nums){
        int positiveNums = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0) positiveNums++;
        }
        int[] buckets = new int[positiveNums+1];
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                if(nums[i]>=buckets.length) continue;
                else buckets[nums[i]] = 1;
            }
        }
        for(int i=1;i<buckets.length;i++){
            if(buckets[i]==0) return i;
        }
        return buckets.length;
    }
    //O(n) time complexity and O(1) space
    public int firstMissingPositiveBetter(int[] nums) {
        int positiveNums = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0) positiveNums++;
        }
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1&&nums[i]!=0){
                if(nums[i]>positiveNums||nums[i]<=0) nums[i] = 0;
                else if(nums[i]==nums[nums[i]-1]) nums[i] = 0;
                else{
                    int temp = nums[i];
                    nums[i] = nums[temp-1];
                    nums[temp-1] = temp;
                }
            }
        }
        for(int i=0;i<positiveNums;i++){
            if(nums[i]!=i+1) return i+1;
        }
        return positiveNums+1;
    }
}
