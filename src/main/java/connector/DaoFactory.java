package connector;

import dao.AuthorDaoImpl;
import dao.BookDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DaoFactory {

  private static Connection connection;
  private static final String USER = "root";
  private static final String PASSWORD = "root";
  private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";

  public static void main(String[] args) {
    System.out.println(bookDao().isBookAvailable("It"));
  }

  /**
   * Private constructor used for Singleton implementation
   */
  private DaoFactory() {
  }

  static {
    try {
      Class.forName(DB_DRIVER);
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
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