/**
 * Created by youzhang on 2017/9/24.
 */
public class PartitionEqualSubsetSum {
    //0-1背包问题，DP
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        if(total%2==1) return false;
        total/=2;
        boolean[] sums = new boolean[total+1];
        sums[0] = true;
        for(int i=1;i<sums.length;i++){
            if(i!=nums[0]) sums[i] = false;
            else sums[i] = true;
        }
        if(sums[total]==true) return true;
        for(int i=1;i<nums.length;i++){
            boolean[] currentSum = new boolean[total+1];
            currentSum[0] = true;
            for(int j=1;j<sums.length;j++){
                if(j-nums[i]>=0&&sums[j-nums[i]]) currentSum[j] = true;
                else currentSum[j] = sums[j];
            }
            if(currentSum[total]) return true;
            sums = currentSum;
        }
        return false;
    }
}
