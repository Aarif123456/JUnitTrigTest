import java.lang.Math; // For testing our class
import static org.junit.Assert.assertEquals; // Used to test the case
import static org.junit.Assert.fail; // Used during development
import org.junit.Test; // Used by JUnit to set up cases
import org.junit.runner.RunWith; // Used to run parametrized class
import org.junit.runners.Parameterized; // Used to create parameters for test
import java.util.*; // Used to set up test cases in arrays

@RunWith(Parameterized.class)
public class TrigonometryTest {
     private static final double precision = 0.0000001; // lower than the precision in the Trigonometry class
     private final Double inputNumber;
     private final Boolean isRadian;
    // Set up arguments for test - so, the test case and if it is in degree or in radians
    public TrigonometryTest(Double inputNumber, Boolean isRadian) {
        this.inputNumber = inputNumber;
        this.isRadian = isRadian;
    }

    // Set up the actual test cases
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        List<Object[]> testCases = new ArrayList<>();
        // basic test to test all quadrant points for: negative value, wrap around and boundaries, all quadrant using radians
        for(double a = -Math.PI*4; a <= Math.PI*2; a += Math.PI/4)
            testCases.add(new Object[] {a, true});
        // add in the tests for degrees
        double[] degreeTestCases = {0, // 0 is a boundary case - as the range of sin and tan
                33,            // point in the first quadrant (partition)
                90,            // This is undefined for tan so it should throw an NaN
                117,           // point in second quadrant (partition)
                180,           // boundary for cos
                180.0001,      // super close to zero for sin
                199.54,        // point in third quadrant but is fractional (partition)
                270,           // much like 90 may break tan also boundary for sin
                324,           // point in third quadrant (partition)
                371.64,        // point in first quadrant after it wraps around (partition)
                90+360,        // same as 90 but wrap around  (partition for wrapped )
                164+(360*3),   // check second quadrant after wrapping
                269.99999+360, // third quadrant but comes super close to undefined tan
                355+(360*80),  // fourth quadrant wrap around
                -20,           // fourth quadrant but using negatives
                -90 - (360*4), // wrap around with 90
                -115 - (360*4),// negative value using third quadrant
                -180,          // boundary check but using negative version of 180
                -190 - (360*200), // second quadrant negative
                -280.7436 - (360*99999) // fraction and negative first quadrant
        };
        // Add in degree test cases using the array
        for(double a: degreeTestCases)
            testCases.add(new Object[] {a, false});
        return testCases;

    }

    @Test
    public void sinTest(){
        if(isRadian)
            assertEquals(Math.sin(inputNumber), Trignometry.sin(inputNumber), precision);
        else
            assertEquals(Math.sin(Math.toRadians(inputNumber)), Trignometry.sin(Trignometry.toRadians(inputNumber)), precision);
        System.out.println("Passed for " + inputNumber);
    }

    @Test
    public void cosTest(){
        fail("Make the cos function");
        if(isRadian)
            assertEquals(Math.cos(inputNumber), Trignometry.cos(inputNumber), precision);
        else
            assertEquals(Math.cos(Math.toRadians(inputNumber)), Trignometry.cos(Trignometry.toRadians(inputNumber)), precision);
        System.out.println("Passed for " + inputNumber);
    }

    @Test
    public void tanTest(){
        fail("Make the tan function");
        if(isRadian)
            assertEquals(Math.tan(inputNumber), Trignometry.tan(inputNumber), precision);
        else
            assertEquals(Math.tan(Math.toRadians(inputNumber)), Trignometry.tan(Trignometry.toRadians(inputNumber)), precision);
        System.out.println("Passed for " + inputNumber);
    }
}