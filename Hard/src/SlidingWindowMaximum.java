import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by youzhang on 2017/11/17.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null)
            return null;
        if(nums.length==0)
            return new int[]{};
        int[] res = new int[nums.length-k+1];
        Deque<Integer> deQueue = new ArrayDeque<Integer>();
        for(int i = 0;i<k;i++) {
            while(deQueue.size()>0) {
                if(nums[deQueue.peek()]<=nums[i])
                    deQueue.poll();
                else
                    break;
            }
            deQueue.push(i);
        }
        res[0] = nums[deQueue.peekLast()];
        for(int i = 1;i+k<=nums.length;i++) {
            if(deQueue.peekLast()<i)
                deQueue.pollLast();
            while(deQueue.size()>0) {
                if(nums[deQueue.peek()]<=nums[i+k-1])
                    deQueue.poll();
                else
                    break;
            }
            deQueue.push(i+k-1);
            res[i] = nums[deQueue.peekLast()];
        }
        return res;
    }
}
