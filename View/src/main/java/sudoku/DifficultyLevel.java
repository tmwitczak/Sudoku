////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import jptw.sudoku.SudokuBoard;


//////////////////////////////////////////////////////////// | Class: DifficultyLevel
public class DifficultyLevel {

    //============================================================ | Behaviour <
    private void fillRandomPositionsList(final int numberOfSpots) {

        for (int i = 0; i < numberOfSpots; i++) {
            boolean flag = false;
            while (!flag) {
                int x = randomNumberGenerator.nextInt(9);
                int y = randomNumberGenerator.nextInt(9);
                flag = position.add(new Field(x, y));
            }
        }
    }

    SudokuBoard modifySudokuBoard(final SudokuBoard sudokuBoard,
                                  final String level) {

        switch (level) {
            case "Easy": {
                fillRandomPositionsList(MISSING_SPOTS[0]);
                break;
            }
            case "Medium": {
                fillRandomPositionsList(MISSING_SPOTS[1]);
                break;
            }
            case "Hard": {
                fillRandomPositionsList(MISSING_SPOTS[2]);
                break;
            }
            default: {
                fillRandomPositionsList(MISSING_SPOTS[0]);
            }
        }

        for (Field it : position) {
            sudokuBoard.set(it.getX(), it.getY(), 0);
        }
        return sudokuBoard;
    }

    //================================================================= | Data <
    private static final int[] MISSING_SPOTS = {10, 20, 30};

    private Random randomNumberGenerator = new Random();
    private Set<Field> position = new HashSet<>();


}


////////////////////////////////////////////////////////////////////////////////
