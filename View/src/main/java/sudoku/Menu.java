package sudoku;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Menu {

    @FXML
    private Button acceptLevel;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox chooseLevel;
    private static String level;

    public static String getLevel() {
        return level;
    }

    @FXML
    private void onActionStartButton(final ActionEvent actionEvent) throws IOException {
        StageSetup.buildStage("Board.fxml");
    }

    @FXML
    private void onActionAcceptLevel(final ActionEvent actionEvent) {
        Menu.level = chooseLevel
                .getSelectionModel().getSelectedItem().toString();

    }

}
