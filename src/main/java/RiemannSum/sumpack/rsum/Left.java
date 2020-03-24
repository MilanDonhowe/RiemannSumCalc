package RiemannSum.sumpack.rsum;

// This class class approximates an integral by using the left-riemann sum rule.
public class Left extends Sum implements Integral{

    public Left(String func, int n, double a, double b) {
        super(func, n, a, b);
    }

    public double calculate() {
        double sum = 0.0d;
        for (double i=a; i < b; i += dx){
            sum += this.functionParse(i)*dx;
        }
        return sum;
    }
}