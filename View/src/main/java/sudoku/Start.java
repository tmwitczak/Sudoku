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


    private static final Logger logger = Logger.getLogger(Start.class.getName());

//    static {
//        String configPath = Start.class.getClassLoader().getResource("./resources/sudoku/loggerConfig.properties").getFile();
//        System.setProperty("java.util.logging.config.file", configPath);
//    }


    //============================================================ | Behaviour <
    public static void main(final String[] args) {
        logger.log(Level.INFO, "Launch");
        launch();
    }

    @Override
    public void start(final Stage stage)
            throws FileException {
        try {
            StageSetup.buildStage(stage,
                    "Menu.fxml",
                    "Sudoku");
            logger.log(Level.INFO, "Setup stage");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File exception", e);
            throw new FileException(e);
        }

    }


}


////////////////////////////////////////////////////////////////////////////////
