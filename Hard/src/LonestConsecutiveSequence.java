/**
 * Created by youzhang on 2017/10/11.
 */
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Iterator;
public class LonestConsecutiveSequence {
    public static void main(String[] args){
        longestConsecutive(new int[]{100,4,200,1,3,2});
    }
    //LeetCode top Solution
    /*public int longestConsecutive(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }*/
    //own solution using Union-Find
    public static int longestConsecutive(int[] nums) {
        Hashtable<Integer, Integer> sizeTable = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> rootTable = new Hashtable<Integer, Integer>();
        //for(int i=0;i<nums.length;i++){
        //  table.put(nums[i], -1);
        // }
        for(int i=0;i<nums.length;i++){
            if(sizeTable.get(nums[i])!=null) continue;
            sizeTable.put(nums[i], 1);
            rootTable.put(nums[i],nums[i]);
            if(sizeTable.get(nums[i]-1)!=null){
                int root1 = nums[i];
                while(root1!=rootTable.get(root1)) root1 = rootTable.get(root1);
                int root2 = nums[i]-1;
                while(root2!=rootTable.get(root2)) root2 = rootTable.get(root2);
                if(sizeTable.get(root1)>sizeTable.get(root2)){
                    sizeTable.put(root1, sizeTable.get(root1)+sizeTable.get(root2));
                    rootTable.put(root2, root1);
                }
                else{
                    sizeTable.put(root2, sizeTable.get(root1)+sizeTable.get(root2));
                    rootTable.put(root1, root2);
                }
            }
            if(sizeTable.get(nums[i]+1)!=null){
                int root1 = nums[i];
                while(root1!=rootTable.get(root1)) root1 = rootTable.get(root1);
                int root2 = nums[i]+1;
                while(root2!=rootTable.get(root2)) root2 = rootTable.get(root2);
                if(sizeTable.get(root1)>sizeTable.get(root2)){
                    sizeTable.put(root1, sizeTable.get(root1)+sizeTable.get(root2));
                    rootTable.put(root2, root1);
                }
                else{
                    sizeTable.put(root2, sizeTable.get(root1)+sizeTable.get(root2));
                    rootTable.put(root1, root2);
                }
            }
        }
        Iterator<Entry<Integer, Integer>> it = sizeTable.entrySet().iterator();
        int max = 0;
        while(it.hasNext()){
            Entry<Integer, Integer> current = it.next();
            max = Math.max(current.getValue(), max);
        }
        return max;
    }
}
