package sudoku;


import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import jptw.sudoku.BacktrackingSudokuSolver;
import jptw.sudoku.SudokuBoard;

public class Board {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private GridPane grid;

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private Difficulty difficultyLevel = new Difficulty();

    /*------------------------ METHODS REGION ------------------------*/
    @FXML
    private void initialize() {
        solver.solve(sudokuBoard);
        difficultyLevel.modifySudokuBoard(sudokuBoard, Menu.getLevel());
        fill();
    }

    /**
     * Method fill GripPane in .fxml file.
     */
    private void fill() {
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(20));
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i,j)));
                }
                grid.add(textField, j, i);
            }
        }
    }
}
