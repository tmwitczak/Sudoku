package sudoku;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void start(final Stage stage) throws IOException {
        StageSetup.buildStage(stage, "Menu.fxml", "Sudoku");
    }

}
