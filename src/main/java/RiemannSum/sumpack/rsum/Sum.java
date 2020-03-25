package RiemannSum.sumpack.rsum;

import RiemannSum.sumpack.arithmetic.Arithmetic;
/**Abstract Base Class for all Riemann Sum methods */
public abstract class Sum {
    protected int n;
    protected double a, b;
    protected String func;
    protected double dx;

    public Sum(String func, int n, double a, double b) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.func = func;
        this.dx = ((double)(b-a)) / ((double)n);
        System.out.println(dx);
    }

    protected double functionParse(Double x) {
        // High-tech function evaluation (string replacement ftw)
        String expression = func.replace("x", x.toString());
        //System.out.println(expression);
        return Arithmetic.eval(expression);
    }
    
    public abstract double calculate();
}