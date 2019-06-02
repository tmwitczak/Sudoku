package jptw.sudoku;

import java.io.*;
import java.util.logging.*;

public class FileSudokuBoardDao
        implements Dao<SudokuBoard>,
        AutoCloseable{

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = Logger.getLogger(FileSudokuBoardDao.class.getName());
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
        catch (IOException e) {
            logger.log(Level.WARNING, "FileOperationException: ", e);
            throw new RuntimeException();
        }catch (ClassNotFoundException e){
            logger.log(Level.SEVERE, "ClassNotFoundException: ", e);
            throw new RuntimeException();
        }

        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) {

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(obj);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "FileOperationException: ", e);
            throw new RuntimeException();
        }
    }

    private String filename;
}
