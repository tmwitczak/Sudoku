///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/////////////////////////////////////////////////// Class: FileSudokuBoardDao //
public
class FileSudokuBoardDao
        implements AutoCloseable,
                   Dao<SudokuBoard> {

    //=========================================================== Behaviour ==//
    //------------------------------------------------------ Constructors --==//
    public
    FileSudokuBoardDao(final String filename) {

        this.filename = filename;
    }


    public
    void finalize()
            throws Exception {

        close();
    }


    //--------------------------- Interface implementation: AutoCloseable --==//
    @Override
    public
    void close()
            throws Exception {
    }


    //------------------------ Interface implementation: Dao<SudokuBoard> --==//
    @Override
    public
    SudokuBoard read()
            throws FileException {

        SudokuBoard sudokuBoard
                = null;

        try (FileInputStream fileInputStream
                    = new FileInputStream(filename); 
             ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream)) {

            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            logger.log(Level.INFO,"Read from file: ".concat(filename));
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error initializing stream", e);
            throw new FileException(e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE,"Class not found", e);
            throw new FileException(e);
        }

        return sudokuBoard;
    }

    @Override
    public
    void write(final SudokuBoard obj)
            throws FileException {

        try (FileOutputStream fileOutputStream
                    = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(obj);
            logger.log(Level.INFO,"Wrote to file: ".concat(filename));
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error initializing stream", e);
            throw new FileException(e);
        }
    }


    //================================================================ Data ==//
    private
    String filename;
    private static final Logger logger = Logger.getLogger(FileSudokuBoardDao.class.getName());

}


////////////////////////////////////////////////////////////////////////////////

