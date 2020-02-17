import org.junit.Test;
import java.lang.Math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrignometryTest {
    private static double[] degreeTestCases = {0,                // 0 is a boundary case
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

    @Test
    public void testSetup() {
        this.sinTest();
        this.cosTest();
        this.tanTest();
    }

    public void sinTest(){
        fail("Make the sin function");
        // basic test to test all quadrant points for: negative value, wrap around and boundaries, all quadrant using radians
        for(double a = -Math.PI*4; a <= Math.PI*2; a += Math.PI/4)
            assertEquals(Math.sin(a), Trignometry.sin(a), 0.000001);
        for( double a: degreeTestCases) assertEquals(Math.sin(Math.toRadians(a)), Trignometry.sin(Trignometry.toRadians(a)), 0.0000001);
    }

    public void cosTest(){
        fail("Make the cos function");
        // basic test to test all quadrant points for: negative value, wrap around and boundaries, all quadrant using radians
        for(double a = -Math.PI*4; a <= Math.PI*2; a += Math.PI/4)
            assertEquals(Math.cos(a), Trignometry.cos(a), 0.000001);
        for( double a: degreeTestCases) assertEquals(Math.cos(Math.toRadians(a)), Trignometry.cos(Trignometry.toRadians(a)), 0.0000001);
    }

    public void tanTest(){
        fail("Make the tan function");
        // basic test to test all quadrant points for: negative value, wrap around and boundaries, all quadrant using radians
        for(double a = -Math.PI*4; a <= Math.PI*2; a += Math.PI/4)
            assertEquals(Math.tan(a), Trignometry.tan(a), 0.000001);
        for( double a: degreeTestCases) assertEquals(Math.tan(Math.toRadians(a)), Trignometry.tan(Trignometry.toRadians(a)), 0.0000001);
    }
}