////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


///////////////////////////////////////////////////////////////// | Class: Start
public class Start
        extends Application {

    //============================================================ | Behaviour <
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void start(final Stage stage)
            throws IOException {
        StageSetup.buildStage(stage,
                              "Menu.fxml",
                              "Sudoku");
    }


}


////////////////////////////////////////////////////////////////////////////////
