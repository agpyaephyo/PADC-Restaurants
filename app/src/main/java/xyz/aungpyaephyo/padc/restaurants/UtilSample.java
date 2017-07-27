package xyz.aungpyaephyo.padc.restaurants;

/**
 * Created by aung on 7/22/17.
 */

public class UtilSample {

    private int param1;

    private static UtilSample objInstance;

    private UtilSample() {
        param1 = 2;
    }

    public static UtilSample getInstance() {
        if(objInstance == null) {
            objInstance = new UtilSample();
        }

        return objInstance;
    }

    public int complexCalculation(int param2) {
        int result = param1 * param2;
        if(result > 50) {
            result += 5;
        }

        return result;
    }
}
