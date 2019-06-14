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

//    static {
//        String configPath = Start.class.getClassLoader().getResource("./resources/sudoku/loggerConfig.properties").getFile();
//        System.setProperty("java.util.logging.config.file", configPath);
//    }


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

        } catch (IOException e) {

            logger.log(Level.SEVERE,
                       "File exception",
                       e);

            throw new FileException(e);
        }
    }

    //================================================================ Data ==//
    //------------------------------------------------------------ Logger --==//
    private static final
    Logger logger
            = Logger.getLogger(Start.class.getName());


}


////////////////////////////////////////////////////////////////////////////////
