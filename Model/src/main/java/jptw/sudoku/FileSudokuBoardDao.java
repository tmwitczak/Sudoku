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
    public SudokuBoard read() {

        SudokuBoard sudokuBoard = null;

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String filename;
}
