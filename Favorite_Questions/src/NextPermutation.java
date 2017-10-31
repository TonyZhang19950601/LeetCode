/**
 * Created by youzhang on 2017/10/13.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        for(int i = nums.length-2;i>=0;i--){
            if(nums[i]>=nums[i+1]) continue; //if nums[i]>=nums[i+1], that indicates that (nums[i]...nums[nums.length-1]) is a non-ascending sequence, which represents the largest permutation of (nums[i]...nums[nums.length-1])
            else{
                int j = i+1;
                //find the least element in the following sequence that is larger than nums[i] and swap it with nums[i]
                for(;j<nums.length;j++){
                    if(nums[j]<=nums[i]) break;
                }
                j--;
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                //reverse the (nums[i+1]...nums[nums.length-1]) sequence to the smallest permutation
                int start = i+1, end = nums.length-1;
                while(start<end){
                    temp = nums[start];
                    nums[start] = nums[end];
                    nums[end] = temp;
                    start++;
                    end--;
                }
                return;
            }
        }
        //if nums is the largest permutation, reverse it so that it represents the smallest permutation.
        int start = 0, end = nums.length-1;
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
