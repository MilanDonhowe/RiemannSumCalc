package RiemannSum.sumpack.rsum;

// This uses the Trapezoid rule to approximate an integral
public class Trapezoid extends Sum implements Integral{
    public Trapezoid(String func, int n, double a, double b){
        super(func, n, a, b);
    }

    public double calculate(){
        double sum = 0.0d;
        for (double i=a; i < b; i+=dx){
            sum = sum + this.functionParse(i) + this.functionParse(i+dx);
        }
        return (dx/2)*sum;
    }
}