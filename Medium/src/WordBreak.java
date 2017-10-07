import java.util.HashSet;
import java.util.List;

/**
 * Created by youzhang on 2017/9/30.
 */
public class WordBreak {
    //a solution using DP
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<String>();
        dict.addAll(wordDict);
        int[] res = new int[s.length()+1];
        for(int i=0;i<res.length;i++){
            res[i] = 0;
        }
        res[s.length()] = 1;
        return helper(s, dict, 0, res);
    }
    public boolean helper(String s, HashSet<String> dict, int start, int[] res){
        if(start>=s.length()) return true;
        for(int i=start+1;i<=s.length();i++){
            if(dict.contains(s.substring(start, i))){
                if(res[i]!=0){
                    if(res[i]==1) return true;
                }
                else{
                    if(helper(s, dict, i, res)) {
                        res[start] = 1;
                        return true;
                    }
                }
            }
        }
        res[start] = -1;
        return false;
    }
}
