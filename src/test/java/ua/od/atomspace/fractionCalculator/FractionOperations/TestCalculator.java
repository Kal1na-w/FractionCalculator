package ua.od.atomspace.fractionCalculator.FractionOperations;

import org.junit.Before;
import org.junit.Test;
import ua.od.atomspace.Calculator.service.FractionOperations.calculate.Calculator;

import static org.junit.Assert.assertEquals;

public class TestCalculator {
    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testFractionCalculator() {
        assertEquals("1",calculator.calculate("1/7 + 2 * (2/7 + 3/7 + 7/7 * 1/7) : 2"));
        assertEquals("8",calculator.calculate("(2 + 2) + (2 + 2)"));
        assertEquals("55",calculator.calculate("55"));
        assertEquals("7/6",calculator.calculate("1/2 + 2/3"));
        assertEquals("3/4",calculator.calculate("(3/6) : (2/3)"));
        assertEquals("-47/42",calculator.calculate("((1/2) + (2/3)) - ((2/7) : (1/8))"));
        assertEquals("3/2",calculator.calculate("1 + 1/2"));
        assertEquals("No valid expression",calculator.calculate("1/7 + 2 * (2/7 + 3/7 + 7/7 * 1/7#) : 2"));
        assertEquals("-47/42",calculator.calculate("((1/2) + (2/3)) - ((2/7) : (1/8))"));
        assertEquals("42",calculator.calculate("((42))"));
        assertEquals("No valid expression",calculator.calculate("(1/2) + 1+"));
        assertEquals("No valid expression",calculator.calculate("())5(()"));
    }
}
