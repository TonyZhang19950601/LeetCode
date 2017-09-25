/**
 * Created by youzhang on 2017/9/25.
 */
public class MedianOfTwoSortedArray {
    //the following solution is in O(log(m)) time complexity, where m is the length of the shorter array
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int low1 = 0, high1 = nums1.length, half = (nums2.length+nums1.length+1)/2, m1 = 0, m2 = 0;
        while(low1<=high1){
            m1 = (low1+high1)/2;
            m2 = half-m1;
            if(m1<nums1.length&&nums2[m2-1]>nums1[m1]) low1 = m1+1;
            else if(m1>0&&nums1[m1-1]>nums2[m2]) high1 = m1-1;
            else break;
        }
        int maxLeft = 0;
        if(m1==0) maxLeft = nums2[m2-1];
        else if(m2==0) maxLeft = nums1[m1-1];
        else{
            maxLeft = Math.max(nums1[m1-1], nums2[m2-1]);
        }
        if((nums2.length+nums1.length)%2==1) return maxLeft;
        int minRight = 0;
        if(m1==nums1.length) minRight = nums2[m2];
        else if(m2==nums2.length) minRight = nums1[m1];
        else{
            minRight = Math.min(nums1[m1], nums2[m2]);
        }
        return ((double)maxLeft+(double)minRight)/2.0;
    }
}
