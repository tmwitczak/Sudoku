////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports

import javafx.css.PseudoClass;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jptw.sudoku.BacktrackingSudokuSolver;
import jptw.sudoku.SudokuBoard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.regex.Pattern;


///////////////////////////////////////////////////////////////// | Class: Board
public class Board {

    //============================================================ | Behaviour <
    //---------------------------------------------------------------- | FXML <<
    @FXML
    private void initialize() {
        backtrackingSudokuSolver.solve(sudokuBoard);
        difficultyLevel.modifySudokuBoard(sudokuBoard, Menu.getLevel());
        createTextFieldsForSudokuBoard();
    }

    private void createTextFieldsForSudokuBoard() {



//        TextFormatter textFormatter = new TextFormatter<>(c -> {
//            if (c.isContentChange()) {
//                if (c.getControlNewText().length() == 0) {
//                    return c;
//                }
//                try {
//                    Integer.parseInt(c.getControlNewText());
//                    return c;
//                } catch (NumberFormatException e) {
//                }
//                return null;
//
//            }
//            return c;
//        });

//        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
//            System.out.println("New double value " + newValue);
//        });

        for (int i = 0;
             i < SudokuBoard.BOARD_SIZE;
             i++) {

            for (int j = 0;
                 j < SudokuBoard.BOARD_SIZE;
                 j++) {

                Pattern validNewText = Pattern.compile("[1-9 ]");
                TextFormatter textFormatter
                        = new TextFormatter<>(new CharacterStringConverter() {},
                                              null, change -> {
                            String newText = change.getControlNewText();
                            if (validNewText.matcher(newText).matches()) {
                                return change;
                            } else {
                                return null;
                            }
                        });

                TextField textField = new TextField();
                textField.setTextFormatter(textFormatter);
                textField.setMaxSize(60, 60);
                //textField.setFont(Font.font(20));
                textField.pseudoClassStateChanged(PseudoClass.getPseudoClass(
                        "sudoku-field"), true);
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                grid.add(textField, j, i);
            }
        }

    }

    //================================================================= | Data <
    //---------------------------------------------------------------- | FXML <<
    @FXML
    private GridPane grid;

    //--------------------------------------------------------- | SudokuBoard <<
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver backtrackingSudokuSolver
            = new BacktrackingSudokuSolver();
    private DifficultyLevel difficultyLevel = new DifficultyLevel();


}

////////////////////////////////////////////////////////////////////////////////
