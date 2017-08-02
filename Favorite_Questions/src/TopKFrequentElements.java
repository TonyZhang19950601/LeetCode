/**
 * Created by youzhang on 2017/8/2.
 */
import java.util.List;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class TopKFrequentElements {
    /*
    * this question is quite straight forward. There's several ways to solve this question
    * 1. Heap: time complexity O((n-k)log(k))
    * 2. BucketSort: time complexity O(n)
    * 3. quick select: time complexity O(n)
    * 4. TreeMap: time complexity O(n*log(n))
    * here I only implement the first two using heap and bucket sort.
    * */
    //solution with heap
    public List<Integer> topKFrequentWithHeap(int[] nums, int k) {
        Hashtable<Integer, Integer> countTable = new Hashtable<Integer, Integer>();
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for(int i:nums){
            if(countTable.get(i)==null){
                countTable.put(i, 1);
                keys.add(i);
            }
            else{
                int count = countTable.get(i);
                countTable.put(i, count+1);
            }
        }
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        for(int i:keys){
            int currentCount = countTable.get(i);
            if(heap.size()<k){
                heap.add(currentCount);
            }
            else{
                if(heap.peek()<currentCount){
                    heap.remove();
                    heap.add(currentCount);
                }
            }
        }
        int minCount = heap.poll();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i:keys){
            if(countTable.get(i)>=minCount){
                result.add(i);
            }
        }
        return result;
    }
    //solution with bucket sort
    public List<Integer> topKFrequentWithBuckets(int[] nums, int k) {
        Hashtable<Integer, Integer> countTable = new Hashtable<Integer, Integer>();
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for(int i:nums){
            if(countTable.get(i)==null){
                countTable.put(i, 1);
                keys.add(i);
            }
            else{
                int count = countTable.get(i);
                countTable.put(i, count+1);
            }
        }
        ArrayList[] buckets = new ArrayList[nums.length+1];
        for(int i:keys){
            if(buckets[countTable.get(i)]==null){
                buckets[countTable.get(i)] = new ArrayList<Integer>();
            }
            buckets[countTable.get(i)].add(i);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = buckets.length-1;i>=0&&result.size()<k;i--){
            if(buckets[i]!=null){
                result.addAll(buckets[i]);
            }
        }
        return result;
    }
}
