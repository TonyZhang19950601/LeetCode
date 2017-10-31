import java.util.Hashtable;

/**
 * Created by youzhang on 2017/10/17.
 */
public class EditDistance {
    //a classical DP problem. the general idea is to build a M*N matrix, moving down in the matrix indicates a delete, moving right in the matrix indicates a insert, moving diagonal indicates a replace.
    public int minDistance(String word1, String word2) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        table.values();
        int[] distances = new int[word2.length()+1];
        for(int i=0;i<distances.length;i++) {
            distances[i] = i;
        }
        for(int i=1;i<word1.length()+1;i++) {
            int[] newDistances = new int[word2.length()+1];
            newDistances[0] = i;
            for(int j=1;j<distances.length;j++) {
                int temp = distances[j-1]+1; //the distance of moving from previous diagonal element
                if(word1.charAt(i-1)==word2.charAt(j-1)) temp--; //if the two character at this position are same, than the cost will not increase
                newDistances[j] = Math.min(Math.min(newDistances[j-1]+1, distances[j]+1), temp); //the min cost of moving from up, left or diagoanl.
            }
            distances = newDistances;
        }
        return distances[word2.length()];
    }
}
