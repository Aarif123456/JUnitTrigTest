// Abdullah Arif
// February 24, 2020
// Program to calculate sin, cos and tan given in radians

public class Trignometry {
    public static final double PI = 3.14159265358979323846;
    private static final double degree2Radian = 0.017453292519943295; // PI/180
    private static final double precision = 0.000000000000001; // the precision for the answer

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
    private static int factorial(int a){
        int output = 1;
        for(int i=2; i<=a; i++)
            output *= i;
        return output;
    }
    public static double sin(double radian){
        if(Double.isNaN(radian) || Double.isInfinite(radian)) // not a number returns not a number
            return Double.NaN;
        radian %= 2* PI;
        double start = radian; // series starts with itself
        double startNumerator = cube(radian); // x^3
        int denominatorNumber = 3; // stores the number the factorial goes up by
        return TaylorSeries(radian, start, startNumerator, denominatorNumber);
    }

    // Function to calculate cos where input is given in radians
    public static double cos(double radian){
        if(Double.isNaN(radian) || Double.isInfinite(radian)) // not a number returns not a number
            return Double.NaN;
        radian %= 2* PI;
        double start = 1; // series starts with itself
        double startNumerator = square(radian); // x^3
        int denominatorNumber = 2; // 2!
        return TaylorSeries(radian, start, startNumerator, denominatorNumber);
    }

    private static double TaylorSeries(double radian, double output, double numerator, int denominatorNumber) {
        double lastOutput = output+2, difference = 2, denominator = factorial(denominatorNumber);
        boolean add = true;
        while(!Double.isInfinite(numerator) && !Double.isInfinite(denominator) && numerator != 0.0&& difference>=precision) {
            difference = abs(abs(lastOutput) - abs(output));
            lastOutput = output;
            if(add)
                output -= (numerator/denominator);
            else
                output += (numerator/denominator);
            add = !add;
            // the exponent in the numerator increases by 2 -> (a^n) *(a^2) = a^(n+2)
            numerator *= square(radian);
            denominator *= (denominatorNumber+1) * (denominatorNumber+2);
            denominatorNumber += 2;
        }
        return output;
    }

    // Function to calculate tan where input is given in radians
    public static double tan(double radian){
        if(Double.isNaN(radian) || Double.isInfinite(radian)) // not a number returns not a number
            return Double.NaN;
        double numerator = sin(radian), denominator = cos(radian);
        if(   Double.isNaN(denominator)
           || Double.isInfinite(denominator)
           || Double.isNaN(numerator)
           || Double.isInfinite(numerator)
           || denominator==0)
            return Double.NaN;
        return numerator/denominator;
    }

    public static double toRadians(double degrees){
        return degrees * degree2Radian;
    }
}
