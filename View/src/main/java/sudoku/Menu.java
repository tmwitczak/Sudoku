package sudoku;

import jptw.sudoku.SudokuBoard;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;


public class Menu {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private Button acceptLevel;
    @FXML
    private Button startButton;

    @FXML
    private ComboBox chooseLevel;

    private static String level;

    /*------------------------ METHODS REGION ------------------------*/
    public static String getLevel() {
        return level;
    }

    @FXML
    private void onActionStartButton(ActionEvent actionEvent) throws IOException {
        StageSetup.buildStage("Board.fxml");
    }

    @FXML
    private void onActionAcceptLevel(ActionEvent actionEvent) {
        Menu.level = chooseLevel
                .getSelectionModel().getSelectedItem().toString();

    }



}
