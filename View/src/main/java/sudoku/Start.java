///////////////////////////////////////////////////////////////////// Package //
package sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//////////////////////////////////////////////////////////////// Class: Start //
public
class Start
        extends Application {


    private static final Logger logger = Logger.getLogger(Start.class.getName());
    static {
        System.setProperty("java.util.logging.config.file", "./resources/sudoku/logging.properties");
    }


    //=========================================================== Behaviour ==//
    //-------------------------------------------------------------- Main --==//
    public static
    void main(final String[] args) {

        logger.log(Level.INFO,
                   "Launch");

        launch();
    }

    @Override
    public
    void start(final Stage stage)
            throws FileException {

        try {
            StageSetup.buildStage(stage,
                                 "Menu.fxml",
                                 "Sudoku");

            logger.log(Level.INFO,
                       "Setup stage");

        } catch (IOException ioException) {

            logger.log(Level.SEVERE,
                       "File exception",
                       ioException);

            throw new FileException(ioException);
        }
    }
    //================================================================ Data ==//
}


////////////////////////////////////////////////////////////////////////////////
