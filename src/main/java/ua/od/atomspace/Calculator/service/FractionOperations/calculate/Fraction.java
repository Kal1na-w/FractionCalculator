package ua.od.atomspace.Calculator.service.FractionOperations.calculate;

public class Fraction {
    private int numerator;
    private int denominator;

    Fraction() {
    }

    Fraction(String fraction) {
        if(fraction.matches("\\d+\\/\\d+")) {
           String[] numeratorDenominator = fraction.split("/");
           this.numerator = Integer.valueOf(numeratorDenominator[0]);
           this.denominator = Integer.valueOf(numeratorDenominator[1]);
        }
        else {
            this.numerator = Integer.valueOf(fraction);
            this.denominator = 1;
        }
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    int getNumerator() {
        return numerator;
    }

    void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    int getDenominator() {
        return denominator;
    }

    void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        if (numerator == denominator) {
            return String.valueOf(numerator);
        }
        else if (denominator == 1) {
            return String.valueOf(numerator);
        }
        else {
            return numerator + "/" + denominator;
        }
    }

}
