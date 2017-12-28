import sun.jvm.hotspot.utilities.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by youzhang on 2017/12/28.
 */
public class MeetingRoomII {
    //the thought is similar to the solution using heap. for every start event, see if there is an end event before it, if there is, it means there's vacant room for
    //the new event, else, add a new room for the new event.
    public int minMeetingRooms(IntervalX[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }

    //sort the interval according to the start time first, then try add them into a min heap, the heap are sorted on the end time of interval
    //if the peek element in heap ends before the current interval, it means there's a vacant room for the new interval, then remove the peek element
    //and add the new interval to the heap.
    //if the peek elements ends after the current interval starts, it means a new room is needed for the current event, thus, add the new interval
    //to the heap.
    public int solutionWithHeap(IntervalX[] intervals) {
        if(intervals==null||intervals.length==0)
            return 0;
        Arrays.sort(intervals, new Comparator<IntervalX>() {
            public int compare(IntervalX i1, IntervalX i2) {
                return i1.start-i2.start;
            }
        });
        PriorityQueue<IntervalX> heap = new PriorityQueue<IntervalX>(intervals.length+1, new Comparator<IntervalX>() {
            public int compare(IntervalX i1, IntervalX i2) {
                return i1.end-i2.end;
            }
        });
        heap.add(intervals[0]);
        for(int i = 1;i<intervals.length;i++) {
            IntervalX current = intervals[i];
            if(current.start>=heap.peek().end) {
                heap.poll();
            }
            heap.add(current);
        }
        return heap.size();
    }
}


class IntervalX {
    int start;
    int end;
    IntervalX() { start = 0; end = 0; }
    IntervalX(int s, int e) { start = s; end = e; }
}
