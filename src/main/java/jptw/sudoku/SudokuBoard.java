//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.Arrays;

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

    //-------------------------------------------------------------- Accessors <
    public int get(int x, int y) {

        if (x < 0 || x >= 9) {
            throw new IllegalArgumentException(Integer.toString(x));
        }

        if (y < 0 || y >= 9) {
            throw new IllegalArgumentException(Integer.toString(y));
        }

        return board[x][y].getFieldValue();
    }

    public SudokuRow getRow(final int x) {

        SudokuField[] row = new SudokuField[BOARD_SIZE];

        for (int i = 0; i < row.length; i++) {
            row[i] = board[x][i];
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(final int y) {

        SudokuField[] column = new SudokuField[BOARD_SIZE];

        for (int i = 0; i < column.length; i++) {
            column[i] = board[i][y];
        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(final int x, final int y) {

        SudokuField[] box = new SudokuField[BOX_SIZE * BOX_SIZE];
        int[] boxCoordinates = getBoxCoordinates(x, y);

        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                box[BOX_SIZE * i + j]
                        = board[boxCoordinates[0] + i][boxCoordinates[1] + j];
            }
        }

        return new SudokuBox(box);
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

    //------------------------------------------------------- Board validation <
    private boolean checkBoard() {

        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }

        // Check boxes
        for (int i = 0; i < (BOARD_SIZE / BOX_SIZE); i++) {
            for (int j = 0; j < (BOARD_SIZE / BOX_SIZE); j++) {
                if (!getBox(BOX_SIZE * i, BOX_SIZE * j).verify()) {
                    return false;
                }
            }
        }

        return true;
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

    //------------------------------------------------------- Helper functions <
    private int[] getBoxCoordinates(int x, int y) {
        
        return new int[]{
                (x / BOX_SIZE) * BOX_SIZE,
                (y / BOX_SIZE) * BOX_SIZE
        };
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private SudokuField[][] board;
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;

}

////////////////////////////////////////////////////////////////////////////////
