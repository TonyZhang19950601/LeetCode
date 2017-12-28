import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by youzhang on 2017/12/7.
 */
class DesignPhoneDirectory {
    HashSet<Integer> usedNumber;
    int maxNumber;
    LinkedList<Integer> freeList;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory(int maxNumbers) {
        maxNumber = maxNumbers;
        usedNumber = new HashSet();
        freeList = new LinkedList<Integer>();
        freeList.add(0);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(maxNumber==usedNumber.size())
            return -1;
        int res = freeList.poll();
        if(!usedNumber.contains(res+1))
            freeList.addFirst(res+1);
        return 0;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !usedNumber.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        usedNumber.remove(number);
        freeList.addFirst(number);
    }
}
