package sudoku;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        StageSetup.buildStage(stage, "Menu.fxml", "Sudoku");
    }

}
