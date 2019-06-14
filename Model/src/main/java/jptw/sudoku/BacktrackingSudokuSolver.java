///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;


///////////////////////////////////////////// Class: BacktrackingSudokuSolver //
public
class BacktrackingSudokuSolver
    implements SudokuSolver {

    //=========================================================== Behaviour ==//
    //-------------------------------------------------------------- Main --==//
    @Override
    public
    boolean solve(final SudokuBoard board) {

        Pair<Integer, Integer> emptyFieldCoordinates
                = findEmptyField(board);

        if (emptyFieldCoordinates == null) {

            if (board.equals(previousBoard)) {
                return false;
            }

            previousBoard = new SudokuBoard(board);

            return true;
        }

        int i = emptyFieldCoordinates.getKey();
        int j = emptyFieldCoordinates.getValue();

        ArrayList<Integer> randomSudokuNumbers
                = generateRandomSudokuNumbers();

        for (int randomSudokuNumber
                : randomSudokuNumbers) {

            board.set(i, j, randomSudokuNumber);

            if (board.checkBoard()) {

                if (solve(board)) {

                    return true;
                }
            }
        }

        board.set(i, j, 0);

        return false;
    }


    //-------------------------------------------------- Helper functions --==//
    private
    Pair<Integer, Integer> findEmptyField(final SudokuBoard board) {

        for (int i = 0;
             i < BOARD_SIZE;
             i++) {

            for (int j = 0;
                 j < BOARD_SIZE;
                 j++) {

                if (board.get(i, j).getFieldValue() == 0) {
                    return new Pair<>(i, j);
                }
            }
        }

        return null;
    }

    private
    ArrayList<Integer> generateRandomSudokuNumbers() {

        ArrayList<Integer> randomSudokuNumbers
                = new ArrayList<>();

        for (int i = 0;
             i < BOARD_SIZE;
             i++) {

            randomSudokuNumbers.add(i + 1);
        }

        Collections.shuffle(randomSudokuNumbers);

        return randomSudokuNumbers;
    }


    //================================================================ Data ==//
    private static final
    int BOARD_SIZE = 9;

    private static
    SudokuBoard previousBoard = new SudokuBoard();


}


////////////////////////////////////////////////////////////////////////////////

