package jptw.sudoku;

public interface Dao<T>{
    T read() throws FileException;
    void write(T obj) throws FileException;
}
