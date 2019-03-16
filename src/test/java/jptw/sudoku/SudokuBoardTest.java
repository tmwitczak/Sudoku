//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void getBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int expectedResult = 0;

        sudokuBoard.getBoard()[0][0] = 10;
        int actualResult = sudokuBoard.getBoard()[0][0];

        assertEquals(expectedResult, actualResult);
    }

    ////////////////////////////////////////////////////////////////// [Methods]
    //------------------------------------------------------- Helper functions <
    /*private boolean isBoardValid(final int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (!isRowValid(board, i)) {
                return false;
            }
            for (int j = 0; j < )
        }
        return true;
    }*/
}
////////////////////////////////////////////////////////////////////////////////