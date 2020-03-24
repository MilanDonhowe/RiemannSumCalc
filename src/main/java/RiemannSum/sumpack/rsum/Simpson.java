package RiemannSum.sumpack.rsum;

// This class uses Simpson's Rule, it's the most accurate out of the other rules.
public class Simpson extends Sum implements Integral{
    public Simpson(String func, int n, double a, double b){
        super(func, n, a, b);
    }

    public double calculate(){
        double sum = 0.0d;
        for (double i = a; i < b-dx; i += 2*dx){
            sum = sum + this.functionParse(i) + (4*this.functionParse(i+dx)) + this.functionParse(i+2*dx);
        }
        return (dx/3)*sum;
    }
}