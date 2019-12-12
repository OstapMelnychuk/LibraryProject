package connector;

import dao.AuthorDaoImpl;
import dao.BookDaoImpl;
import dao.UserDao;
import dto.AuthorDto;
import models.Author;
import models.Book;

import java.io.*;
import java.sql.*;

public final class DaoFactory implements AutoClosable{

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
//    String user = "";
//    String password = "";
//    String driver = "";
//    String URL = "";
//    try {
//      File file = new File("properties.properties");
//      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//      user = bufferedReader.readLine();
//      password = bufferedReader.readLine();
//      driver = bufferedReader.readLine();
//      URL = bufferedReader.readLine();
//      bufferedReader.close();
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
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

  private static Connection connect(){
    String user;
    String password;
    String driver;
    String URL;
    try {
      File file = new File("properties.properties");
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      user = bufferedReader.readLine();
      password = bufferedReader.readLine();
      driver = bufferedReader.readLine();
      URL = bufferedReader.readLine();
      bufferedReader.close();
      try {
        Class.forName(driver).newInstance();
      } catch (Exception e) {
        System.out.println("JAVA: Class.forName() error");
        e.printStackTrace();
      }
      try {
        Connection connection = DriverManager.getConnection(URL, user, password);
        return connection;
      } catch (SQLException e) {
        System.out.println("Error in initializing a connection to MYSQL DB");
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String firstStatement(){
    StringBuilder stringBuilder = new StringBuilder();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM JOURNAL");
      while (resultSet.next()){
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
  public void close() {
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