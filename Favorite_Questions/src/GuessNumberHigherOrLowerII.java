/**
 * Created by youzhang on 2017/8/14.
 */
public class GuessNumberHigherOrLowerII {
    /*
    * this is an algorithm on leetcode.
    * the general idea is to use DP and minimax.
    * We assume every time our guess is wrong, until there's only one correct choice for us.
    * Thus, for every element in 1 to n, our cost to guess i is i+max(max_cost(start, i-1), max_cost(i+1,end)).
    * So, we iterate from i to n and find the element who has the minimum max cost.
    * */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                dp[i][j] = 0;
            }
        }
        return helper(dp, 1, n);
    }

    public int helper(int[][] dp, int start, int end){
        if(start>=end) return 0;
        if(dp[start][end]!=0) return dp[start][end];
        int res = Integer.MAX_VALUE;
        for(int i=start;i<=end;i++){
            int cur = i+Math.max(helper(dp, start, i-1), helper(dp, i+1, end));
            if(cur<res){
                res = cur;
            }
        }
        dp[start][end] = res;
        return dp[start][end];
    }
}
