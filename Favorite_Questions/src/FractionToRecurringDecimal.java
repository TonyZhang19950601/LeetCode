import java.util.Hashtable;

/**
 * Created by youzhang on 2017/10/15.
 */
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator, den = denominator;
        boolean flag = true;
        if(num<0){
            num = -num;
            flag = !flag;
        }
        if(den<0) {
            den = -den;
            flag = !flag;
        }
        String pre = "";
        if(!flag&&num!=0) pre = "-";
        long part1 = num/den, part2 = num%den;
        Hashtable<Long, Integer> table = new Hashtable<Long, Integer>();
        table.put(part2, 0);
        StringBuilder sb = new StringBuilder();
        while(part2!=0) {
            long dec = part2*10/den;
            sb.append(dec);
            part2 = part2*10%den;
            if(table.get(part2)!=null) {
                sb.insert(table.get(part2), "(").append(")");
                break;
            }
            table.put(part2, sb.length());
        }
        if(sb.length()>0) return pre+part1+"."+sb.toString();
        return pre+part1;
    }
}
