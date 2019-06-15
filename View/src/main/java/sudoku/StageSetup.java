////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//////////////////////////////////////////////////////////// | Class: StageSetup
public class StageSetup {


    //============================================================ | Behaviour <
    public static Stage getStage() {
        return stage;
    }

    private static void setStage(final Stage stage) {
        StageSetup.stage = stage;
    }

    private static Parent loadFxml(final String fxml)
            throws IOException {
        return new FXMLLoader(StageSetup.class.getResource(fxml)).load();
    }

    static void buildStage(final String filePath)
            throws IOException {
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    static void buildStage(final Stage stage,
                           final String filePath,
                           final String title)
            throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath)));

        stage.getScene().getStylesheets().add(
                "https://fonts.googleapis.com/css?family="
                + "Montserrat&display=swap");

        stage.setTitle(title);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }


    //================================================================= | Data <
    private static Stage stage;


}

////////////////////////////////////////////////////////////////////////////////
