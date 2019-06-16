package jptw.sudoku;

import java.sql.SQLException;

public class DBException extends SQLException {
    public DBException(final Throwable cause) {
        super(cause);
    }
}
