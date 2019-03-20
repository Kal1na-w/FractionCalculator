package calculate;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ua.od.atomspace.Fraction;
import ua.od.atomspace.calculate.MathOperations;

public class MathOperationsTest {

    private MathOperations mathOperations;

    @Before
    public void init() {
        mathOperations = new MathOperations();
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

}
