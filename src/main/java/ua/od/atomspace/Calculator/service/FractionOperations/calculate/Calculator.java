package ua.od.atomspace.Calculator.service.FractionOperations.calculate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

@Service
public class Calculator {
    private Stack<String> stackOperations = new Stack<>();
    private Stack<String> stackAnswer = new Stack<>();
    private MathOperations mathOperations = new MathOperations();
    private ExpressionValidation expressionValidation = new ExpressionValidation();


    private String operators = "+-*:";

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }
    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }
    private boolean isOperator(String token) {
        return operators.contains(token);
    }


    public String calculate(String expression) {
        if (expressionValidation.fullValidation(expression)) {
            return count(parse(expression));
        }
        else {
            return "No valid expression";
        }
    }


    private ArrayList<String> parse(String expression) {
        stackAnswer.clear();
        expression = expression.replace(" ", "").replace("(-", "(0-").replace("(+","(0+").replace("/",":");
        if (expression.charAt(0) == '-' || expression.charAt(0) == '+') {
            expression = "0" + expression;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(expression, operators + "()", true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isNumber(token)) {
                stackAnswer.push(token);
            }
            else if (isOpenBracket(token)) {
                stackOperations.push(token);
            }
            else if (isCloseBracket(token)) {
                while (!stackOperations.lastElement().equals("(")) {
                    stackAnswer.push(stackOperations.pop());
                }
                stackOperations.pop();
            }
            else if(isOperator(token)) {
                if (stackOperations.empty()) {
                    stackOperations.push(token);
                }
                else if((stackOperations.lastElement().equals("*") || stackOperations.lastElement().equals(":")) && (token.equals("+") || token.equals("-"))) {
                    stackAnswer.push(stackOperations.pop());
                    stackOperations.push(token);
                }
                else if ((stackOperations.lastElement().equals("-") && token.equals("+")) || (stackOperations.lastElement().equals(":") && token.equals("*"))) {
                    stackAnswer.push(stackOperations.pop());
                    stackOperations.push(token);
                }
                else {
                    stackOperations.push(token);
                }
            }
        }
        int counter = stackOperations.size();
        for (int i = 0; i < counter; i++) {
            stackAnswer.push(stackOperations.pop());
        }
        return new ArrayList<String>(Arrays.asList(stackAnswer.toArray(new String[stackOperations.size()])));
    }
    private String count(ArrayList<String> expression) {
        for (String token : expression) {
            if(isOperator(token)) {
                int index = expression.indexOf(token);
                Fraction answer = mathOperations.countUp(new Fraction(expression.get(index-2)),new Fraction(expression.get(index-1)),token);
                expression.set(index,answer.toString());
                expression.remove(index-1);
                expression.remove(index-2);
                return count(expression);
            }
        }
        return expression.get(0);
    }
}
