package jptw.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileSudokuBoardDaoTest {

    @Test
    public void writeAndRead() {
        // Given:
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("test");
        SudokuBoard sudokuBoard1 = new SudokuBoard();

        // When:
        fileSudokuBoardDao.write(sudokuBoard1);
        SudokuBoard sudokuBoard2 = fileSudokuBoardDao.read();

        // Then:
        assertEquals(sudokuBoard1, sudokuBoard2);
    }
}