package sudoku;

import jptw.sudoku.SudokuBoard;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Difficulty {

    private static final int[] missingSpots = {5, 9, 13};

    private Random rand = new Random();
    private Set<Field> position = new HashSet<>();

    private void fillRandomPositionsList(int spots) {
        for (int i = 0; i < spots; i++) {
            boolean flag = false;

            while (!flag) {
                int x = rand.nextInt(9);
                int y = rand.nextInt(9);
                flag = position.add(new Field(x, y));
            }
        }
    }

    public SudokuBoard modifySudokuBoard(final SudokuBoard sudokuBoard, final String level) {

        switch (level) {
            case "Easy": {
                fillRandomPositionsList(missingSpots[0]);
                break;
            }
            case "Medium": {
                fillRandomPositionsList(missingSpots[1]);
                break;
            }
            case "Hard": {
                fillRandomPositionsList(missingSpots[2]);
                break;
            }
            default: {
                fillRandomPositionsList(missingSpots[0]);
            }
        }

        for (Field it : position) {
            sudokuBoard.set(it.getX(), it.getY(), 0);
        }
        return sudokuBoard;
    }
}