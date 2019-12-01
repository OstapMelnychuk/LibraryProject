package connector;

import dao.AuthorDaoImpl;
import dao.BookDaoImpl;
import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import models.Author;
import models.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Connector {
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  private static Connection connection;

  public static void main(String[] args) {
    Connector connector = new Connector();
    Connector.connectToDB();
    System.out.println(connector.firstStatement());
    AuthorDao author = new AuthorDaoImpl(connection);
    BookDaoImpl book = new BookDaoImpl(connection);


   book.getTenTheMostPopularBook();
    List<Book> list = book.findAllBooksByAuthor("Anton");

    System.out.println(author.getAvarageAgeOfUserByAuthor("Anton"));
    System.out.println(list.get(0));

    System.out.println(book.isBookAvailable("It"));

    System.out.println(book.getAvarageAgeOfUserByBook("Sunset"));

    List<Book> list1 = book.findAllBooksBetweenDate(
            LocalDate.of(1950, 12, 31), LocalDate.of(2015, 12, 31));

//    book.save(new Book(4, "Karin", "car","ded",
//            34, new Author(2, "Vasia","Malion","Pupkin")));

    for (int i = 0; i < list1.size(); i++) {
      list1.get(i);
      System.out.println(list1.get(i));
    }
  }

  public static void connectToDB() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {
      System.out.println("JAVA: Class.forName() error");
      e.printStackTrace();
    }
    try {
      connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
    } catch (SQLException e) {
      System.out.println("Error in initializing a connection to MYSQL DB");
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return connection;
  }

  public String firstStatement() {
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

  public void closeConnection(){
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}