package RiemannSum.sumpack.rsum;


// This class class approximates an integral by using the midpoint rule.
public class Midpoint extends Sum implements Integral {

    public Midpoint(String func, int n, double a, double b) {
        super(func, n, a, b);
    }

    public double calculate(){
        double sum = 0.0d;
        for (double i = a; i < b; i += dx){
            double midpoint = (double)(i+i+dx)/2;
            sum += this.functionParse(midpoint)*dx;
        }
        return sum;
    }
}