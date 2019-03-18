package ua.od.atomspace.parser;

import ua.od.atomspace.Fraction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FractionParser {

    public StringBuilder clearPlusMinus(StringBuilder expression) {
        Pattern patternFindPlusMinus = Pattern.compile("(\\+\\-)|(\\-\\-)");
        Matcher matcherPlusMinus = patternFindPlusMinus.matcher(expression);
        if(matcherPlusMinus.find()) {
            if(matcherPlusMinus.group(1) != null) {
                return clearPlusMinus(expression.replace(matcherPlusMinus.start(),matcherPlusMinus.end(),"-"));
            }
            else {
                return clearPlusMinus(expression.replace(matcherPlusMinus.start(),matcherPlusMinus.end(),"+"));
            }
        }
        else {
            return expression;
        }
    }

    public String checkPlus(Fraction resultFraction) {
        if(resultFraction.getDenominator() < 0 && resultFraction.getNumerator() < 0) {
            resultFraction.setDenominator(resultFraction.getDenominator()*-1);
            resultFraction.setNumerator(resultFraction.getNumerator()*-1);
            return "+"+resultFraction.toString();
        }
        else {
            return resultFraction.toString();
        }
    }

    public String clearSpaces(String expression) {
        return expression.replaceAll("\\s+","");
    }

    public Fraction parseFraction(String fraction) {
        Fraction resultFraction = new Fraction();
        if(fraction.matches("(\\-?\\d+\\/\\-?\\d+)")) {
            Pattern patternFraction = Pattern.compile("(\\-?\\d+)\\/(\\-?\\d+)");
            Matcher matcherFraction = patternFraction.matcher(fraction);
            while(matcherFraction.find()) {
                resultFraction.setNumerator(Integer.parseInt(matcherFraction.group(1)));
                resultFraction.setDenominator(Integer.parseInt(matcherFraction.group(2)));
            }
        }
        else {
            resultFraction.setNumerator(Integer.parseInt(fraction));
            resultFraction.setDenominator(1);
        }
        return resultFraction;
    }


    public Matcher parseBrackets(StringBuilder expression) {
        Pattern patternBrackets = Pattern.compile("\\((\\-?\\d*\\/?\\d+[+:\\-\\*]\\d*\\/?\\d+([+:\\-\\*]\\d*\\/?\\d+)*)\\)");
        return patternBrackets.matcher(expression);
    }
    public Matcher parseMultiplicationAndDivision(StringBuilder expression) {
        Pattern patternMultiplicationAndDivision = Pattern.compile("(\\-?\\d*\\/?\\d+)([:\\*])(\\-?\\d*\\/?\\d+)");
        return patternMultiplicationAndDivision.matcher(expression);
    }
    public Matcher parseSumAndSubtraction(StringBuilder expression) {
        Pattern patternSumAndSubtraction = Pattern.compile("(\\-?\\d*\\/?\\d+)([\\-+])(\\-?\\d*\\/?\\d+)");
        return patternSumAndSubtraction.matcher(expression);
    }

    public StringBuilder clearSingleBrackets(StringBuilder expression) {
        Pattern patternSingleBrackets = Pattern.compile("\\(((\\d+\\/)?\\d+)\\)");
        Matcher matcherSingleBrackets = patternSingleBrackets.matcher(expression);
        if(matcherSingleBrackets.find()) {
            return clearSingleBrackets(expression.replace(matcherSingleBrackets.start(),matcherSingleBrackets.end(),matcherSingleBrackets.group(1)));
        }
        else {
            return expression;
        }
    }

    public StringBuilder bracketsTransformation(StringBuilder expression) {
        Pattern patternTransform = Pattern.compile("(\\-\\(\\-(\\d+)\\))|(\\+\\(\\-(\\d+)\\))");
        Matcher matcherTransform = patternTransform.matcher(expression);
        if(matcherTransform.find()) {
            if(matcherTransform.group(1)!=null) {
                return bracketsTransformation(expression.replace(matcherTransform.start(),matcherTransform.end(),"+"+matcherTransform.group(2)));
            }
            else if (matcherTransform.group(3)!=null) {
                return bracketsTransformation(expression.replace(matcherTransform.start(),matcherTransform.end(),"-"+matcherTransform.group(4)));
            }
        }
        return expression;
    }
}
