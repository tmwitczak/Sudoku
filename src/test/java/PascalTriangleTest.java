import org.junit.Test;

import static org.junit.Assert.*;

public class PascalTriangleTest {

    PascalTriangle triangle;

    @Test
    public void T1(){
        triangle = new PascalTriangle();
        triangle.compute(0);
        assertEquals(triangle.getTriangle().length, 0);
    }

    @Test
    public void T2(){
        triangle = new PascalTriangle();
        triangle.compute(1);
        assertEquals(triangle.getTriangle().length, 1);
        assertArrayEquals(triangle.getTriangle()[0], new int[]{1});
    }

    @Test
    public void T3(){
        triangle = new PascalTriangle();
        triangle.compute(4);
        assertEquals(triangle.getTriangle().length, 4);
        assertArrayEquals(triangle.getTriangle()[0], new int[]{1});
        assertArrayEquals(triangle.getTriangle()[1], new int[]{1, 1});
        assertArrayEquals(triangle.getTriangle()[2], new int[]{1, 2, 1});
        assertArrayEquals(triangle.getTriangle()[3], new int[]{1, 3, 3, 1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void T4(){
        triangle = new PascalTriangle();
        triangle.compute(-3);
    }
}