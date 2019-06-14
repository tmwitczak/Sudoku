///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;

import java.sql.SQLException;

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
    JdbcSudokuBoardDao getJdbcDao(final String filename) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        return new JdbcSudokuBoardDao(filename);
    }


}


////////////////////////////////////////////////////////////////////////////////

