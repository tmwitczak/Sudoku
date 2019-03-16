//////////////////////////////////////////////////////////////////////// Package
package jptw;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

////////////////////////////////////////////////////////// Test class definition
public class PascalTriangleTest {
    //////////////////////////////////////////////////////////////////// [Tests]
    @Test
    public void computeZero() {
        PascalTriangle pascalTriangle = new PascalTriangle();
        int n = 0;
        int expectedLength = 0;

        pascalTriangle.compute(n);
        int actualLength = pascalTriangle.getTriangle().length;

        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void computeOne() {
        PascalTriangle pascalTriangle = new PascalTriangle();
        int n = 1;
        int[][] expectedResult = new int[][]{{1}};

        pascalTriangle.compute(n);
        int[][] actualResult = pascalTriangle.getTriangle();

        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void computeFour() {
        PascalTriangle pascalTriangle = new PascalTriangle();
        int n = 4;
        int[][] expectedResult = new int[][]
                {
                        {1},
                        {1, 1},
                        {1, 2, 1},
                        {1, 3, 3, 1}
                };

        pascalTriangle.compute(n);
        int[][] actualResult = pascalTriangle.getTriangle();

        assertArrayEquals(expectedResult, actualResult);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void computeMinus3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Input must be a non-negative"
                + " number.");

        PascalTriangle pascalTriangle = new PascalTriangle();
        int n = -3;

        pascalTriangle.compute(n);
    }
}
////////////////////////////////////////////////////////////////////////////////
