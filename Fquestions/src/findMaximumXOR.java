import java.util.HashSet;
import java.util.Set;

/**
 * Created by youzhang on 2017/9/25.
 */
public class findMaximumXOR {
    //solution on leetcode using hashmap
    //贪心算法，先看有没有1<<31,如果有，让最大值等于1<<31,
    //再看1<<30,看有没有11<<30,如果有，最大等于11<<30，否则还等于1<<31,以此类推
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
    //solution on leetcod using trie
    //首先构建字典树，字典树每一层代表一位，除root外，第一层代表1<<31位，以此类推
    //对每个元素，在字典树里找当前元素所能构成的最大异或值
    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }

    public int findMaximumXORWithTrie(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Trie root = new Trie();
        for(int num: nums) {
            Trie curNode = root;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            Trie curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }
}
