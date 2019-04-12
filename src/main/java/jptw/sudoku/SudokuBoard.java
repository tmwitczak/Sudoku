//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/////////////////////////////////////////////////////////////// Class definition
public class SudokuBoard {

    private List<List<SudokuField>> board;
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;

    SudokuBoard() {

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

    SudokuBoard(final SudokuBoard board) {

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
    int get(int x, int y) {

        if (x < 0 || x >= 9) {
            throw new IllegalArgumentException(Integer.toString(x));
        }

        if (y < 0 || y >= 9) {
            throw new IllegalArgumentException(Integer.toString(y));
        }

        return board.get(x).get(y).getFieldValue();
    }

    SudokuRow getRow(final int x) {

        List<SudokuField> row = Arrays.asList(new SudokuField[BOARD_SIZE]);

        for (int i = 0; i < row.size(); i++) {
            row.set(i, new SudokuField(board.get(x).get(i).getFieldValue()));
        }

        return new SudokuRow(row);
    }

    SudokuColumn getColumn(final int y) {

        List<SudokuField> column = Arrays.asList(new SudokuField[BOARD_SIZE]);

        for (int i = 0; i < column.size(); i++) {
            column.set(i, new SudokuField(board.get(i).get(y).getFieldValue()));

        }

        return new SudokuColumn(column);
    }

    SudokuBox getBox(final int x, final int y) {

        List<SudokuField> box = Arrays.asList(new SudokuField[BOX_SIZE * BOX_SIZE]);
        int[] boxCoordinates = getBoxCoordinates(x, y);

        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                box.set(BOX_SIZE * i + j, new SudokuField(board.get(boxCoordinates[0] + i).get(boxCoordinates[1] + j).getFieldValue()));
            }
        }

        return new SudokuBox(box);
    }

    public boolean set(int x, int y, int value) {

        if (value < 0 || value > 9) {
            throw new IllegalArgumentException(Integer.toString(value));
        }

        int oldValue = get(x, y);
        board.get(x).set(y, new SudokuField(value));

        if (!checkBoard()) {
            board.get(x).set(y, new SudokuField(oldValue));
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

        return new EqualsBuilder().append(this.board,board.board).isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(11,17).append(this.board).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Board",this.board).toString();
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
