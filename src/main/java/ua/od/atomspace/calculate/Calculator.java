package ua.od.atomspace.calculate;

import ua.od.atomspace.Fraction;
import ua.od.atomspace.parser.FractionParser;
import ua.od.atomspace.validation.ExpressionValidation;

import java.util.regex.Matcher;

public class Calculator {

    private final FractionParser fractionParser = new FractionParser();
    private final MathOperations mathOperations = new MathOperations();
    private final ExpressionValidation expressionValidation = new ExpressionValidation();


    public String calculate(String expression) {
        if(expressionValidation.fullValidation(expression)) {
            StringBuilder resultExpression = new StringBuilder(fractionParser.clearSpaces(expression.replaceAll("\\/+",":")));
            return countExpressionWithBrackets(resultExpression).toString();
        }
        else {
            return "No valid expression";
        }
    }

    private StringBuilder countExpressionWithBrackets(StringBuilder expression) {
        Matcher matcherBrackets = fractionParser.parseBrackets(expression);
        if(matcherBrackets.find()) {
            StringBuilder resultExpression = new StringBuilder(matcherBrackets.group(1));
            expression.replace(matcherBrackets.start(),matcherBrackets.end(),countFullExpression(resultExpression).toString());
            return countExpressionWithBrackets(expression);
        }
        else {
            return countFullExpression(expression);
        }
    }

    private StringBuilder countFullExpression(StringBuilder expression) {
        expression = fractionParser.clearPlusMinus(expression);
        expression = fractionParser.clearSingleBrackets(expression);
        expression = fractionParser.bracketsTransformation(expression);
        Matcher matcherMultiplicationAndDivision = fractionParser.parseMultiplicationAndDivision(expression);
        Matcher matcherSumAndSubtraction = fractionParser.parseSumAndSubtraction(expression);

        Fraction[] fractions = new Fraction[2];
        if(matcherMultiplicationAndDivision.find()) {
            return countOneExpression(expression, matcherMultiplicationAndDivision, fractions);
        }
        else if (matcherSumAndSubtraction.find()){
            return countOneExpression(expression, matcherSumAndSubtraction, fractions);
        }
        else {
            return expression;
        }
    }

    private StringBuilder countOneExpression(StringBuilder expression, Matcher matcher, Fraction[] fractions) {
        char operation;
        operation = matcher.group(2).charAt(0);
        fractions[0] = fractionParser.parseFraction(matcher.group(1));
        fractions[1] = fractionParser.parseFraction(matcher.group(3));
        Fraction resultFraction = mathOperations.countUp(fractions[0],fractions[1],operation);

        expression.replace(matcher.start(),matcher.end(),fractionParser.checkPlus(resultFraction));
        return countFullExpression(expression);
    }

}
