//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/////////////////////////////////////////////////////////////// Class definition
class SudokuBoard {
    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------- Main functionality <
    void fillBoard() {
        do {
            cleanBoard();

            fillBoardElements:
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (!fillBoardElement(i, j)) {
                        break fillBoardElements;
                    }
                }
            }
        }
        while (!sudokuBoardValidator.isBoardValid(board));
    }

    //------------------------------------------------------ Helper functions <<
    private void cleanBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    private boolean fillBoardElement(int i, int j) {
        ArrayList<Integer> wrongNumbers = new ArrayList<>();
        do {
            do {
                board[i][j] = generateRandomSudokuNumber();
            } while (wrongNumbers.contains(board[i][j]));

            if (sudokuBoardValidator.isElementValid(board, i, j)) {
                return true;
            } else {
                wrongNumbers.add(board[i][j]);
            }

            if (wrongNumbers.size() == BOARD_SIZE) {
                return false;
            }

        } while (!sudokuBoardValidator.isElementValid(board, i, j));
        return true;
    }

    private int generateRandomSudokuNumber() {
        return random.nextInt(BOARD_SIZE) + 1;
    }

    //---------------------------------------------------------------- Getters <
    int[][] getBoard() {
        int[][] clonedBoard = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            clonedBoard[i] = board[i].clone();
        }
        return clonedBoard;
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static final int BOARD_SIZE = 9;
    private static final Random random = new Random();
    private static final SudokuBoardValidator sudokuBoardValidator
            = new SudokuBoardValidator();
}

//////////////////////////////////////////////////////// Helper class definition
class SudokuBoardValidator {
    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------- Main functionality <
    boolean isBoardValid(final int[][] board) {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!isRowValid(board, i) || !isColumnValid(board, i)) {
                return false;
            }
        }

        // Check boxes
        for (int i = 0; i < BOX_SIZE; i++) {
            if (!isBoxValid(board, BOX_SIZE * i, BOX_SIZE * i)) {
                return false;
            }
        }

        return true;
    }

    boolean isElementValid(final int[][] board, int i, int j) {
        return isRowValid(board, i)
                && isColumnValid(board, j)
                && isBoxValid(board, i, j);
    }

    private boolean isRowValid(final int[][] board, int n) {
        int[] row = getRow(board, n);
        int[] rowWithoutZeros = removeZerosFromArray(row);
        return !checkForDuplicatesInArray(rowWithoutZeros);
    }

    private boolean isColumnValid(final int[][] board, int n) {
        int[] column = getColumn(board, n);
        int[] columnWithoutZeros = removeZerosFromArray(column);
        return !checkForDuplicatesInArray(columnWithoutZeros);
    }

    private boolean isBoxValid(final int[][] board, int i, int j) {
        int[] box = getBox(board, getBoxCoordinates(i, j)[0],
                getBoxCoordinates(i, j)[1]);
        int[] boxWithoutZeros = removeZerosFromArray(box);
        return !checkForDuplicatesInArray(boxWithoutZeros);
    }

    //------------------------------------------------------ Helper functions <<
    private int[] getRow(final int[][] board, int n) {
        return board[n];
    }

    private int[] getColumn(final int[][] board, int n) {
        int[] column = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            column[i] = board[i][n];
        }
        return column;
    }

    private int[] getBox(final int[][] board, int i, int j) {
        int[] box = new int[BOX_SIZE * BOX_SIZE];
        int[] boxCoordinates = getBoxCoordinates(i, j);
        for (int k = 0; k < BOX_SIZE; k++) {
            for (int l = 0; l < BOX_SIZE; l++) {
                box[BOX_SIZE * k + l]
                        = board[boxCoordinates[0] + k][boxCoordinates[1] + l];
            }
        }
        return box;
    }

    private int[] getBoxCoordinates(int i, int j) {
        return new int[]{(i / BOX_SIZE) * BOX_SIZE, (j / BOX_SIZE) * BOX_SIZE};
    }

    private int[] removeZerosFromArray(final int[] array) {
        int zeroCount = countZerosInArray(array);
        int[] arrayWithoutZeros = new int[array.length - zeroCount];

        int i = 0;
        for (int element : array) {
            if (element != 0) {
                arrayWithoutZeros[i] = element;
                i++;
            }
        }

        return arrayWithoutZeros;
    }

    private int countZerosInArray(final int[] array) {
        int count = 0;
        for (int element : array) {
            if (element == 0) {
                count++;
            }
        }
        return count;
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

    /////////////////////////////////////////////////////////////////// [Fields]
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;
}
////////////////////////////////////////////////////////////////////////////////