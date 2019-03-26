//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

////////////////////////////////////////////////////////// Test class definition
public class SudokuFieldArrayTest {

    //////////////////////////////////////////////////////////////////// [Tests]
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void verifyCorrect() {

        // Given
        final int NUMBER_OF_FIELDS = 9;
        SudokuField[] sudokuFields = new SudokuField[NUMBER_OF_FIELDS];

        int[] sudokuFieldsInteger = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < sudokuFields.length; i++) {
            sudokuFields[i] = new SudokuField(sudokuFieldsInteger[i]);
        }

        // When
        SudokuFieldArray sudokuFieldArray = new SudokuFieldArray(sudokuFields);
        boolean verifyResult = sudokuFieldArray.verify();

        // Then
        assertThat(verifyResult, is(true));
    }

    @Test
    public void verifyIncorrect() {

        // Given
        final int NUMBER_OF_FIELDS = 9;
        SudokuField[] sudokuFields = new SudokuField[NUMBER_OF_FIELDS];

        int[] sudokuFieldsInteger = new int[]{1, 2, 1, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < sudokuFields.length; i++) {
            sudokuFields[i] = new SudokuField(sudokuFieldsInteger[i]);
        }

        // When
        SudokuFieldArray sudokuFieldArray = new SudokuFieldArray(sudokuFields);
        boolean verifyResult = sudokuFieldArray.verify();

        // Then
        assertThat(verifyResult, is(false));
    }

    @Test
    public void constructIncorrectNumberOfFields() {

        // Given
        final int NUMBER_OF_FIELDS = 7;
        SudokuField[] sudokuFields = new SudokuField[NUMBER_OF_FIELDS];

        int[] sudokuFieldsInteger = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < sudokuFields.length; i++) {
            sudokuFields[i] = new SudokuField(sudokuFieldsInteger[i]);
        }

        // Then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "fields should contain 9 elements!");

        // When
        new SudokuFieldArray(sudokuFields);
    }

}

////////////////////////////////////////////////////////////////////////////////
