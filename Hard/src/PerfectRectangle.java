import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by youzhang on 2017/12/27.
 */
public class PerfectRectangle {
    //if the given rectangles forms a rectangle cover, the following two conditions should be met:
    //1. the area of the small rectangles sum to equal the large rectangle determined by minx, miny, maxx, maxy
    //2. there's no overlap between the small rectangles. To ensure that, every point within the laarge rectangle should appear
    //even times and the 4 point on the corner can only occur once.
    public boolean solution1(int[][] rectangles) {
        int minx = Integer.MAX_VALUE;
        int miny = minx;
        int maxx = -1;
        int maxy = -1;
        int totalArea = 0;
        HashSet<String> set = new HashSet<String>();
        for(int[] rectangle:rectangles) {
            totalArea+=(rectangle[2]-rectangle[0])*(rectangle[3]-rectangle[1]);
            minx = Math.min(rectangle[0], minx);
            miny = Math.min(rectangle[1], miny);
            maxx = Math.max(maxx, rectangle[2]);
            maxy = Math.max(maxy, rectangle[3]);
            String s1 = rectangle[0]+" "+rectangle[1];
            String s2 = rectangle[0]+" "+rectangle[3];
            String s3 = rectangle[2]+" "+rectangle[1];
            String s4 = rectangle[2]+" "+rectangle[3];
            if(!set.add(s1)) set.remove(s1);
            if(!set.add(s2)) set.remove(s2);
            if(!set.add(s3)) set.remove(s3);
            if(!set.add(s4)) set.remove(s4);
        }
        String s1 = minx+" "+miny;
        String s2 = minx+" "+maxy;
        String s3 = maxx+" "+miny;
        String s4 = maxx+" "+maxy;
        if(set.size()!=4||!set.contains(s1)||!set.contains(s2)||!set.contains(s3)||!set.contains(s4))
            return false;
        return (maxx-minx)*(maxy-miny)==totalArea;
    }
    //swap line solution
    //Sort by x-coordinate.
    //Insert y-interval into TreeSet, and check if there are intersections.
    //Delete y-interval.
    public boolean isRectangleCover(int[][] rectangles) {
        PriorityQueue<Event> pq = new PriorityQueue<Event> ();
        // border of y-intervals
        int[] border= {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int[] rect : rectangles) {
            Event e1 = new Event(rect[0], rect);
            Event e2 = new Event(rect[2], rect);
            pq.add(e1);
            pq.add(e2);
            if (rect[1] < border[0]) border[0] = rect[1];
            if (rect[3] > border[1]) border[1] = rect[3];
        }
        TreeSet<int[]> set = new TreeSet<int[]> (new Comparator<int[]>() {
            @Override
            // if two y-intervals intersects, return 0
            public int compare (int[] rect1, int[] rect2) {
                if (rect1[3] <= rect2[1]) return -1;
                else if (rect2[3] <= rect1[1]) return 1;
                else return 0;
            }
        });
        int yRange = 0;
        while (!pq.isEmpty()) {
            int time = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == time) {
                Event e = pq.poll();
                int[] rect = e.rect;
                if (time == rect[2]) {
                    set.remove(rect);
                    yRange -= rect[3] - rect[1];
                } else {
                    if (!set.add(rect)) return false;
                    yRange += rect[3] - rect[1];
                }
            }
            // check intervals' range
            if (!pq.isEmpty() && yRange != border[1] - border[0]) {
                return false;
                //if (set.isEmpty()) return false;
                //if (yRange != border[1] - border[0]) return false;
            }
        }
        return true;
    }
}

class Event implements Comparable<Event> {
    int time;
    int[] rect;

    public Event(int time, int[] rect) {
        this.time = time;
        this.rect = rect;
    }

    public int compareTo(Event that) {
        if (this.time != that.time) return this.time - that.time;
        else return this.rect[0] - that.rect[0];
    }
}
