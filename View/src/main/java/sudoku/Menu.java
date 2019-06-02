////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;


////////////////////////////////////////////////////////////////// | Class: Menu
public class Menu {

    //============================================================ | Behaviour <
    @FXML
    private void initialize() {
        chooseLevel.getSelectionModel().selectFirst();
    }

    static String getLevel() {
        return level;
    }

    @FXML
    private void onActionStartButton(final ActionEvent actionEvent)
            throws IOException {
        Menu.level = chooseLevel
                .getSelectionModel().getSelectedItem().toString();
        StageSetup.buildStage("Board.fxml");
    }


    //================================================================= | Data <
    @FXML
    private Button startButton;

    @FXML
    private ComboBox chooseLevel;

    private static String level;


}


////////////////////////////////////////////////////////////////////////////////
