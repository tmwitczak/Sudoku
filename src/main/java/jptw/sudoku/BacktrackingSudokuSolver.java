//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.ArrayList;
import java.util.Random;

/////////////////////////////////////////////////////////////// Class definition
class BacktrackingSudokuSolver
        implements SudokuSolver {
    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------- Main functionality <
    @Override
    public void solve(final SudokuBoard board) {
        SudokuBoard boardCopy = new SudokuBoard(board);
        copy(board, previousBoard);
        do {
            if (!fill(board)) {
                copy(boardCopy, board);
            }
        }
        while (!isBoardFilled(board) || board.equals(previousBoard));
    }

    //------------------------------------------------------ Helper functions <<
    private void clean(final SudokuBoard board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.set(i, j, 0);
            }
        }
    }

    private void copy(final SudokuBoard source, final SudokuBoard destination) {
        clean(destination);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                destination.set(i, j, source.get(i, j));
            }
        }
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

    private boolean fill(final SudokuBoard board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board.get(i, j) == 0) {
                    if (!fillElement(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean fillElement(final SudokuBoard board, int i, int j) {
        ArrayList<Integer> wrongNumbers = new ArrayList<>();
        do {
            int randomSudokuNumber;

            do {
                randomSudokuNumber = generateRandomSudokuNumber();
            } while (wrongNumbers.contains(randomSudokuNumber));

            if (board.set(i, j, randomSudokuNumber)) {
                return true;
            } else {
                wrongNumbers.add(randomSudokuNumber);
            }

        } while (wrongNumbers.size() < BOARD_SIZE);
        return false;
    }

    private int generateRandomSudokuNumber() {
        return random.nextInt(BOARD_SIZE) + 1;
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private static final SudokuBoard previousBoard = new SudokuBoard();
    private static final int BOARD_SIZE = 9;
    private static final Random random = new Random();

}

////////////////////////////////////////////////////////////////////////////////
