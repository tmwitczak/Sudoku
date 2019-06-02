////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


////////////////////////////////////////////////////////////////// | Class: Menu
public class Menu {

    //============================================================ | Behaviour <
    private void localizeUserInterface(final String lang) throws IOException {
        InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream(
                        "sudoku/Localization_" + lang + ".properties");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                StandardCharsets.UTF_8);
        resourceBundleMenu = new PropertyResourceBundle(inputStreamReader);

        startButton.setText(resourceBundleMenu.getString("startGame"));
        chooseLevel.getItems().clear();
        chooseLevel.getItems().addAll(
                resourceBundleMenu.getString("difficultyEasy"),
                resourceBundleMenu.getString("difficultyMedium"),
                resourceBundleMenu.getString("difficultyHard"));
        chooseLevel.getSelectionModel().selectFirst();
        textFieldDifficulty.setText(resourceBundleMenu.getString("difficulty"));

        menuBar.getMenus().get(0).setText(resourceBundleMenu.getString(
                "localization"));
        menuBar.getMenus().get(1).setText(resourceBundleMenu.getString(
                "authors"));
        menuBar.getMenus().get(0).getItems().get(0).setText(resourceBundleMenu.getString("polish"));
        menuBar.getMenus().get(0).getItems().get(1).setText(resourceBundleMenu.getString("english"));

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(
                "localization-language"))) {
            writer.write(lang);
        }
    }

    @FXML
    private void initialize() throws IOException {
        String lang;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(
                "localization-language"))) {
            lang = reader.readLine();
        } catch (Exception exception) {
            lang = "pl";
        }
        localizeUserInterface(lang);
    }

    static int getLevel() {
        return level;
    }

    @FXML
    private void onActionStartButton(final ActionEvent actionEvent)
            throws IOException {
        Menu.level = chooseLevel
                .getSelectionModel().getSelectedIndex();
        StageSetup.buildStage("Board.fxml");

    }

    @FXML
    private void onActionPolish(final ActionEvent actionEvent)
            throws IOException {
        localizeUserInterface("pl");
    }

    @FXML
    private void onActionEnglish(final ActionEvent actionEvent)
            throws IOException {
        localizeUserInterface("en");
    }

    @FXML
    private void onActionAuthors(final ActionEvent actionEvent)
            throws IOException {
        AuthorsBundle authors = new AuthorsBundle();
        Object[][] contents = authors.getContents();
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < contents.length; i++) {
            names.append((String) contents[i][1]).append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundleMenu.getString("authors"));
        alert.setHeaderText(null);
        alert.setContentText(names.toString());

        alert.showAndWait();
    }


    //================================================================= | Data <
    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> chooseLevel;

    @FXML
    private TextField textFieldDifficulty;

    private static int level;

    ResourceBundle resourceBundleMenu;

    @FXML
    MenuBar menuBar;

}


////////////////////////////////////////////////////////////////////////////////