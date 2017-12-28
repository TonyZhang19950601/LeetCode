import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by youzhang on 2017/11/30.
 */
public class MedianOfDataStream {
    PriorityQueue<Integer> smallHeap;
    PriorityQueue<Integer> largeHeap;
    int total;
    /** initialize your data structure here. */
    public MedianOfDataStream() {
        total = 0;
        smallHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        largeHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        if(total==0)
            largeHeap.add(num);
        else {
            if(total%2==0) {
                if(num>=smallHeap.peek())
                    largeHeap.add(num);
                else {
                    largeHeap.add(smallHeap.poll());
                    smallHeap.add(num);
                }
            }
            else {
                if(num<=largeHeap.peek())
                    smallHeap.add(num);
                else {
                    smallHeap.add(largeHeap.poll());
                    largeHeap.add(num);
                }
            }
        }
        total++;
    }

    public double findMedian() {
        if(total%2==1)
            return largeHeap.peek();
        else
            return (largeHeap.peek()+smallHeap.peek())/2.0;
    }
}
