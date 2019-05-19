package sudoku;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageSetup {

    /*------------------------ FIELDS REGION ------------------------*/
    private static Stage stage;

    /*------------------------ METHODS REGION ------------------------*/
    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        StageSetup.stage = stage;
    }

    private static Parent loadFxml(String fxml) throws IOException {
        return new FXMLLoader(StageSetup.class.getResource(fxml)).load();
    }

    public static void buildStage(String filePath) throws IOException {
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(Stage stage, String filePath, String title) throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }
}