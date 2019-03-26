//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.Objects;

/////////////////////////////////////////////////////////////// Class definition
class SudokuField {
    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------------- Constructors <
    public SudokuField() {
        this(0);
    }

    public SudokuField(final int value) {
        setFieldValue(value);
    }

    //-------------------------------------------------------------- Accessors <
    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("value: " + value
                    + " should be within the range of [0, 9]!");
        }
        this.value = value;
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
        SudokuField field = (SudokuField) object;
        return field.value == this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private int value;
}

////////////////////////////////////////////////////////////////////////////////
