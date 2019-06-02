////////////////////////////////////////////////////////////////////// | Package
package sudoku;


////////////////////////////////////////////////////////////////////// | Imports

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;


///////////////////////////////////////////////////////////////// | Class: Start
public class Start
        extends Application {

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = Logger.getLogger(Start.class.getName());
    static{
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./start.log");
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            logManager.readConfiguration(new FileInputStream("./loggerConfig.properties"));

        } catch (IOException e) {
            logger.log(Level.SEVERE, "FileOperationException: ", e);
        }

    }


    //============================================================ | Behaviour <
    public static void main(final String[] args) {
        logger.log(Level.WARNING, "working");
        launch();
    }

    @Override
    public void start(final Stage stage)
            throws IOException {
        try {
            StageSetup.buildStage(stage,
                    "Menu.fxml",
                    "Sudoku");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException: ", e);
        }

    }


}


////////////////////////////////////////////////////////////////////////////////
