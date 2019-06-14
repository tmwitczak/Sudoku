///////////////////////////////////////////////////////////////////// Package //
package sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jptw.sudoku.BacktrackingSudokuSolver;
import jptw.sudoku.FileSudokuBoardDao;
import jptw.sudoku.SudokuBoard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.*;
import java.util.regex.Pattern;


//////////////////////////////////////////////////////////////// Class: Board //
public
class Board {

    private static final
    Logger logger
            = Logger.getLogger(Board.class
                                    .getName());

    //=========================================================== Behaviour ==//
    //-------------------------------------------------------------- FXML --==//
    private
    void localizeUserInterface
            (final String lang)
            throws IOException {

        InputStream inputStream
                = getClass().getClassLoader()
                            .getResourceAsStream("sudoku/Localization_"
                                                 + lang
                                                 + ".properties");

        InputStreamReader inputStreamReader
            = new InputStreamReader(inputStream,
                                    StandardCharsets.UTF_8);

        resourceBundleMenu
                = new PropertyResourceBundle(inputStreamReader);

        buttonLoad.setText(resourceBundleMenu.getString("load"));
        buttonSave.setText(resourceBundleMenu.getString("save"));
        buttonVerify.setText(resourceBundleMenu.getString("verify"));

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(
                "localization-language"))) {

            writer.write(lang);

        } catch (IOException e) {

            throw new FileException(e);
        }
    }

    @FXML
    private
    void initialize
            ()
            throws IOException {

        backtrackingSudokuSolver.solve(sudokuBoard);
        difficultyLevel.modifySudokuBoard(sudokuBoard, Menu.getLevel());
        createTextFieldsForSudokuBoard();

        String lang;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(
                "localization-language"))) {
            lang = reader.readLine();
            logger.log(Level.INFO,"Language set up.");
        } catch (Exception exception) {
            lang = "pl";
            logger.log(Level.WARNING, "Language set to default.", exception);
        }
        localizeUserInterface(lang);
    }

    @FXML
    private void onActionButtonSave(final ActionEvent actionEvent)
            throws IOException {

        onActionButtonVerify(actionEvent);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Sudoku to file");
        File file = fileChooser.showSaveDialog(((Node) actionEvent.getSource())
                .getScene().getWindow());

        try (FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(
                file.getAbsolutePath())) {
            fileSudokuBoardDao.write(sudokuBoard);
            logger.log(Level.INFO, "Board saved");
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.log(Level.WARNING, "Failed to save board", exception);
        }
    }

    @FXML
    private void onActionButtonLoad(final ActionEvent actionEvent)
            throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sudoku from file");
        File file = fileChooser.showOpenDialog(((Node) actionEvent.getSource())
                .getScene().getWindow());

        try (FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(
                file.getAbsolutePath())) {
            sudokuBoard = fileSudokuBoardDao.read();
            logger.log(Level.INFO, "Board loaded");
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.log(Level.WARNING, "Failed to load board", exception);
        }

        createTextFieldsForSudokuBoard();
    }

    @FXML
    private void onActionButtonVerify(final ActionEvent actionEvent)
            throws IOException {
        boolean isCorrect = true;
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                if (!textFields[i][j].isDisabled()) {
                    String content = textFields[i][j].getText();

                    if (!content.isEmpty()) {
                        System.out.println(content);
                        isCorrect &= sudokuBoard.set(i, j,
                                content.charAt(0) - '0');
                    }
                }
            }
        }

        if (isCorrect) {
            labelIsCorrect.setText(resourceBundleMenu.getString("correct"));
            logger.log(Level.INFO, "Board verification: Correct");
        } else {
            labelIsCorrect.setText(resourceBundleMenu.getString("notCorrect"));
            logger.log(Level.INFO, "Board verification: NotCorrect");
        }
    }

    private void createTextFieldsForSudokuBoard() {


//        TextFormatter textFormatter = new TextFormatter<>(c -> {
//            if (c.isContentChange()) {
//                if (c.getControlNewText().length() == 0) {
//                    return c;
//                }
//                try {
//                    Integer.parseInt(c.getControlNewText());
//                    return c;
//                } catch (NumberFormatException e) {
//                }
//                return null;
//
//            }
//            return c;
//        });

//        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
//            System.out.println("New double value " + newValue);
//        });

        textFields = new TextField[9][9];

        for (int i = 0;
             i < SudokuBoard.BOARD_SIZE;
             i++) {

            for (int j = 0;
                 j < SudokuBoard.BOARD_SIZE;
                 j++) {

                Pattern validNewText = Pattern.compile("[1-9 ]");
                TextFormatter textFormatter
                        = new TextFormatter<>(new CharacterStringConverter() {
                },
                        null, change -> {
                    String newText = change.getControlNewText();
                    if (validNewText.matcher(newText).matches()) {
                        return change;
                    } else {
                        return null;
                    }
                });

                TextField textField = new TextField();
                textFields[i][j] = textField;
                textField.setTextFormatter(textFormatter);
                textField.setMaxSize(60, 60);
                //textField.setFont(Font.font(20));
                textField.pseudoClassStateChanged(PseudoClass.getPseudoClass(
                        "sudoku-field"), true);
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                grid.add(textField, j, i);
            }
        }

    }

    //================================================================ Data ==//
    //-------------------------------------------------------------- FXML --==//
    @FXML
    private
    GridPane grid;

    @FXML
    private
    Button buttonVerify;

    @FXML
    private
    Button buttonSave;

    @FXML
    private
    Button buttonLoad;

    @FXML
    private
    Label labelIsCorrect;

    ResourceBundle resourceBundleMenu;


    //------------------------------------------------------- SudokuBoard --==//
    private
    TextField[][] textFields;

    private
    SudokuBoard sudokuBoard
            = new SudokuBoard();

    private
    BacktrackingSudokuSolver backtrackingSudokuSolver
            = new BacktrackingSudokuSolver();

    private
    DifficultyLevel difficultyLevel
            = new DifficultyLevel();


}


////////////////////////////////////////////////////////////////////////////////

