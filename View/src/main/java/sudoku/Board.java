///////////////////////////////////////////////////////////////////// Package //
package sudoku;

///////////////////////////////////////////////////////////////////// Imports //

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import jptw.sudoku.BacktrackingSudokuSolver;
import jptw.sudoku.Dao;
import jptw.sudoku.SudokuBoard;
import jptw.sudoku.SudokuBoardDaoFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private void localizeUserInterface(final String lang)
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
    private void initialize()
            throws IOException {

        backtrackingSudokuSolver.solve(sudokuBoard);
        difficultyLevel.modifySudokuBoard(sudokuBoard, Menu.getLevel());
        createTextFieldsForSudokuBoard();

        String lang;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(
                "localization-language"))) {
            lang = reader.readLine();
            logger.log(Level.INFO, "Language set up.");
        } catch (Exception exception) {
            lang = "pl";
            logger.log(Level.WARNING, "Language set to default.", exception);
        }
        localizeUserInterface(lang);
    }

    @FXML
    private void onActionButtonSave(final ActionEvent actionEvent)
            throws IOException {

        //onActionButtonVerify(actionEvent);

        TextInputDialog dialog = new TextInputDialog(null);
        dialog.setTitle("Save Sudoku Board");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter sudoku name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            try (Dao<SudokuBoard> jdbcSudokuBoardDao
                         = (new SudokuBoardDaoFactory())
                    .getJdbcDao(result.get())) {

                jdbcSudokuBoardDao.write(sudokuBoard);
                logger.log(Level.INFO, "Board saved");

            } catch (Exception exception) {
                exception.printStackTrace();
                logger.log(Level.WARNING, "Failed to save board", exception);
            }
        }
    }

    @FXML
    private void onActionButtonLoad(final ActionEvent actionEvent)
            throws IOException {

        TextInputDialog dialog = new TextInputDialog(null);
        dialog.setTitle("Load Sudoku Board");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter sudoku name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            try (Dao<SudokuBoard> jdbcSudokuBoardDao
                         = (new SudokuBoardDaoFactory())
                    .getJdbcDao(result.get())) {
                sudokuBoard = jdbcSudokuBoardDao.read();
                if (sudokuBoard != null) {
                    logger.log(Level.INFO, "Board loaded");

                    createTextFieldsForSudokuBoard();
                    labelIsCorrect.setText("");

                    return;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            logger.log(Level.WARNING, "Failed to load board: ");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Sudoku Board Error");
            alert.setHeaderText(null);
            alert.setContentText("Sudoku Board: " + result.get()
                    + " doesn't exist and couldn't be loaded!");

            alert.showAndWait();
        }

    }

    @FXML
    private void onActionButtonVerify(final ActionEvent actionEvent)
            throws IOException {
        boolean isCorrect = sudokuBoard.checkBoard();

        boolean win = true;
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                if (sudokuBoard.get(i, j).getFieldValue() == 0) {
                    win = false;
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

        if (win) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Win");
            alert.setHeaderText("Congratulations!");
            alert.setContentText("You won!");

            alert.showAndWait();

            ((Stage) grid.getScene().getWindow()).close();
        }
    }

    private void createTextFieldsForSudokuBoard() {

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
                        null,
                        change -> {

                            String newText = change.getControlNewText();

                            if (validNewText.matcher(newText).matches()) {
                                return change;
                            } else {
                                return null;
                            }
                        });

                TextField textField = new TextField();
                textFields[i][j] = textField;

                final int x = i;
                final int y = j;

                textField.setTextFormatter(textFormatter);
                textField.setOnKeyReleased(event -> {
                    int value;
                    try {
                        value = (((TextField) event.getSource())
                                .getText()
                                .charAt(0) - '0');
                        sudokuBoard.set(x, y, value);
                    } catch (Exception e) {
                        sudokuBoard.set(x, y, 0);
                    }
                    logger.log(Level.INFO, "Setting (" + x + ", " + y + ") "
                            + "sudoku field to value: "
                            + sudokuBoard.get(x, y).getFieldValue());
                });
                textField.setMaxSize(60, 60);
                //textField.setFont(Font.font(20));
                textField.pseudoClassStateChanged(PseudoClass.getPseudoClass(
                        "sudoku-field"), true);
                if (sudokuBoard.get(i, j).getFieldValue() != 0) {
                    textField.setDisable(!sudokuBoard.get(i, j).getFieldMutability());
                    textField.setText(String.valueOf(sudokuBoard.get(i, j).getFieldValue()));
                } else {
                    textField.setText(" ");
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

