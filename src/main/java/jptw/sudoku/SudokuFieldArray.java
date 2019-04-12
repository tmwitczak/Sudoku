//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/////////////////////////////////////////////////////////////// Class definition
class SudokuFieldArray {

    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------------- Constructors <
    SudokuFieldArray(final List<SudokuField> fields) {

        if (fields.size() != NUMBER_OF_FIELDS) {
            throw new IllegalArgumentException(
                    "fields should contain 9 elements!");
        }

        this.sudokuFields = Arrays.asList(new SudokuField[NUMBER_OF_FIELDS]);

        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            this.sudokuFields.set(i, fields.get(i));
        }

    }

    //----------------------------------------------------------- Verification <
    boolean verify() {

        return !checkForDuplicatesWhileIgnoringZeros(sudokuFields);
    }

    //------------------------------------------------------- Helper functions <
    private boolean checkForDuplicatesWhileIgnoringZeros(
            final List<SudokuField> fields) {

        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            if (fields.get(i).getFieldValue() != 0) {
                for (int j = i + 1; j < NUMBER_OF_FIELDS; j++) {
                    if (fields.get(j).getFieldValue() != 0) {
                        if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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

        SudokuFieldArray fields = (SudokuFieldArray) object;
        return new EqualsBuilder().append(this.sudokuFields, fields.sudokuFields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 17).append(this.sudokuFields).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Array of fields:", this.sudokuFields).toString();
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private List<SudokuField> sudokuFields;
    private static final int NUMBER_OF_FIELDS = 9;

}

////////////////////////////////////////////////////////////////////////////////
