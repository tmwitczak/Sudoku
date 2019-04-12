//////////////////////////////////////////////////////////////////////// Package
package jptw.sudoku;

//////////////////////////////////////////////////////////////////////// Imports
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

////////////////////////////////////////////////////////// Test class definition
public class SudokuFieldTest {
    //////////////////////////////////////////////////////////////////// [Tests]
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructorWithoutArguments() {
        int expectedValue = 0;

        sudokuField = new SudokuField();
        int actualValue = sudokuField.getFieldValue();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void constructorWithValueCorrect() {
        int expectedValue = 5;

        sudokuField = new SudokuField(expectedValue);
        int actualValue = sudokuField.getFieldValue();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void constructorWithValueIncorrect() {
        int expectedValue = 12;
        String expectedMessage = "value: " + expectedValue
                + " should be within the range of [0, 9]!";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(expectedMessage);

        sudokuField = new SudokuField(expectedValue);
    }

    @Test
    public void setFieldValueCorrect() {
        sudokuField = new SudokuField();
        int expectedValue = 2;

        sudokuField.setFieldValue(expectedValue);
        int actualValue = sudokuField.getFieldValue();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setFieldValueIncorrectLower() {
        sudokuField = new SudokuField();
        int expectedValue = -2;
        String expectedMessage = "value: " + expectedValue
                + " should be within the range of [0, 9]!";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(expectedMessage);

        sudokuField.setFieldValue(expectedValue);
    }

    @Test
    public void setFieldValueIncorrectHigher() {
        sudokuField = new SudokuField();
        int expectedValue = 12;
        String expectedMessage = "value: " + expectedValue
                + " should be within the range of [0, 9]!";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(expectedMessage);

        sudokuField.setFieldValue(expectedValue);
    }

    @Test
    public void equalsThis() {
        sudokuField = new SudokuField();

        assertThat(sudokuField.equals(sudokuField), is(true));
        assertThat(sudokuField.hashCode() == sudokuField.hashCode(),
                is(true));
    }

    @Test
    public void equalsNull() {
        sudokuField = new SudokuField();

        assertThat(sudokuField.equals(null), is(false));
    }

    @Test
    public void equalsDifferentClass() {
        sudokuField = new SudokuField();
        Integer integerObject = 0;

        assertThat(sudokuField.equals(integerObject), is(false));
    }

    @Test
    public void equalsSymmetric() {
        SudokuBoard a = new SudokuBoard(),
                b = new SudokuBoard();

        assertThat(a.equals(b) && b.equals(a), is(true));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void toStringMethod(){
        sudokuField = new SudokuField(5);
        assertNotNull(sudokuField.toString());
        assertEquals(sudokuField.toString(),"5");
    }
    /////////////////////////////////////////////////////////////////// [Fields]
    private SudokuField sudokuField;

}

////////////////////////////////////////////////////////////////////////////////
