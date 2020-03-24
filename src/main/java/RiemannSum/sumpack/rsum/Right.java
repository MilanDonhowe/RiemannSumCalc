package RiemannSum.sumpack.rsum;

// This class approximates the integral with the right riemann sum rule
public class Right extends Sum implements Integral{

    public Right(String func, int n, double a, double b) {
        super(func, n, a, b);
    }

    public double calculate() {
       double sum = 0.0d;
       for (double i=a+dx; i < b+dx; i += dx){
           sum += this.functionParse(i)*dx;
       } 
       return sum;
    }
}