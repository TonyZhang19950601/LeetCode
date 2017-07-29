/**
 * Created by youzhang on 2017/7/29.
 */
import java.util.Arrays;
import java.util.Comparator;

public class MaximumProductOfWordLength {
    public int maxProduct(String[] words) {
        if(words.length<=1){
            return 0;
        }
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String word1, String word2){
                return word1.length()-word2.length();
            }
        });
        int[] bitArray = new int[words.length];
        for(int i:bitArray){
            i = 0;
        }
        for(int i=0;i<words.length;i++){
            for(char c:words[i].toCharArray()){
                bitArray[i]|=(1<<(c-'a'));
            }
        }

        return helper(0, words.length-1, words, bitArray);
    }

    public int helper(int start, int end, String[] words, int[] bitArray){
        int p = end-1;
        int result = 0;
        while(p>=start){
            if((bitArray[end]&bitArray[p])==0){
                result = Math.max(words[end].length()*words[p].length(), helper(p+1, end-1, words, bitArray));
                return result;
            }
            p--;
        }
        if(start<end-1){
            return helper(start, end-1, words, bitArray);
        }
        return 0;
    }
}
