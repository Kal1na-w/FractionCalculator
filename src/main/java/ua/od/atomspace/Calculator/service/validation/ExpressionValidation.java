package ua.od.atomspace.Calculator.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidation {

    public boolean fullValidation(String expression) {
        boolean valid;
        valid = charValidation(expression);
        if(!valid) {
            return false;
        }
        valid = bracketsValidation(expression);
        if(!valid) {
            return false;
        }
        valid = operationValidation(expression);
        if(!valid) {
            return false;
        }
        valid = fractionValidation(expression);
        return valid;
    }

    private boolean charValidation(String expression) {
        return expression.matches("[^@-\\[\\]-~!-',\\.;-?]+");
    }

    private boolean operationValidation(String expression) {
        Pattern patternOperation = Pattern.compile("\\d+\\)?\\++$");
        Matcher matcherOperation = patternOperation.matcher(expression);
        return !matcherOperation.find();
    }

    private boolean bracketsValidation(String expression) {
        int firstBracketIndex = expression.indexOf("(");
        int secondBracketIndex = expression.indexOf(")");
        for(int i = 0;i<expression.length();i++) {
            if (firstBracketIndex > secondBracketIndex){
                return false;
            }
            firstBracketIndex = expression.indexOf("(",firstBracketIndex+1);
            secondBracketIndex = expression.indexOf(")",secondBracketIndex+1);
        }
        Pattern patternFirstBrackets = Pattern.compile("\\(");
        Matcher matcherFirstBrackets = patternFirstBrackets.matcher(expression);
        int firstBracketsCount = 0;
        while (matcherFirstBrackets.find()) {
            firstBracketsCount++;
        }
        Pattern patternSecondBrackets = Pattern.compile("\\)");
        Matcher matcherSecondBrackets = patternSecondBrackets.matcher(expression);
        int secondBracketsCount = 0;
        while (matcherSecondBrackets.find()) {
            secondBracketsCount++;
        }
        return firstBracketsCount == secondBracketsCount;
    }

    private boolean fractionValidation(String expression) {
        expression = " " + expression + " ";
        Pattern patternMultistoryFraction = Pattern.compile("(\\d+\\/\\d+(\\/\\d+)+)");
        Matcher matcherMultistoryFraction = patternMultistoryFraction.matcher(expression);
        if(matcherMultistoryFraction.find()){
            return false;
        }

        Pattern patternEmptyNumerator = Pattern.compile("( \\/\\d+)");
        Matcher matcherEmptyNumerator = patternEmptyNumerator.matcher(expression);
        if(matcherEmptyNumerator.find()){
            return false;
        }

        Pattern patternEmptyDenominator = Pattern.compile("(\\d+\\/ )");
        Matcher matcherEmptyDenominator = patternEmptyDenominator.matcher(expression);
        return !matcherEmptyDenominator.find();
    }


}
