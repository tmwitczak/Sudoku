///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import org.apache.commons.lang3.SerializationUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;
import java.util.Arrays;


/////////////////////////////////////////////////// Class: JdbcSudokuBoardDao //
public
class JdbcSudokuBoardDao
        implements AutoCloseable,
                   Dao<SudokuBoard> {

    JdbcSudokuBoardDao
            (final String id) {

        this.id = id;
    }

    @Override
    public
    SudokuBoard read() {

        try {
            Connection connection = connectToDerbyDatabase();

            PreparedStatement pstmt = connection
                    .prepareStatement("select object from " +
                            "sudoku_boards where id = ?");
            pstmt.setString(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();


            Blob blob = resultSet.getBlob(1);
            InputStream byte_stream = blob.getBinaryStream();
            byte [] byte_array = new byte [4096];
            int bytes_read = byte_stream.read(byte_array);
            byte[] bytes_better = Arrays.copyOfRange(byte_array, 0, bytes_read);
            Object deSerializedObject =
                    SerializationUtils.deserialize(bytes_better);


            resultSet.close();
            pstmt.close();

            connection.close();

            return (SudokuBoard) deSerializedObject;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private
    Connection connectToDerbyDatabase()
            throws SQLException {

        return DriverManager.getConnection(
                "jdbc:derby://localhost:1527/" + "sudokuDatabase"
                        + ";create=true");
    }

    private
    boolean checkIfDatabaseHasAlreadyBeenCreated(
            final Connection connection)
            throws SQLException {

        DatabaseMetaData databaseMetaData
            = connection.getMetaData();

        ResultSet tables
            = databaseMetaData.getTables(
                connection.getCatalog(),
                null,
                null,
                null);

        return tables.next();
    }

    private
    void createTableForSudokuBoards(final Connection connection)
            throws SQLException {

        connection.createStatement().execute(
                "create table sudoku_boards" +
                "(" +
                "    id varchar(30) not null" +
                "        constraint primary_key_sudoku_boards_id" +
                "            primary key," +
                "" +
                "    name varchar(30)," +
                "" +
                "    object blob not null" +
                "" +
                ")"
        );
    }

    private
    void writeObjectToDatabase(final Connection connection,
                                       final Object obj)
            throws SQLException {

        PreparedStatement preparedStatement
                = connection.prepareStatement(
                        "insert into" +
                        "    sudoku_boards (id, name, object)" +
                        "    values (?, ?, ?)");
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, obj.getClass().getName());
        Blob blob
                = new SerialBlob(SerializationUtils.serialize(
                        (SudokuBoard) obj).clone());
        preparedStatement.setBlob(3, blob);
        preparedStatement.executeUpdate();
    }

    @Override
    public
    void write
            (final SudokuBoard sudokuBoard) {

        try {
            Connection connection
                    = connectToDerbyDatabase();

            if (!checkIfDatabaseHasAlreadyBeenCreated(connection)) {
                createTableForSudokuBoards(connection);
            }

            writeObjectToDatabase(connection, sudokuBoard);

            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public
    void close() {

    }


    //================================================================ Data ==//
    private
    final String id;


}


////////////////////////////////////////////////////////////////////////////////

