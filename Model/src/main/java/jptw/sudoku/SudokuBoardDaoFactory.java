package jptw.sudoku;

public class SudokuBoardDaoFactory {
    public FileSudokuBoardDao getFileDao(final String filename) {
        return new FileSudokuBoardDao(filename);
    }
    public JdbcSudokuBoardDao getJdbcDao(final String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
