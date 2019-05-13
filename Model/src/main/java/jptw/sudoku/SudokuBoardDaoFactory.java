package jptw.sudoku;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(final String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
