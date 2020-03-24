/**
 * A hacky Math Expression Parser using the Shunting-yard Algorithm 
 * and Reverse Polish Notation expression evaluation.  It does not support trig functions 
 * nor include common constants like E or Pi at this time.
 */
package RiemannSum.sumpack.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Map;

public class Arithmetic {

    // Function divides expression into a list of tokens I.E. ("5+4") -> [5, +, 4]
    public static ArrayList<String> setupList (String expr){

        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("E");// token denotes empty list
        Set<Character> delimiters = new HashSet<Character>(Arrays.asList('+','-','*','/','^','(', ')', 'E'));
        int index = 0;
        String currentToken = "";
        while (index < expr.length()){
            char currentLetter = expr.charAt(index);
            if(delimiters.contains(currentLetter)){
                // Try and parse out negative numbers like -5 
                if (((currentLetter == '-') && (currentToken.isEmpty())) && 
                (delimiters.contains(tokens.get(tokens.size()-1).charAt(0)))){
                    currentToken += currentLetter;
                } else {
                    if (currentToken.length() > 0) tokens.add(currentToken);
                    tokens.add(""+currentLetter);
                    currentToken = "";
                }
            } else {
                currentToken += currentLetter;
            }
            index++;
        }

        if (currentToken != "") tokens.add(currentToken);

        tokens.remove(0); // remove "E" token
        
        // Loop used for debugging
        //for (String tok : tokens){
        //    System.out.println(tok);
        //}

        return tokens;
    }

    public static boolean isNUMBER(String tok){
        try {
            Double.parseDouble(tok);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // Uses Shunting-yard algorithm to take convert the token list into reverse Polish Notation
    public static Stack<String> polishNotation(ArrayList<String> expressionList){
        Stack<String> statements = new Stack<String>();
        Stack<String> operatorBuffer = new Stack<String>();

        Map<String, Integer> precedenceTable = new HashMap<String, Integer>();
        precedenceTable.put("+", 2);
        precedenceTable.put("-", 2);
        precedenceTable.put("*", 3);
        precedenceTable.put("/", 3);
        precedenceTable.put("^", 4);

        for (String token : expressionList){
            //if (Character.isDigit(token.charAt(0))){
            if (Arithmetic.isNUMBER(token)){
                statements.push(token);
            } else if (token.charAt(0) == '(') {
                operatorBuffer.push(token);
            } else if (token.charAt(0) == ')'){ 
                while(operatorBuffer.peek().charAt(0) != '('){
                    statements.push(operatorBuffer.pop());
                }
                operatorBuffer.pop();
            } else {
                // check operatorBuffer for existing operator and its precedence
                if ((operatorBuffer.size() > 0) && (operatorBuffer.peek().charAt(0) != '(')){
                    while (precedenceTable.get(operatorBuffer.peek()) >= precedenceTable.get(token)){
                        statements.push(operatorBuffer.pop());
                        if (operatorBuffer.isEmpty()) break;
                        else if (operatorBuffer.peek().charAt(0) == '(') break;
                    }
                    operatorBuffer.push(token);
                } else {
                    operatorBuffer.push(token);
                }
            }
        }

        while (operatorBuffer.isEmpty() == false){
            statements.push(operatorBuffer.pop());
        }

        return statements;
    }

    // Calculate the result of the formatted list of tokens
    public static double evaluatePolishNotation(Stack<String> polishList){
        Stack <String> value_stack = new Stack<String> ();
        for (String tok : polishList){
            if (Arithmetic.isNUMBER(tok)){
                value_stack.push(tok);
            } else {
                double arg1 = Double.parseDouble(value_stack.pop());
                double arg2 = Double.parseDouble(value_stack.pop());

                switch (tok){
                    case "+":
                        value_stack.push(Double.toString(arg1+arg2));
                        break;
                    case "-":
                        value_stack.push(Double.toString(arg2-arg1));
                        break;
                    case "*":
                        value_stack.push(Double.toString(arg2*arg1));
                        break;
                    case "/":
                        value_stack.push(Double.toString(arg2/arg1));
                        break;
                    case "^":
                        value_stack.push(Double.toString(Math.pow(arg2, arg1)));
                        break;
                }

            }
        }
        
        return Double.parseDouble(value_stack.get(0));
    }

    // Convenient function which combines above functions to evaluate an expression
    public static double eval(String expression){
        ArrayList<String> expressionList = Arithmetic.setupList(expression);
        Stack<String> expressionListFormatted = Arithmetic.polishNotation(expressionList);
        return Arithmetic.evaluatePolishNotation(expressionListFormatted);
    }

}