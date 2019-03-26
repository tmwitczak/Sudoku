//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/////////////////////////////////////////////////////////////// Class definition
class SudokuFieldArray {

    ////////////////////////////////////////////////////////////////// [Methods]
    //----------------------------------------------------------- Constructors <
    SudokuFieldArray(final SudokuField[] fields) {

        if (fields.length != NUMBER_OF_FIELDS) {
            throw new IllegalArgumentException(
                    "fields should contain 9 elements!");
        }

        sudokuFields = Arrays.copyOf(fields, fields.length);
    }

    //----------------------------------------------------------- Verification <
    boolean verify() {

        return !checkForDuplicatesWhileIgnoringZeros(sudokuFields);
    }

    //------------------------------------------------------- Helper functions <
    private boolean checkForDuplicatesWhileIgnoringZeros(
            final SudokuField[] fields) {

        Set<SudokuField> fieldsSet = new HashSet<>();

        for (SudokuField field : fields) {
            if (field.getFieldValue() != 0) {
                if (fieldsSet.contains(field)) {
                    return true;
                }
                fieldsSet.add(field);
            }
        }

        return false;
    }

    /////////////////////////////////////////////////////////////////// [Fields]
    private SudokuField[] sudokuFields;
    private static final int NUMBER_OF_FIELDS = 9;
    
}

////////////////////////////////////////////////////////////////////////////////
