/**
 * Created by youzhang on 2017/10/12.
 */
public class FindPeakElement {
    //a more concise solution with Binary Search
    /*int findPeakElement(int num[]) {
        if (num.length <= 1) return 0;
        int mid = 0, l = 0, h = num.length - 1;

        while (l < h) {
            mid = (l + h) / 2;
            if (num[mid] > num[mid + 1])
                h = mid;
            else if (num[mid] < num[mid + 1])
                l = mid + 1;
        }

        return l;
    }*/
    //my solution using Binary Search
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length-1, middle = 0;
        while(low<=high){
            middle = (low+high)/2;
            //if(low==high) return middle;
            int left = Integer.MIN_VALUE;
            int right = Integer.MAX_VALUE;
            if(middle>0) left = nums[middle-1];
            if(middle<nums.length-1) right = nums[middle+1];
            if(nums[middle]>left&&nums[middle]>right) return middle;
            else if(nums[middle]>=left) low = middle+1;
            else high = middle-1;
        }
        return (low+high)/2;
    }
}
