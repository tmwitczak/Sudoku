///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


////////////////////////////////////////////////////// Class: SudokuFieldTest //
public
class SudokuFieldTest {

    //=========================================================== Behaviour ==//
    //------------------------------------------------------------- Tests --==//
    @Rule
    public final
    ExpectedException expectedException
            = ExpectedException.none();

    @Test
    public
    void constructorWithoutArguments() {

        // Given:
        final
        int expectedFieldValue = 0;

        // When:
        final
        int actualFieldValue = (new SudokuField()).getFieldValue();

        // Then:
        assertEquals(expectedFieldValue,
                     actualFieldValue);
    }

    @Test
    public
    void constructorWithValueCorrect() {

        // Given:
        final
        int expectedFieldValue = 5;

        // When:
        final
        int actualValue = (new SudokuField(expectedFieldValue)).getFieldValue();

        // Then:
        assertEquals(expectedFieldValue,
                     actualValue);
    }

    @Test
    public
    void constructorWithValueIncorrect() {

        // Given:
        final
        int expectedFieldValue = 12;

        // Expect:
        expectedException.expect(InvalidValueException.class);
        expectedException.expectMessage("value: "
                                         + expectedFieldValue
                                         + " should be within the range"
                                           + " of [0, 9]!");

        // When:
        new SudokuField(expectedFieldValue);
    }

    @Test
    public
    void setFieldValueCorrect() {

        // Given:
        final
        int expectedSudokuFieldValue = 2;

        // When:
        final
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(expectedSudokuFieldValue);

        int actualSudokuFieldValue = sudokuField.getFieldValue();

        // Then:
        assertEquals(expectedSudokuFieldValue,
                     actualSudokuFieldValue);
    }

    @Test
    public
    void setFieldValueIncorrectLower() {

        // Given:
        final
        int incorrectSudokuFieldValue = -2;

        // Expect:
        expectedException.expect(InvalidValueException.class);
        expectedException.expectMessage("value: "
                                        + incorrectSudokuFieldValue
                                        + " should be within the range"
                                          + " of [0, 9]!");

        // When:
        (new SudokuField()).setFieldValue(incorrectSudokuFieldValue);
    }

    @Test
    public
    void setFieldValueIncorrectHigher() {

        // Given:
        int incorrectSudokuFieldValue = 12;

        // Expect:
        expectedException.expect(InvalidValueException.class);
        expectedException.expectMessage("value: "
                                        + incorrectSudokuFieldValue
                                        + " should be within the range"
                                          + " of [0, 9]!");

        // When:
        (new SudokuField()).setFieldValue(incorrectSudokuFieldValue);
    }

    @Test
    public
    void equalsThis() {

        // Given:
        final
        SudokuField sudokuField = new SudokuField();

        // Then:
        assertTrue(sudokuField.equals(sudokuField));

        assertTrue(sudokuField.hashCode() == sudokuField.hashCode());
    }

    @Test
    public
    void equalsNull() {

        // Given:
        final
        SudokuField sudokuField = new SudokuField();

        // Then:
        assertNotNull(sudokuField);

        assertNotEquals(sudokuField, null);
    }

    @Test
    public
    void equalsDifferentClass() {
        
        // Given:
        final
        SudokuField sudokuField = new SudokuField();

        final
        Integer integer = 0;

        // Then:
        assertFalse(sudokuField.equals(integer));
    }

    @Test
    public
    void equalsSymmetric() {

        // Given:
        SudokuField sudokuField1 = new SudokuField(),
                    sudokuField2 = new SudokuField();

        // Then:
        assertTrue(sudokuField1.equals(sudokuField2)
                   &&
                   sudokuField2.equals(sudokuField1));

        assertEquals(sudokuField1.hashCode(),
                     sudokuField2.hashCode());
    }

    @Test
    public
    void toStringMethod() {

        // Given:
        final
        int expectedSudokuFieldValue = 5;

        final
        String expectedToStringValue = "5";

        // When:
        final
        SudokuField sudokuField = new SudokuField(expectedSudokuFieldValue);

        // Then:
        assertNotNull(sudokuField.toString());

        assertEquals(expectedToStringValue,
                     sudokuField.toString());
    }

    @Test
    public
    void compareEqual() {

        // Given:
        final
        int expectedComparisonResult = 0;

        final
        SudokuField field1 = new SudokuField(4),
                    field2 = new SudokuField(4);

        // Then:
        assertEquals(expectedComparisonResult,
                     field1.compareTo(field2));
    }

    @Test
    public
    void compareLess() {

        // Given:
        final
        int expectedComparisonResult = -1;

        final
        SudokuField field1 = new SudokuField(1),
                    field2 = new SudokuField(4);

        // Then:
        assertEquals(expectedComparisonResult,
                     field1.compareTo(field2));
    }

    @Test
    public
    void compareGreater() {

        // Given:
        final
        int expectedComparisonResult = 1;

        final
        SudokuField field1 = new SudokuField(4),
                    field2 = new SudokuField(1);

        // Then:
        assertEquals(expectedComparisonResult,
                     field1.compareTo(field2));
    }

    @Test
    public
    void cloneTest() {

        // Given:
        final
        int sudokuFieldValue = 3;

        final
        SudokuField sudokuFieldOriginal = new SudokuField(sudokuFieldValue);

        // When:
        SudokuField sudokuFieldCloned
                = (SudokuField) sudokuFieldOriginal.clone();

        // Then:
        assertTrue(sudokuFieldOriginal.equals(sudokuFieldCloned)
                   && sudokuFieldCloned.equals(sudokuFieldOriginal));

        assertEquals(sudokuFieldOriginal.hashCode(),
                     sudokuFieldCloned.hashCode());
    }


}


////////////////////////////////////////////////////////////////////////////////
