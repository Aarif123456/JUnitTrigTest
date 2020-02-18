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

    private static double getRemainder(double radian){
        radian %= 2* PI;
        if(radian<0)
            radian+=2*PI;
        return radian;
    }
    public static double sin(double radian){
        if(Double.isNaN(radian) || Double.isInfinite(radian)) // not a number returns not a number
            return Double.NaN;
        radian = getRemainder(radian);
        double start = radian; // series starts with itself
        double startNumerator = cube(radian); // x^3
        int denominatorNumber = 3; // stores the number the factorial goes up by
        return taylorSeries(radian, start, startNumerator, denominatorNumber);
    }

    // Function to calculate cos where input is given in radians
    public static double cos(double radian){
        if(Double.isNaN(radian) || Double.isInfinite(radian)) // not a number returns not a number
            return Double.NaN;
        radian = getRemainder(radian);
        double start = 1; // series starts with itself
        double startNumerator = square(radian); // x^3
        int denominatorNumber = 2; // 2!
        return taylorSeries(radian, start, startNumerator, denominatorNumber);
    }

    private static double taylorSeries(double radian, double output, double numerator, int denominatorNumber) {
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
        double denominator = cos(radian);
        radian = getRemainder(radian);
//        boolean negative = (radian >= 1.5*PI||(radian>=PI/2 && radian <=PI))? true: false;
//        if(abs(denominator)<=precision)
//            return (negative)?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY;
        double numerator = sin(radian);
        if(abs(numerator)<=precision)
            return 0;
        if(   Double.isNaN(denominator)
           || Double.isInfinite(denominator)
           || Double.isNaN(numerator)
           || Double.isInfinite(numerator)
           || denominator==0)
            return Double.NaN;


        return numerator/denominator;
    }

    public static double toRadians(double degrees){
        degrees %= 360;
        if(degrees<0)
            degrees+=360;
        return degrees * degree2Radian;
    }
}


/* deprecated tan - less accurate
        if(abs(numerator-denominator)<=0.00000001)
            return (negative)?-1:1;
        radian %=  PI/2;
        double bernoulli;  // bernoulli number
        double output = radian; // series start with itself
        double numeratorTerm =-4; // term in numerator stats with (-4
        double numeratorXTerm = radian; // x terms that goes exponentially up by 2 -> x^(2n-1)
        double denominatorNumber = 2;
        denominator = 2;
        double difference =2;
        double lastOutput= output +2;
        int i=1;
        while(!Double.isInfinite(numerator) && !Double.isInfinite(denominator) &&
                (++i)<bernoulliEvenTerms.length && difference>=precision) {
            difference = abs(abs(lastOutput) - abs(output));
            lastOutput = output;
            bernoulli =  bernoulliEvenTerms[i];
            numeratorTerm *= (-4);  //(-4)^n
            numerator = bernoulli * numeratorTerm * (1-abs(numeratorTerm)) *numeratorXTerm;
            denominator *= (denominatorNumber+1) * (denominatorNumber+2);
            denominatorNumber += 2;
            output += numerator/denominator;
        }
        System.out.println("difference "+ difference);
        output = abs(output);
        double numerator = sin(radian), denominator = cos(radian);
        if(negative)
            return -output;
        return output;
 */