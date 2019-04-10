//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports

import java.util.Arrays;
import java.util.List;

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

        return this.sudokuFields.equals(fields);
    }

    @Override
    public int hashCode() {

        return sudokuFields.hashCode();
    }

    @Override
    public String toString() {
        String s = "";
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
                sb.append(sudokuFields.get(i).toString());
        }
        return sb.toString();
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private List<SudokuField> sudokuFields;
    private static final int NUMBER_OF_FIELDS = 9;

}

////////////////////////////////////////////////////////////////////////////////
