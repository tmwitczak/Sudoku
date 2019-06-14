///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


//////////////////////////////////////////////////////////// Class: SudokuBox //
public
class SudokuBoardDaoFactory {

    //=========================================================== Behaviour ==//
    //-------------------------------------------------------------- Main --==//
    public
    FileSudokuBoardDao getFileDao(final String filename) {

        return new FileSudokuBoardDao(filename);
    }

    public
    JdbcSudokuBoardDao getJdbcDao(final String filename) {

        return new JdbcSudokuBoardDao(filename);
    }


}


////////////////////////////////////////////////////////////////////////////////

