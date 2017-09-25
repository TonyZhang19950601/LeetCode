import java.util.ArrayList;
import java.util.List;

/**
 * Created by youzhang on 2017/9/25.
 */
public class FindAllNumbersDisasspeardInAnArray {
    //类似first missing positive,是那道题的简化版
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1){
                if(nums[i]==0||nums[nums[i]-1] == nums[i]){
                    nums[i]=0;
                    break;
                }
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)
                res.add(i+1);
        }
        return res;
    }
}
