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
    static String getLevel() {
        return level;
    }

    @FXML
    private void onActionStartButton(final ActionEvent actionEvent)
            throws IOException {
        StageSetup.buildStage("Board.fxml");
    }

    @FXML
    private void onActionAcceptLevel(final ActionEvent actionEvent) {
        Menu.level = chooseLevel
                .getSelectionModel().getSelectedItem().toString();

    }


    //================================================================= | Data <
    @FXML
    private Button acceptLevel;

    @FXML
    private Button startButton;

    @FXML
    private ComboBox chooseLevel;

    private static String level;


}


////////////////////////////////////////////////////////////////////////////////
