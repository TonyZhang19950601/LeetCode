import java.util.*;

/**
 * Created by youzhang on 2017/12/27.
 */
public class SkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<Point> eventList = new ArrayList<Point>();
        for(int[] building:buildings) {
            eventList.add(new Point(building[0], building[2], 1));
            eventList.add(new Point(building[1], building[2], -1));
        }
        Collections.sort(eventList, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if(p1.x!=p2.x)
                    return p1.x-p2.x;
                else
                    return p2.h*p2.e-p1.h*p1.e;
            }
        });
        TreeMap<Integer, Integer> heap = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        ArrayList<int[]> ans = new ArrayList<int[]>();
        heap.put(0, 1);
        for(Point event:eventList) {
            if(event.e==1) {
                if(event.h>heap.firstKey()) {
                    ans.add(new int[]{event.x, event.h});
                }
                if(heap.get(event.h)==null)
                    heap.put(event.h, 1);
                else
                    heap.put(event.h, heap.get(event.h)+1);
            }
            else {
                int maxH = heap.firstKey();
                if(heap.get(event.h)==1)
                    heap.remove(event.h);
                else
                    heap.put(event.h, heap.get(event.h)-1);
                if(event.h>heap.firstKey()) {
                    ans.add(new int[]{event.x, heap.firstKey()});
                }
            }
        }
        return ans;
    }
}

class Point {
    int x;
    int h;
    int e; // represents the event type, 1 represents start, -1 represents end
    public Point(int x, int h, int e) {
        this.x = x;
        this.h = h;
        this.e = e;
    }
}