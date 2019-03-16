//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

////////////////////////////////////////////////////////// Test class definition
public class SudokuBoardTest {
    //////////////////////////////////////////////////////////////////// [Tests]
    @Test
    public void fillBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoardValidator sudokuBoardValidator = new SudokuBoardValidator();
        boolean expectedResult = true;

        sudokuBoard.fillBoard();
        boolean actualResult
                = sudokuBoardValidator.isBoardValid(sudokuBoard.getBoard());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkTwoConsecutiveBoards() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][][] boards = new int[2][][];

        for (int i = 0; i < boards.length; i++) {
            sudokuBoard.fillBoard();
            boards[i] = sudokuBoard.getBoard();
        }

        assertThat(boards[0], not(equalTo(boards[1])));
    }

    @Test
    public void getBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int expectedResult = 0;

        sudokuBoard.getBoard()[0][0] = 10;
        int actualResult = sudokuBoard.getBoard()[0][0];

        assertEquals(expectedResult, actualResult);
    }

}

////////////////////////////////////////////////////////////////////////////////
