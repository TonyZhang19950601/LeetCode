/**
 * Created by youzhang on 2017/8/15.
 */
import java.util.PriorityQueue;
import java.util.Comparator;
public class KthSmallestElementInaSortedMatrix {
    /*
    * Solution with Heap
    * The idea is to load the first element of each row into a min-heap,
    * every time we pop the top number in the heap, and put the next number in the same row of the top number into the heap.
    * the time complexity should be O(n+k*log(n))
    * */
    public int solutionWithHeap(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(matrix.length+1, new Comparator<int[]>(){
            public int compare(int[] num1, int[] num2){
                return num1[0]-num2[0];
            }
        });
        for(int i=0;i<matrix.length;i++){
            heap.add(new int[]{matrix[i][0], i, 0});
        }
        for(int i=1;i<k;i++){
            int[] current = heap.poll();
            if(current[2]==matrix.length-1) continue;
            heap.add(new int[]{matrix[current[1]][current[2]+1], current[1], current[2]+1});
        }
        return heap.poll()[0];
    }

    /*
    * this is a solution on leetcode using Binary search.
    * we use the number range(max_number -> matrix[n-1][n-1] - min_number -> matrix[0][0]) as the search space.
    * the time complexity is O(n*log(m-n))
    * */
    public int solutionWithBinarySearch(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            //Note, the time comlexity here is O(n), not O(n^2)
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
