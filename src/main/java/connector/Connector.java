package connector;

import java.sql.*;
public class Connector{
  private static final String URL= "jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  private static Connection connection;

  public static void main(String[] args) {
    Connector connector = new Connector();
    Connector.connectToDB();
    System.out.println(connector.firstStatement());
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

  public static Connection getConn() {
    return connection;
  }

  public String firstStatement(){
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
}