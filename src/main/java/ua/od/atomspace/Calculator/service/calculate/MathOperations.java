package ua.od.atomspace.Calculator.service.calculate;


import ua.od.atomspace.Calculator.service.Fraction;

public class MathOperations {

    public Fraction countUp(Fraction firstFraction, Fraction secondFraction,char operation) {

        switch (operation) {
            case '+':
                return sum(firstFraction, secondFraction);
            case '-':
                return subtraction(firstFraction, secondFraction);
            case ':':
                return division(firstFraction,secondFraction);
            case '*':
                return multiplication(firstFraction,secondFraction);
            default:
                return new Fraction();
        }
    }

    private Fraction division(Fraction firstFraction,Fraction secondFraction){
        Fraction resultFraction = new Fraction();
        resultFraction.setDenominator(firstFraction.getDenominator()*secondFraction.getNumerator());
        resultFraction.setNumerator(firstFraction.getNumerator()*(secondFraction.getDenominator()));
        return reduction(resultFraction);
    }

    private Fraction multiplication(Fraction firstFraction,Fraction secondFraction){
        Fraction resultFraction = new Fraction();
        resultFraction.setDenominator(firstFraction.getDenominator()*secondFraction.getDenominator());
        resultFraction.setNumerator(firstFraction.getNumerator()*secondFraction.getNumerator());
        return reduction(resultFraction);
    }

    private Fraction sum(Fraction firstFraction, Fraction secondFraction) {
        Fraction resultFraction = new Fraction();
        if(firstFraction.getDenominator() == secondFraction.getDenominator()) {
            resultFraction.setDenominator(firstFraction.getDenominator());
            resultFraction.setNumerator(firstFraction.getNumerator() + secondFraction.getNumerator());
        }
        else {
            int commonDenominator = firstFraction.getDenominator()*secondFraction.getDenominator();
            int resultNumerator = (firstFraction.getNumerator()*(commonDenominator/firstFraction.getDenominator())) + (secondFraction.getNumerator()*(commonDenominator/secondFraction.getDenominator()));
            resultFraction.setNumerator(resultNumerator);
            resultFraction.setDenominator(commonDenominator);
        }
        return reduction(resultFraction);
    }

    private Fraction subtraction(Fraction firstFraction,Fraction secondFraction) {
        Fraction resultFraction = new Fraction();
        if(firstFraction.getDenominator() == secondFraction.getDenominator()) {
            resultFraction.setDenominator(firstFraction.getDenominator());
            resultFraction.setNumerator(firstFraction.getNumerator() - secondFraction.getNumerator());
        }
        else {
            int commonDenominator = firstFraction.getDenominator()*secondFraction.getDenominator();
            int resultNumerator = (firstFraction.getNumerator()*(commonDenominator/firstFraction.getDenominator())) - (secondFraction.getNumerator()*(commonDenominator/secondFraction.getDenominator()));
            resultFraction.setNumerator(resultNumerator);
            resultFraction.setDenominator(commonDenominator);
        }
        return reduction(resultFraction);
    }

    private Fraction reduction(Fraction fraction) {
        int gcd = Math.abs(greatestCommonDividend(fraction.getNumerator(),fraction.getDenominator()));
        fraction.setNumerator(fraction.getNumerator()/gcd);
        fraction.setDenominator(fraction.getDenominator()/gcd);
        return fraction;
    }

    private int greatestCommonDividend(int numerator,int denominator) {
        return denominator == 0 ? numerator : greatestCommonDividend(denominator,numerator % denominator);
    }
}
