//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.Arrays;
import java.util.BitSet;

/////////////////////////////////////////////////////////////// Class definition
public class SudokuBoard {
    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------------- Constructors <
    SudokuBoard() {
        this.board = new SudokuField[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    SudokuBoard(final SudokuBoard board) {
        this.board = new SudokuField[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = new SudokuField(board.get(i, j));
            }
        }
    }

    //----------------------------------------------------- Main functionality <
    //------------------------------------------------------------- Accessors <<
    public int get(int x, int y) {
        if (x < 0 || x >= 9) {
            throw new IllegalArgumentException(Integer.toString(x));
        }
        if (y < 0 || y >= 9) {
            throw new IllegalArgumentException(Integer.toString(y));
        }

        return board[x][y].getFieldValue();
    }

    public boolean set(int x, int y, int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException(Integer.toString(value));
        }

        int oldValue = get(x, y);
        board[x][y].setFieldValue(value);

        if (!checkBoard()) {
            board[x][y].setFieldValue(oldValue);
            return false;
        }

        return true;
    }

    //------------------------------------------------------ Board validation <<
    private boolean checkBoard() {
        return sudokuBoardValidator.isBoardValid(this);
    }

    //------------------------------------------------------------- Comparison <
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        SudokuBoard board = (SudokuBoard) object;
        return Arrays.deepEquals(board.board, this.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private SudokuField[][] board;
    private static final int BOARD_SIZE = 9;
    private static final int EMPTY_CELL = 0;
    private final SudokuBoardValidator sudokuBoardValidator
            = new SudokuBoardValidator();

    /////////////////////////////////////////////////////////// [Helper classes]
    class SudokuBoardValidator {
        ////////////////////////////////////////////////////////////// [Methods]
        //------------------------------------------------- Main functionality <
        boolean isBoardValid(final SudokuBoard board) {
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

    /*boolean isElementValid(final SudokuBoard board, int i, int j) {
        return isRowValid(board, i)
                && isColumnValid(board, j)
                && isBoxValid(board, i, j);
    }*/

        private boolean isRowValid(final SudokuBoard board, int n) {
            int[] row = getRow(board, n);
            int[] rowWithoutZeros = removeZerosFromArray(row);
            return !checkForDuplicatesInArray(rowWithoutZeros);
        }

        private boolean isColumnValid(final SudokuBoard board, int n) {
            int[] column = getColumn(board, n);
            int[] columnWithoutZeros = removeZerosFromArray(column);
            return !checkForDuplicatesInArray(columnWithoutZeros);
        }

        private boolean isBoxValid(final SudokuBoard board, int i, int j) {
            int[] box = getBox(board, getBoxCoordinates(i, j)[0],
                    getBoxCoordinates(i, j)[1]);
            int[] boxWithoutZeros = removeZerosFromArray(box);
            return !checkForDuplicatesInArray(boxWithoutZeros);
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

        /////////////////////////////////////////////////////////////// [Fields]
        private static final int BOARD_SIZE = 9;
        private static final int BOX_SIZE = 3;
    }
}

////////////////////////////////////////////////////////////////////////////////
