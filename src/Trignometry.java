// Abdullah Arif
// February 24, 2020
// Program to calculate sin, cos and tan given in radians

public class Trignometry {
    public static final double PI = 3.14159265358979323846;
    private static final double degree2Radian = 0.017453292519943295; // PI/180
    private static final double precision = 0.00000001; // the precision for the answer

    // Function to calculate sin where input is in radians
    private static double square(double a){
        return a*a;
    }
    private static double cube(double a){
        return a*a*a;
    }
    private static double abs(double a){
        return  (a>=0)?a:-a;
    }
    public static double sin(double radian){
        if(Double.isNaN(radian)) // not a number returns not a number
            return Double.NaN;
        radian %= 2* PI;
        double output = radian; // series starts with itself
        double lastOutput = radian +2; // add some distance between previous put
        double difference;
        double numerator = cube(radian); // x^3
        double denominator = 3*2; // 3!
        int factorialNum = 3; // stores the number the factorial goes up by
        for(int i=0; i<200; i++){
            if(Double.isInfinite(numerator) || Double.isInfinite(denominator) || numerator == 0.0) break;
            difference = abs(lastOutput) - abs(output);
            if(difference>=-precision && difference<=precision)
                return output;
            lastOutput = output;
            if(i%2 ==0)
                output -= (numerator/denominator);
            else
                output += (numerator/denominator);
            numerator *= square(radian); // the exponent in the numerator increases by 2 -> (a^n) *(a^2) = a^(n+2)
            denominator *= (factorialNum+1) * (factorialNum+2);
            factorialNum += 2;
        }
        return output;
    }

    // Function to calculate cos where input is given in radians
    public static double cos(double radian){
        if(Double.isNaN(radian))
            return Double.NaN;

        return radian;
    }

    // Function to calculate tan where input is given in radians
    public static double tan(double radian){
        if(Double.isNaN(radian))
            return Double.NaN;
        return radian;
    }

    public static double toRadians(double degrees){
        return degrees * degree2Radian;
    }
}
