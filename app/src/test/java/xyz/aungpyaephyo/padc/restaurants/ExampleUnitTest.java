package xyz.aungpyaephyo.padc.restaurants;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 3);
        assertEquals(UtilSample.getInstance().complexCalculation(3), 6);
    }

    @Test
    public void complexCalculationTest2() {
        assertEquals(UtilSample.getInstance().complexCalculation(15), 75);
    }
}