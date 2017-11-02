import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by youzhang on 2017/11/1.
 */
public class IsSubsequence {
    //O(n)
    public boolean isSubsequence(String s, String t) {
        int p1 = 0, p2 = 0;
        while(p1<s.length()&&p2<t.length()) {
            if(s.charAt(p1)==t.charAt(p2)) {
                p1++;
                p2++;
            }
            else{
                p2++;
            }
        }
        return p1==s.length();
    }

    //followup: what if there's many incoming s and we want to check all the s one by one?
    //use binary search
    //O(n) initialization, O(Mlog(n)) searching
    //this is a solution on leetcode
    public boolean isSubsequenceBinarySearch(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null) {
                idx[t.charAt(i)] = new ArrayList<>();
            }
            idx[t.charAt(i)].add(i);
        }

        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false; // Note: char of S does NOT exist in T causing NPE
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j < 0) j = -j - 1;
            if (j == idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
}
