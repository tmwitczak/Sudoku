//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

////////////////////////////////////////////////////////// Test class definition
public class SudokuFieldArrayTest {

    //////////////////////////////////////////////////////////////////// [Tests]
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void verifyCorrect() {

        // Given
        List<SudokuField> sudokuFields = Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9));

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
        List<SudokuField> sudokuFields = Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(3),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(9),
                new SudokuField(9));

        // When
        SudokuFieldArray sudokuFieldArray = new SudokuFieldArray(sudokuFields);
        boolean verifyResult = sudokuFieldArray.verify();

        // Then
        assertThat(verifyResult, is(false));
    }

    @Test
    public void constructIncorrectNumberOfFields() {

        // Given
        List<SudokuField> sudokuFields =(Arrays.asList(
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7)));

        // Then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "fields should contain 9 elements!");

        // When
        new SudokuFieldArray(sudokuFields);
    }

    @Test
    public void toStringMethod()
    {
        List<SudokuField> sudokuFields = Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9));
        assertNotNull(sudokuFields.toString());
        assertEquals(sudokuFields.toString(),"[1, 2, 3, 4, 5, 6, 7, 8, 9]");
    }
}

////////////////////////////////////////////////////////////////////////////////
