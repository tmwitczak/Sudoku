//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/////////////////////////////////////////////////////////////// Class definition
public class SudokuBoard {
    ////////////////////////////////////////////////////////////////// [Methods]
    //------------------------------------------------------------ Constructor <
    SudokuBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    //----------------------------------------------------- Main functionality <
    void fillBoard() {
        boolean isBoardValid;
        do {
            cleanBoard();
            isBoardValid = true;

            fillBoardElements:
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (!fillBoardElement(i, j)) {
                        isBoardValid = false;
                        break fillBoardElements;
                    }
                }
            }
        }
        while (!isBoardValid);
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

            if (isElementValid(i, j)) {
                return true;
            } else {
                wrongNumbers.add(board[i][j]);
            }

            if (wrongNumbers.size() == BOARD_SIZE) {
                return false;
            }

        } while (!isElementValid(i, j));
        return true;
    }

    private int generateRandomSudokuNumber() {
        return random.nextInt(BOARD_SIZE) + 1;
    }

    private boolean isElementValid(int i, int j) {
        return isRowValid(i) && isColumnValid(j) && isBoxValid(i, j);
    }

    private boolean isRowValid(int n) {
        int[] row = getRow(n);
        int[] rowWithoutZeros = removeZerosFromArray(row);
        return !checkForDuplicatesInArray(rowWithoutZeros);
    }

    private boolean isColumnValid(int n) {
        int[] column = getColumn(n);
        int[] columnWithoutZeros = removeZerosFromArray(column);
        return !checkForDuplicatesInArray(columnWithoutZeros);
    }

    private boolean isBoxValid(int i, int j) {
        int[] box = getBox(getBoxCoordinates(i, j)[0], getBoxCoordinates(i, j)[1]);
        int[] boxWithoutZeros = removeZerosFromArray(box);
        return !checkForDuplicatesInArray(boxWithoutZeros);
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

    private int[] getRow(int n) {
        return board[n];
    }

    private int[] getColumn(int n) {
        int[] column = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            column[i] = board[i][n];
        }
        return column;
    }

    private int[] getBox(int i, int j) {
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


    //---------------------------------------------------------------- Getters <
    int[][] getBoard() {
        return board.clone();
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private int[][] board;
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;
    private static final Random random = new Random();
}
////////////////////////////////////////////////////////////////////////////////