/**
 * Created by youzhang on 2017/8/5.
 */
public class WaterAndJugProblem {
    /*
    * This question is in fact to figure out if z can be divised by the maximum common divisor of x and y.
    * */
    public boolean canMeasureWater(int x, int y, int z) {
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        while(min>1&&min!=max){
            int temp = max%min;
            max = min;
            min = temp;
        }
        if(min==0&&max==0){
            return z==0;
        }
        if(min==0){
            if(z%max==0){
                return true;
            }
            return false;
        }
        if(z%min==0&&z<=x+y){
            return true;
        }
        return false;
    }
}
