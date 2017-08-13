/**
 * Created by youzhang on 2017/8/13.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
public class KSmallestPairs {
    /**
     * this is an algorithm on leetcode, which has O(k*log(k)) time complexity.
     * The idea is to use a min-heap to track the next minimum pair.
     * for a number x_i in nums1, if the current pair is (x_i, y_j), the next pair contains x_i should be (x_i, y_j+1)
     * Thus, we first load (x_0, y_0), (x_1, y_0),...,(x_k, y_0) into the heap,
     * then we do the following until we have k pairs or m*n pairs in result list
     *      pop the top node (x_i, y_j) in the heap and put it into the result list, then add (x_i, y_j+1) into the heap if j+1<nums2.length
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(k, new Comparator<int[]>(){
            public int compare(int[] pair1, int[] pair2){
                return pair1[0]+pair1[1]-pair2[0]-pair2[1];
            }
        });
        if(nums1.length==0||nums2.length==0) return resultList;
        for(int i=0;i<k&&i<nums1.length;i++) heap.add(new int[]{nums1[i], nums2[0], 0});
        int limit = Math.min(k, nums1.length*nums2.length);
        while(resultList.size()<limit){
            int[] currentPair = heap.poll();
            resultList.add(new int[]{currentPair[0], currentPair[1]});
            if(currentPair[2]==nums2.length-1) continue;
            heap.add(new int[]{currentPair[0], nums2[currentPair[2]+1], currentPair[2]+1});
        }
        return resultList;
    }
}
