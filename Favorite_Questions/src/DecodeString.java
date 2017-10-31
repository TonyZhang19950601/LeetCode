import java.util.Stack;

/**
 * Created by youzhang on 2017/10/14.
 */
public class DecodeString {
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<String> sStack = new Stack<String>();
        int start = 0;
        int i=0;
        while(i<chars.length) {
            char c = chars[i];
            if(c<='9'&&c>='0'){
                int j = i+1;
                for(;j<chars.length;j++) {
                    if(chars[j]<'0'||chars[j]>'9') break;
                }
                int num = Integer.valueOf(s.substring(i,j));
                stack.push(num);
                i = j;
            }
            else if(c=='[') {
                sStack.push("[");
                i++;
            }
            else if(c==']'){
                int temp = stack.pop();
                StringBuilder sb = new StringBuilder();
                String temps = sStack.pop();
                while(!temps.equals("["))
                {
                    sb.insert(0, temps);
                    temps = sStack.pop();
                }
                temps = sb.toString();
                sb = new StringBuilder();
                for(int m=0;m<temp;m++){
                    sb.append(temps);
                }
                sStack.push(sb.toString());
                i++;
            }
            else{
                int j = i+1;
                while(j<chars.length) {
                    if(chars[j]==']'||chars[j]>='0'||chars[j]<='9') break;
                    j++;
                }
                sStack.push(s.substring(i,j));
                i = j;
            }

        }
        StringBuilder sb = new StringBuilder();
        while(!sStack.isEmpty()) sb.insert(0, sStack.pop());
        return sb.toString();
    }
}
