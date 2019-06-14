///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import java.io.*;


/////////////////////////////////////////////////// Class: FileSudokuBoardDao //
public
class FileSudokuBoardDao
        implements AutoCloseable,
                   Dao<SudokuBoard> {

    //=========================================================== Behaviour ==//
    //------------------------------------------------------ Constructors --==//
    public
    FileSudokuBoardDao
            (final String filename) {

        this.filename = filename;
    }


    public
    void finalize
            ()
            throws Exception {

        close();
    }


    //--------------------------- Interface implementation: AutoCloseable --==//
    @Override
    public
    void close
            ()
            throws Exception {
    }


    //------------------------ Interface implementation: Dao<SudokuBoard> --==//
    @Override
    public
    SudokuBoard read
            ()
            throws FileException {

        SudokuBoard sudokuBoard
                = null;

        try (FileInputStream fileInputStream
                    = new FileInputStream(filename); 
             ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream)) {

            sudokuBoard = (SudokuBoard) objectInputStream.readObject();

        } catch (IOException e) {

            throw new FileException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

        return sudokuBoard;
    }

    @Override
    public
    void write
            (final SudokuBoard obj)
            throws FileException {

        try (FileOutputStream fileOutputStream
                    = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(obj);

        } catch (IOException e) {

            throw new FileException(e);
        }
    }


    //================================================================ Data ==//
    private
    String filename;


}


////////////////////////////////////////////////////////////////////////////////

