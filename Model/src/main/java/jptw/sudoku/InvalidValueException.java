package jptw.sudoku;

public class InvalidValueException extends IllegalArgumentException {
    public InvalidValueException(final String message) {
        super(message);
    }
}
