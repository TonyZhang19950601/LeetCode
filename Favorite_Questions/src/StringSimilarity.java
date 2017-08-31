/**
 * Created by youzhang on 2017/8/30.
 */
public class StringSimilarity {
    /*
    * Question: find the sum of maximum common prefix length between string s and all suffix of s(including s itself)
    * Idea:
    * with z algorithm, the question can be solved in O(n) time.
    * The idea is to maintain an interval [L, R] which is the interval with max R
    * such that [L,R] is prefix substring (substring which is also prefix).
    *
    * Steps for maintaining this interval are as follows –
    *   1) If i > R then there is no prefix substring that starts before i and
         ends after i, so we reset L and R and compute new [L,R] by comparing
         str[0..] to str[i..] and get Z[i] (= R-L+1).
        2) If i <= R then let K = i-L,  now Z[i] >= min(Z[K], R-i+1)  because
            str[i..] matches with str[K..] for atleast R-i+1 characters (they are in
            [L,R] interval which we know is a prefix substring).
            Now two sub cases arise –
                a) If Z[K] < R-i+1  then there is no prefix substring starting at
                str[i] (otherwise Z[K] would be larger)  so  Z[i] = Z[K]  and
                interval [L,R] remains same.
                b) If Z[K] >= R-i+1 then it is possible to extend the [L,R] interval
                thus we will set L as i and start matching from str[R]  onwards  and
                get new R then we will update interval [L,R] and calculate Z[i] (=R-L+1).
    * an animation of this process can be found at http://www.utdallas.edu/~besp/demo/John2010/z-algorithm.htm
    * */
    public int stringSimilarity(String s){
        int r = 0, l = 0;
        int[] zArray = new int[s.length()];
        for(int j=1;j<s.length();j++){
            if(j>r){
                l = j;
                int m=0;
                for(;m+j<s.length();m++){
                    if(s.charAt(m)!=s.charAt(m+j)){
                        break;
                    }
                }
                r = m+j-1;
                zArray[j] = m;
            }
            else{
                if(zArray[j-l]<r-j+1) {
                    zArray[j] = zArray[j-l];
                }
                else{
                    l=j;
                    int m = r-l+1;
                    for(;m+l<s.length();m++){
                        if(s.charAt(m)!=s.charAt(m+l)){
                            break;
                        }
                    }
                    r = l+m-1;
                    zArray[j] = r-j+1;
                }
            }
        }
        int res = 0;
        for(int z:zArray){
            res+=z;
        }
        return res;
    }
}
