package validation;

import org.junit.Before;
import org.junit.Test;
import ua.od.atomspace.validation.ExpressionValidation;

import static org.junit.Assert.*;

public class ExpressionValidationTest {

    private ExpressionValidation expressionValidation;

    @Before
    public void init(){expressionValidation = new ExpressionValidation();}

    @Test
    public void charValidationTest() {
        assertTrue(expressionValidation.charValidation(new StringBuilder("1/7 + 2 * (2/7 + 3/7 + 7/7 * 1/7) : 2")));
        assertTrue(expressionValidation.charValidation(new StringBuilder("1/7 + 2")));
        assertFalse(expressionValidation.charValidation(new StringBuilder("1/7 +. 2")));
        assertFalse(expressionValidation.charValidation(new StringBuilder("1/7% + 2")));
    }
    @Test
    public void bracketsValidationTest() {
        assertTrue(expressionValidation.bracketsValidation(new StringBuilder("((()))")));
        assertTrue(expressionValidation.bracketsValidation(new StringBuilder("()")));
        assertFalse(expressionValidation.bracketsValidation(new StringBuilder("(()")));
        assertFalse(expressionValidation.bracketsValidation(new StringBuilder("(())))")));
    }
    @Test
    public void fractionValidationTest() {
        assertTrue(expressionValidation.fractionValidation(new StringBuilder("2/7")));
        assertTrue(expressionValidation.fractionValidation(new StringBuilder("2/7 + 23/56")));
        assertTrue(expressionValidation.fractionValidation(new StringBuilder("2/7 + 23")));
        assertFalse(expressionValidation.fractionValidation(new StringBuilder("2/7/7 + 23")));
        assertFalse(expressionValidation.fractionValidation(new StringBuilder("2/7/7/7 + 23")));
        assertFalse(expressionValidation.fractionValidation(new StringBuilder("2/ + 23")));
        assertFalse(expressionValidation.fractionValidation(new StringBuilder("/2 + 23")));
    }
}
