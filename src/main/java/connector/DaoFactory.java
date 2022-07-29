package connector;

import dao.AuthorDaoImpl;
import dao.BookDaoImpl;
import dao.UserDao;
import dao.interfaces.BookDao;
import dto.AuthorDto;
import models.Author;
import models.Book;
import service.BookService;

import java.sql.*;

public final class DaoFactory {

    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/"
            + "library?"
            + "useSSL=false&"
            + "serverTimezone=UTC&"
            + "allowPublicKeyRetrieval=true";

    /**
     * Private constructor used for Singleton implementation
     */
    private DaoFactory() {
    }


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error in initializing a connection to MYSQL DB");
            e.printStackTrace();
        }
    }


    public static String firstStatement() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM JOURNAL");
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt(1)).append(" ")
                        .append(resultSet.getString(2)).append(" ")
                        .append(resultSet.getString(3)).append(" ")
                        .append(resultSet.getString(4));
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for creating AddressDao instance
     *
     * @return AddressDao instance
     */
    public static BookDaoImpl bookDao() {
        return new BookDaoImpl(connection);
    }

    /**
     * Method used for creating AuthorDao instance
     *
     * @return AuthorDao instance
     */
    public static AuthorDaoImpl authorDao() {
        return new AuthorDaoImpl(connection);
    }

    public static UserDao userDao() {
        return new UserDao(connection);
    }

    /**
     * Method for closing connection.
     * Must be called in the end of the program
     */
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}