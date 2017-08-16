/**
 * Created by youzhang on 2017/8/16.
 */

/*this question is a typical application of reservoir sampling.
* the reservoir sampling algorithm is used to get k elements in a very large data streams and make sure every number in the stream
* has the same probability of being chosen.
* the mains steps of the algorithm is as below:
* 1. select the first k elements in the data stream
* 2. for following node n_k+i in stream, we pick a random p, if p<k/(k+i), we randomly pick a node from selected set
* and replace it with n_k+i.
* by doing so, we can guaruntee that every node has the same probability of being chosen.
* this question is a special case where k == 1;
* for example if we have(1,2,3,4) and k=3:
* we first load 1,2,3 into selected set.
* thus, p(n_4) = 3/4;
* p(n_1) = 1/4(p that 4 is not chosed) + 3/4*2/3(p that n_2/n_3 is removed from selected set) = 3/4.
* */
public class LinkedListRandomNode {
    ListNode head;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int count = 1;
        ListNode temp = head;
        int res = 0;
        while(temp!=null){
            double p = Math.random();
            if(p<=1.0/count){
                res = temp.val;
            }
            count++;
            temp = temp.next;
        }
        return res;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
