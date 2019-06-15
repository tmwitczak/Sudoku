//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

////////////////////////////////////////////////////////// Test class definition
public class SudokuBoardTest {

    //////////////////////////////////////////////////////////////////// [Tests]
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initialize() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    public void get() {
        int x = 0, y = 0;
        int expectedResult = 0;

        int actualResult = sudokuBoard.get(x, y).getFieldValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getWrongInputX() {
        int x = 10, y = 0;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Integer.toString(x));

        sudokuBoard.get(x, y);
    }

    @Test
    public void getWrongInputY() {
        int x = 0, y = 10;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Integer.toString(y));

        sudokuBoard.get(x, y);
    }

    @Test
    public void set() {
        int x = 0, y = 0;
        int expectedResult = 1;

        sudokuBoard.set(x, y, expectedResult);
    }

    @Test
    public void setWrongInput() {
        int x = 0, y = 0, value = 10;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Integer.toString(value));

        sudokuBoard.set(x, y, value);
    }

    @Test
    public void equalsThis() {
        assertThat(sudokuBoard.equals(sudokuBoard), is(true));
        assertThat(sudokuBoard.hashCode() == sudokuBoard.hashCode(), is(true));
    }

    @Test
    public void equalsSymmetric() {
        SudokuBoard a = sudokuBoard,
                b = new SudokuBoard();
        assertThat(a.equals(b) && b.equals(a), is(true));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void equalsNotBoard() {
        int[][] board1 = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };
        int[][] board2 = new int[][]{
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5}
        };

        SudokuBoard sudokuBoard1 = new SudokuBoard(),
                sudokuBoard2 = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, board1[i][j]);
                sudokuBoard2.set(i, j, board2[i][j]);
            }
        }

        assertThat(sudokuBoard1.equals(sudokuBoard2), is(false));
    }

    @Test
    public void equalsBoard() {
        int[][] board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };

        SudokuBoard sudokuBoard1 = new SudokuBoard(),
                sudokuBoard2 = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, board[i][j]);
                sudokuBoard2.set(i, j, board[i][j]);
            }
        }

        assertThat(sudokuBoard1.equals(sudokuBoard2), is(true));
    }

    @Test
    public void equalsNull() {
        assertThat(sudokuBoard.equals(null), is(false));
    }

    @Test
    public void toStringMethod() {
        int[][] board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, board[i][j]);
            }
        }
        assertNotNull(sudokuBoard.toString());
        assertEquals(sudokuBoard.toString(),
                     "[[1,false, 2,false, 3,false, 4,false, 5,false, 6,false, 7,false, 8,false, 9,false],"
                     + " [4,false, 5,false, 6,false, 7,false, 8,false, 9,false, 1,false, 2,false, 3,false],"
                     + " [7,false, 8,false, 9,false, 1,false, 2,false, 3,false, 4,false, 5,false, 6,false],"
                     + " [2,false, 1,false, 4,false, 3,false, 6,false, 5,false, 8,false, 9,false, 7,false],"
                     + " [3,false, 6,false, 5,false, 8,false, 9,false, 7,false, 2,false, 1,false, 4,false],"
                     + " [8,false, 9,false, 7,false, 2,false, 1,false, 4,false, 3,false, 6,false, 5,false],"
                     + " [5,false, 3,false, 1,false, 6,false, 4,false, 2,false, 9,false, 7,false, 8,false],"
                     + " [6,false, 4,false, 2,false, 9,false, 7,false, 8,false, 5,false, 3,false, 1,false],"
                     + " [9,false, 7,false, 8,false, 5,false, 3,false, 1,false, 6,false, 4,false, 2,false]]");

    }

    @Test
    public void cloneTest() {
        int[][] board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, board[i][j]);
            }
        }
        Object sudokuBoard2 = sudokuBoard.clone();
        Assert.assertThat(sudokuBoard.equals(sudokuBoard2) && sudokuBoard2.equals(sudokuBoard), is(true));
        assertEquals(sudokuBoard.hashCode(), sudokuBoard2.hashCode());
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private SudokuBoard sudokuBoard;

}

////////////////////////////////////////////////////////////////////////////////
