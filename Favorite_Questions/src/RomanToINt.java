import java.util.Scanner;

/**
 * Created by youzhang on 2017/9/25.
 */
public class RomanToINt {
    public static void main(String args[]){
        System.out.println(romanToInt("MCDXXXVII"));
    }
    public static int romanToInt(String s) {
        int res = 0;
        if(s.contains("M")){
            for(int i=0;i<=s.lastIndexOf("M");i++){
                if(s.charAt(i)=='M') res+=1000;
                if(s.charAt(i)=='C') res-=100;
            }
            s = s.substring(s.lastIndexOf("M")+1);
        }
        if(s.contains("D")){
            res+=500;
            if(s.startsWith("C")) res-=100;
            s = s.substring(s.indexOf("D")+1);
        }
        if(s.contains("C")){
            for(int i=0;i<=s.lastIndexOf("C");i++){
                if(s.charAt(i)=='C') res+=100;
                if(s.charAt(i)=='X') res-=10;
            }
            s = s.substring(s.lastIndexOf("C")+1);
        }
        if(s.contains("L")){
            res+=50;
            if(s.startsWith("X")) res-=10;
            s = s.substring(s.indexOf("L")+1);
        }
        if(s.contains("X")){
            for(int i=0;i<=s.lastIndexOf("X");i++){
                if(s.charAt(i)=='X') res+=10;
                if(s.charAt(i)=='I') res-=1;
            }
            s = s.substring(s.lastIndexOf("X")+1);
        }
        if(s.contains("V")){
            res+=5;
            if(s.startsWith("I")) res-=1;
            s = s.substring(s.indexOf("V")+1);
        }
        for(int i=0;i<s.length();i++) res++;
        return res;
    }
}
