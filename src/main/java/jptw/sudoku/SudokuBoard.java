//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/////////////////////////////////////////////////////////////// Class definition
public class SudokuBoard {

    private List<List<SudokuField>> board;
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;

    public SudokuBoard() {

        this.board = (List) Arrays.asList(new List[BOARD_SIZE]);

        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[BOARD_SIZE]));
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public SudokuBoard(final SudokuBoard board) {

        this.board = (List) Arrays.asList(new List[BOARD_SIZE]);

        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[BOARD_SIZE]));
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board.get(i).set(j, new SudokuField(board.board.get(i).get(j).getFieldValue()));
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

        return board.get(x).get(y).getFieldValue();
    }

    public SudokuRow getRow(final int x) {

        List<SudokuField> row = Arrays.asList(new SudokuField[BOARD_SIZE]);

        for (int i = 0; i < row.size(); i++) {
            row.set(i,new SudokuField(board.get(x).get(i).getFieldValue()));
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(final int y) {

        List<SudokuField> column = Arrays.asList(new SudokuField[BOARD_SIZE]);

        for (int i = 0; i < column.size(); i++) {
            column.set(i,new SudokuField(board.get(i).get(y).getFieldValue()));

        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(final int x, final int y) {

        List<SudokuField> box = Arrays.asList(new SudokuField[BOX_SIZE * BOX_SIZE]);
        int[] boxCoordinates = getBoxCoordinates(x, y);

        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                box.set(BOX_SIZE * i + j,new SudokuField(board.get(boxCoordinates[0] + i).get(boxCoordinates[1] + j).getFieldValue()));
            }
        }

        return new SudokuBox(box);
    }

    public boolean set(int x, int y, int value) {

        if (value < 0 || value > 9) {
            throw new IllegalArgumentException(Integer.toString(value));
        }

        int oldValue = get(x, y);
        board.get(x).set(y,new SudokuField(value));

        if (!checkBoard()) {
            board.get(x).set(y,new SudokuField(oldValue));
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

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board.board.get(i).get(j).getFieldValue() != this.board.get(i).get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        int code=0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                code+=Objects.hash(board.get(i).get(j).getFieldValue());
            }
        }
        return code;
    }

    //------------------------------------------------------- Helper functions <
    private int[] getBoxCoordinates(int x, int y) {

        return new int[]{
                (x / BOX_SIZE) * BOX_SIZE,
                (y / BOX_SIZE) * BOX_SIZE
        };
    }

    /////////////////////////////////////////////////////////////////// [Fields]


}

////////////////////////////////////////////////////////////////////////////////
