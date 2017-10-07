import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by youzhang on 2017/10/3.
 */
public class LetterCombinationOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> lastRes = new ArrayList<String>();
        if(digits==null||digits.contains("1")||digits.contains("0")||digits.length()==0) return lastRes;
        lastRes.add("");
        for(char c:digits.toCharArray()){
            ArrayList<String> cur = new ArrayList<String>();
            switch(c){
                case '7':{
                    for(String s:lastRes){
                        cur.add(s+"p");
                        cur.add(s+"q");
                        cur.add(s+"r");
                        cur.add(s+"s");
                    }
                    break;
                }
                case '8':{
                    for(String s:lastRes){
                        cur.add(s+"t");
                        cur.add(s+"u");
                        cur.add(s+"v");
                    }
                    break;
                }
                case '9':{
                    for(String s:lastRes){
                        cur.add(s+"w");
                        cur.add(s+"x");
                        cur.add(s+"y");
                        cur.add(s+"z");
                    }
                    break;
                }
                default:{
                    int m = c-'2';
                    for(String s:lastRes){
                        for(int i=0;i<3;i++){
                            cur.add(s+String.valueOf((char)('a'+m*3+i)));
                        }
                    }
                }
            }
            lastRes = cur;
        }
        return lastRes;
    }
}
