package jptw.sudoku;

import java.io.*;

public class FileSudokuBoardDao
        implements Dao<SudokuBoard>,
        AutoCloseable {


    public void finalize() throws Exception {
        close();
    }

    @Override
    public void close() throws Exception {

    }

    public FileSudokuBoardDao(final String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws FileException {

        SudokuBoard sudokuBoard = null;

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        } catch (IOException e) {
            throw new FileException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) throws FileException {

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    private String filename;
}
