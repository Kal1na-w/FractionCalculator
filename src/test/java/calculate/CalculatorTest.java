package calculate;

import org.junit.Before;
import org.junit.Test;
import ua.od.atomspace.calculate.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void init() {calculator = new Calculator();}

    @Test
    public void countFullExpressionTest() {
        assertEquals("7/2",calculator.countFullExpression(new StringBuilder("1/2+2/2:1/3")).toString());
        assertEquals("9/1",calculator.countFullExpression(new StringBuilder("1/1+2/2:1/2*2*1/1:1*2")).toString());
    }

    @Test
    public void countExpressionWithBracketsTest() {
        assertEquals("1/1",calculator.countExpressionWithBrackets(new StringBuilder("1/7+2*(2/7+3/7+7/7*1/7):2")).toString());
        assertEquals("11/8",calculator.countExpressionWithBrackets(new StringBuilder("1/2+(-3/2*7/4):-3")).toString());
        assertEquals("5/2",calculator.countExpressionWithBrackets(new StringBuilder("4/2-(-1:2)")).toString());
        assertEquals("5/2",calculator.countExpressionWithBrackets(new StringBuilder("4/2-(-1:2)")).toString());
    }
    @Test
    public void CalculatorTest() {
        assertEquals("1/1",calculator.calculate("1/7 + 2 * (2/7 + 3/7 + 7/7 * 1/7) : 2"));
        assertEquals("8/1",calculator.calculate("(2 + 2) + (2 + 2)"));
        assertEquals("55",calculator.calculate("55"));
        assertEquals("7/6",calculator.calculate("1/2 + 2/3"));
        assertEquals("3/4",calculator.calculate("(3/6) : (2/3)"));
        assertEquals("-47/42",calculator.calculate("((1/2) + (2/3)) - ((2/7) : (1/8))"));
        assertEquals("3/2",calculator.calculate("1 + 1/2"));
        assertEquals("No valid expression",calculator.calculate("1/7 + 2 * (2/7 + 3/7 + 7/7 * 1/7#) : 2"));
    }

}
