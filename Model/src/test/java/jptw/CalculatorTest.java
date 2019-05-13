//////////////////////////////////////////////////////////////////////// Package
package jptw;

//////////////////////////////////////////////////////////////////////// Imports

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

////////////////////////////////////////////////////////// Test class definition
public class CalculatorTest {
    //////////////////////////////////////////////////////////////////// [Tests]
    //----------------------------------------------------- Main functionality <
    @Test
    public void addInt() {
        Calculator calculator = new Calculator();
        int a = 10, b = 5;
        int expectedResult = 15;

        int actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addDouble() {
        Calculator calculator = new Calculator();
        double a = 10.0, b = 5.0;
        double expectedResult = 15.0;

        double actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void diffInt() {
        Calculator calculator = new Calculator();
        int a = 10, b = 5;
        int expectedResult = 5;

        int actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void diffDouble() {
        Calculator calculator = new Calculator();
        double a = 10.0, b = 5.0;
        double expectedResult = 5.0;

        double actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void mulInt() {
        Calculator calculator = new Calculator();
        int a = 10, b = 5;
        int expectedResult = 50;

        int actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void mulDouble() {
        Calculator calculator = new Calculator();
        double a = 10.0, b = 5.0;
        double expectedResult = 50.0;

        double actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void divInt() {
        Calculator calculator = new Calculator();
        int a = 10, b = 5;
        int expectedResult = 2;

        int actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void divDouble() {
        Calculator calculator = new Calculator();
        double a = 10.0, b = 5.0;
        double expectedResult = 2.0;

        double actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    //--------------------------------------------------------------- Overflow <
    @Test
    public void addIntPositiveOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MAX_VALUE, b = 1;
        int expectedResult = Integer.MIN_VALUE;

        int actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addIntNegativeOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MIN_VALUE, b = -1;
        int expectedResult = Integer.MAX_VALUE;

        int actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addDoublePositiveOverflow() {
        Calculator calculator = new Calculator();
        double a = Double.MAX_VALUE, b = a;
        double expectedResult = Double.POSITIVE_INFINITY;

        double actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void addDoubleNegativeOverflow() {
        Calculator calculator = new Calculator();
        double a = -Double.MAX_VALUE, b = a;
        double expectedResult = Double.NEGATIVE_INFINITY;

        double actualResult = calculator.add(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void diffIntPositiveOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MAX_VALUE, b = -1;
        int expectedResult = Integer.MIN_VALUE;

        int actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void diffIntNegativeOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MIN_VALUE, b = 1;
        int expectedResult = Integer.MAX_VALUE;

        int actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void diffDoublePositiveOverflow() {
        Calculator calculator = new Calculator();
        double a = Double.MAX_VALUE, b = -a;
        double expectedResult = Double.POSITIVE_INFINITY;

        double actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void diffDoubleNegativeOverflow() {
        Calculator calculator = new Calculator();
        double a = -Double.MAX_VALUE, b = -a;
        double expectedResult = Double.NEGATIVE_INFINITY;

        double actualResult = calculator.diff(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void mulIntPositiveOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MAX_VALUE, b = 2;
        int expectedResult = -2;

        int actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void mulIntNegativeOverflow() {
        Calculator calculator = new Calculator();
        int a = Integer.MIN_VALUE, b = 2;
        int expectedResult = 0;

        int actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void mulDoublePositiveOverflow() {
        Calculator calculator = new Calculator();
        double a = Double.MAX_VALUE, b = a;
        double expectedResult = Double.POSITIVE_INFINITY;

        double actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void mulDoubleNegativeOverflow() {
        Calculator calculator = new Calculator();
        double a = Double.MAX_VALUE, b = -a;
        double expectedResult = Double.NEGATIVE_INFINITY;

        double actualResult = calculator.mul(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    //-------------------------------------------------------------- Underflow <
    @Test
    public void divDoublePositiveUnderflow() {
        Calculator calculator = new Calculator();
        double a = Double.MIN_VALUE, b = 2.0;
        double expectedResult = 0.0;

        double actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    @Test
    public void divDoubleNegativeUnderflow() {
        Calculator calculator = new Calculator();
        double a = -Double.MIN_VALUE, b = 2.0;
        double expectedResult = 0.0;

        double actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    //--------------------------------------- Dividing non-zero number by zero <
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void divIntNonZeroByZero() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("/ by zero");

        Calculator calculator = new Calculator();
        int a = 10, b = 0;

        calculator.div(a, b);
    }

    @Test
    public void divDoubleNonZeroByZero() {
        Calculator calculator = new Calculator();
        double a = 10.0, b = 0.0;
        double expectedResult = Double.POSITIVE_INFINITY;

        double actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }

    //-------------------------------------------------- Dividing zero by zero <
    @Test
    public void divIntZeroByZero() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("/ by zero");

        Calculator calculator = new Calculator();
        int a = 0, b = a;

        calculator.div(a, b);
    }

    @Test
    public void divDoubleZeroByZero() {
        Calculator calculator = new Calculator();
        double a = 0.0, b = 0.0;
        double expectedResult = Double.NaN;

        double actualResult = calculator.div(a, b);

        assertEquals(expectedResult, actualResult, Double.MIN_VALUE);
    }
}
////////////////////////////////////////////////////////////////////////////////
