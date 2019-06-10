package jptw.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void readAndWrite() {

        SudokuBoardDaoFactory sudokuBoardDaoFactory
                = new SudokuBoardDaoFactory();

        try (JdbcSudokuBoardDao jdbcSudokuBoardDao =
                     sudokuBoardDaoFactory.getJdbcDao("siemka")) {

            BacktrackingSudokuSolver backtrackingSudokuSolver =
                    new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard1 = new SudokuBoard();
            backtrackingSudokuSolver.solve(sudokuBoard1);
            jdbcSudokuBoardDao.write(sudokuBoard1);

            SudokuBoard sudokuBoard2 = jdbcSudokuBoardDao.read();

            assertEquals(sudokuBoard1, sudokuBoard2);
        }
    }
}