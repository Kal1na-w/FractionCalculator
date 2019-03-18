package ua.od.atomspace.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidation {

    public boolean fullValidation(StringBuilder expression) {
        boolean valid;
        valid = charValidation(expression);
        if(!valid) {
            return false;
        }
        valid = bracketsValidation(expression);
        if(!valid) {
            return false;
        }
        valid = fractionValidation(expression);
        if(!valid){
            return false;
        }
        return true;
    }

    public boolean charValidation(StringBuilder expression) {
        return expression.toString().matches("[^@-\\[\\]-~!-',\\.;-?]+");
    }

    public boolean bracketsValidation(StringBuilder expression) {
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

    public boolean fractionValidation(StringBuilder expression) {
        addSpaces(expression);
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
        if(matcherEmptyDenominator.find()){
            return false;
        }

        return true;
    }

    private void addSpaces(StringBuilder expression) {
        expression.append(" ");
        expression.reverse();
        expression.append(" ");
        expression.reverse();
    }
}
