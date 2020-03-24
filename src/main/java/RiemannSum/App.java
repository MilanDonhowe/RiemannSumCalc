// Entry Point for Riemann Sum Calculator
// Author: Milan Donhowe
// Date: 3/24/2020

package RiemannSum;

import RiemannSum.sumpack.rsum.*;



public class App {

    public static void main(String[] args) {

        if (args.length == 5){

            String type = args[0];

            String functionExpression = args[1];
            int nSubdivisions = Integer.parseInt(args[2]);
            double startPoint = Double.parseDouble(args[3]);
            double endPoint = Double.parseDouble(args[4]);

            Integral result;

            if (type.equals("m")){
                result = new Midpoint(functionExpression, nSubdivisions, startPoint, endPoint);
            } else if (type.equals("t")){
                result = new Trapezoid(functionExpression, nSubdivisions, startPoint, endPoint);
            } else if (type.equals("l")){
                result = new Left(functionExpression, nSubdivisions, startPoint, endPoint);
            } else if (type.equals("r")){
                result = new Right(functionExpression, nSubdivisions, startPoint, endPoint);
            } else if (type.equals("s")){
                result = new Simpson(functionExpression, nSubdivisions, startPoint, endPoint);
            } else {
                result = null;
                System.out.println("ERROR: Invalid Riemann Sum Type!\nAccepted types: ('m', 's', 't', 'l', 'r')");
                System.exit(1);
            }
        
            System.out.println(result.calculate());
        } else {
            System.out.println("ERROR: Invalid number of arguments.");
            System.out.println("Correct usage: executable-name type expression n a b");
        }
    
    
    }
}
