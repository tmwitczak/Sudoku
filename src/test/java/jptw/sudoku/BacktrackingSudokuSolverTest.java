//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Test;

import java.util.BitSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

////////////////////////////////////////////////////////// Test class definition
public class BacktrackingSudokuSolverTest {
    //////////////////////////////////////////////////////////////////// [Tests]
    @Test
    public void solve() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoardValidator sudokuBoardValidator = new SudokuBoardValidator();

        sudokuSolver.solve(sudokuBoard);
        boolean boardStatus
                = sudokuBoardValidator.isBoardValid(sudokuBoard);

        assertThat(boardStatus, is(true));
    }

    @Test
    public void solvePartialBoard() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoardValidator sudokuBoardValidator = new SudokuBoardValidator();

        int[][] board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 0, 3},
                {7, 8, 9, 1, 2, 3, 4, 0, 6},
                {2, 1, 0, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 0, 4},
                {8, 9, 0, 2, 1, 0, 3, 6, 5},
                {5, 0, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 0, 9, 7, 8, 5, 0, 1},
                {9, 7, 8, 5, 0, 1, 6, 4, 2}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, board[i][j]);
            }
        }

        sudokuSolver.solve(sudokuBoard);
        boolean boardStatus
                = sudokuBoardValidator.isBoardValid(sudokuBoard);

        assertThat(boardStatus, is(true));
    }

    @Test
    public void checkTwoIdenticalConsecutiveBoards() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoardValidator sudokuBoardValidator = new SudokuBoardValidator();
        sudokuSolver.solve(sudokuBoard);
        sudokuBoard.set(0, 0, 0);

        sudokuSolver.solve(sudokuBoard);
        boolean boardStatus
                = sudokuBoardValidator.isBoardValid(sudokuBoard);

        assertThat(boardStatus, is(false));
    }

    @Test
    public void checkTwoConsecutiveBoards() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        final int NUMBER_OF_BOARDS = 2;
        SudokuBoard[] sudokuBoards = new SudokuBoard[NUMBER_OF_BOARDS];

        for (int i = 0; i < NUMBER_OF_BOARDS; i++) {
            sudokuBoards[i] = new SudokuBoard();
            sudokuSolver.solve(sudokuBoards[i]);
        }

        assertThat(sudokuBoards[0].equals(sudokuBoards[1]), is(false));
    }

    ////////////////////////////////////////////////// [Helper class definition]
    class SudokuBoardValidator {
        ////////////////////////////////////////////////////////////// [Methods]
        //------------------------------------------------- Main functionality <
        boolean isBoardValid(final SudokuBoard board) {
            // Check for zeros
            if (!isBoardFilled(board)) {
                return false;
            }

            // Check rows and columns
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (!isRowValid(board, i) || !isColumnValid(board, i)) {
                    return false;
                }
            }

            // Check boxes
            for (int i = 0; i < (BOARD_SIZE / BOX_SIZE); i++) {
                for (int j = 0; j < (BOARD_SIZE / BOX_SIZE); j++) {
                    if (!isBoxValid(board, BOX_SIZE * i, BOX_SIZE * j)) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isBoardFilled(final SudokuBoard board) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board.get(i, j) == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isRowValid(final SudokuBoard board, int n) {
            int[] row = getRow(board, n);
            return !checkForDuplicatesInArray(row);
        }

        private boolean isColumnValid(final SudokuBoard board, int n) {
            int[] column = getColumn(board, n);
            return !checkForDuplicatesInArray(column);
        }

        private boolean isBoxValid(final SudokuBoard board, int i, int j) {
            int[] box = getBox(board, getBoxCoordinates(i, j)[0],
                    getBoxCoordinates(i, j)[1]);
            return !checkForDuplicatesInArray(box);
        }

        //-------------------------------------------------- Helper functions <<
        private int[] getRow(final SudokuBoard board, int n) {
            int[] row = new int[BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; i++) {
                row[i] = board.get(n, i);
            }
            return row;
        }

        private int[] getColumn(final SudokuBoard board, int n) {
            int[] column = new int[BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; i++) {
                column[i] = board.get(i, n);
            }
            return column;
        }

        private int[] getBox(final SudokuBoard board, int i, int j) {
            int[] box = new int[BOX_SIZE * BOX_SIZE];
            int[] boxCoordinates = getBoxCoordinates(i, j);
            for (int k = 0; k < BOX_SIZE; k++) {
                for (int l = 0; l < BOX_SIZE; l++) {
                    box[BOX_SIZE * k + l]
                            = board.get(boxCoordinates[0] + k,
                            boxCoordinates[1] + l);
                }
            }
            return box;
        }

        private int[] getBoxCoordinates(int i, int j) {
            return new int[]{
                    (i / BOX_SIZE) * BOX_SIZE,
                    (j / BOX_SIZE) * BOX_SIZE
            };
        }

        private boolean checkForDuplicatesInArray(final int[] array) {
            BitSet bitSet = new BitSet(array.length);
            bitSet.set(0, array.length, false);
            for (int i : array) {
                if (!bitSet.get(i)) {
                    bitSet.set(i, true);
                } else {
                    return true;
                }
            }
            return false;
        }

        /////////////////////////////////////////////////////////////// [Fields]
        private static final int BOARD_SIZE = 9;
        private static final int BOX_SIZE = 3;
    }

}

////////////////////////////////////////////////////////////////////////////////
