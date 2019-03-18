package calculate;

import org.junit.*;

import static org.junit.Assert.*;

import org.omg.CORBA.PUBLIC_MEMBER;
import ua.od.atomspace.Fraction;
import ua.od.atomspace.calculate.MathOperations;

public class MathOperationsTest {

    private MathOperations mathOperations;


    @Before
    public void init() {
        mathOperations = new MathOperations();
    }

    @Test
    public void testSum() {
        Fraction[] fractions = new Fraction[] {
          new Fraction(2,4),
          new Fraction(4,4),
          new Fraction(4,5),
        };
        assertEquals("3/2",mathOperations.sum(fractions[0],fractions[1]).toString());
        assertEquals("13/10",mathOperations.sum(fractions[0],fractions[2]).toString());
    }
    @Test
    public void testSubtraction() {
        Fraction[] fractions = new Fraction[] {
                new Fraction(-2,4),
                new Fraction(4,4),
                new Fraction(4,5),
        };
        assertEquals("-3/2",mathOperations.subtraction(fractions[0],fractions[1]).toString());
        assertEquals("-13/10",mathOperations.subtraction(fractions[0],fractions[2]).toString());
    }

    @Test
    public void testDivision() {
        Fraction[] fractions = new Fraction[] {
                new Fraction(-2,4),
                new Fraction(4,4),
                new Fraction(4,5),
        };
        assertEquals("-1/2",mathOperations.division(fractions[0],fractions[1]).toString());
        assertEquals("5/4",mathOperations.division(fractions[1],fractions[2]).toString());
    }

    @Test
    public void testMultiplication() {
        Fraction[] fractions = new Fraction[] {
                new Fraction(-2,4),
                new Fraction(4,4),
                new Fraction(4,5),
        };
        assertEquals("-1/2",mathOperations.multiplication(fractions[0],fractions[1]).toString());
        assertEquals("4/5",mathOperations.multiplication(fractions[1],fractions[2]).toString());
    }
    @Test
    public void testCountUp() {
        Fraction[] fractions = new Fraction[] {
                new Fraction(2,4),
                new Fraction(4,4),
        };
        assertEquals("3/2",mathOperations.countUp(fractions[0],fractions[1],'+').toString());
        assertEquals("-1/2",mathOperations.countUp(fractions[0],fractions[1],'-').toString());
        assertEquals("1/2",mathOperations.countUp(fractions[0],fractions[1],'*').toString());
        assertEquals("1/2",mathOperations.countUp(fractions[0],fractions[1],':').toString());
    }

    @Test
    public void testReduction() {
        assertEquals("1/2",mathOperations.reduction(new Fraction(3,6)).toString());
        assertEquals("3/4",mathOperations.reduction(new Fraction(3,4)).toString());
    }

    @Test
    public void testGreatestCommonDividend() {
        assertEquals(4,mathOperations.greatestCommonDividend(20,16));
        assertEquals(1,mathOperations.greatestCommonDividend(20,21));
        assertEquals(1,mathOperations.greatestCommonDividend(5,7));
    }
}
