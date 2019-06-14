///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/////////////////////////////////////////////// Class: JdbcSudokuBoardDaoTest //
public
class JdbcSudokuBoardDaoTest {

    //=========================================================== Behaviour ==//
    //------------------------------------------------------------- Tests --==//
    //................................................... IO operations ..--==//
    @Test
    public
    void writeSudokuBoardToAndReadFromDatabase()
            throws Exception,
                   InstantiationException,
                   IllegalAccessException,
                   ClassNotFoundException {

        // Given:
        final
        SudokuBoard expectedSudokuBoard
                = new SudokuBoard();

        (new BacktrackingSudokuSolver()).solve(expectedSudokuBoard);

        final
        String sudokuBoardName
                = "sudokuBoardTest";

        // When:
        SudokuBoard actualSudokuBoard;
        try (final
             JdbcSudokuBoardDao jdbcSudokuBoardDao
                     = (new SudokuBoardDaoFactory())
                               .getJdbcDao(sudokuBoardName)) {

            jdbcSudokuBoardDao.write(expectedSudokuBoard);

            actualSudokuBoard = jdbcSudokuBoardDao.read();
        }

        // Then:
        assertEquals(expectedSudokuBoard,
                     actualSudokuBoard);
    }


}


////////////////////////////////////////////////////////////////////////////////

