package dao;

import dao.interfaces.UserDaoInterface;
import dao.interfaces.mappers.BookMapper;
import dao.interfaces.mappers.UserMapper;
import dto.BookDto;
import models.Book;
import models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserDao implements UserDaoInterface {
  private Connection connection;

  public UserDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<User> readUserById(Integer id) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("Select * From USERS Where id = ?");
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new UserMapper().rowMapper(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public List<User> readUserByLogin(String login) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("Select * From USERS Where login = ?");
      preparedStatement.setString(1, login);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new UserMapper().rowMapper(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public List<User> readUserByName(String name) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("Select * From USERS Where nick_name = ?");
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new UserMapper().rowMapper(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void deleteUserById(Integer id) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE id = ?");
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteUserByLogin(String login) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE login = ?");
      preparedStatement.setString(2, login);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int createUser(User user) {
    try {
      String query = "SELECT login FROM Users " +
        "WHERE login = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, user.getLogin());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (!resultSet.first()) {
        query = "INSERT INTO USERS"
          + "(id, nick_name, login, user_password, role_id, email, age, start_date) "
          + "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getNickName());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getRoleId());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, user.getAge());
        preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
        preparedStatement.executeUpdate();
        return user.getId();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public void updateUserById(Integer id, User user) {
    try {
      String query = "UPDATE USERS\n" +
        "SET " +
        "nick_name = ? , " +
        "login = ? , " +
        "user_password = ? , " +
        "role_id = ? , " +
        "email = ? , " +
        "age = ? , " +
        "start_date = ?" +
        "Where id = " + id;
      PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, user.getNickName());
      preparedStatement.setString(2, user.getLogin());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.setInt(4, user.getRoleId());
      preparedStatement.setString(5, user.getEmail());
      preparedStatement.setInt(6, user.getAge());
      preparedStatement.setDate(7, Date.valueOf(user.getStartDay()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public long getUserTimeFromStart(Integer id) {
    try {
      long a = 0;
      long diff = 0;
      TimeUnit timeUnit = null;
      String query = "SELECT start_date \n" +
        "FROM USERS Where id = ?;";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Date date = resultSet.getDate(1);
        a = Date.valueOf(LocalDate.now()).getTime();
        diff = a - date.getTime();
        timeUnit = TimeUnit.DAYS;
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public List<BookDto> getUserBooksTaken(Integer id) {
    try {
      String query = "Select * From BOOK " +
        "join copy_book on copy_book.book_id = book.id " +
        "join journal on copy_book.book_id = journal.book_id " +
        " Where journal.user_id = ? AND journal.date_of_input  IS NOT NULL";
      List<Book> books = new ArrayList<>();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new BookMapper().rowMapper(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public List<BookDto> getUserBooksNotReturned(Integer id) {
    try {
      ArrayList<Book> books = new ArrayList<>();
      String query = "Select * From BOOK " +
        "join copy_book on copy_book.book_id = book.id " +
        "join journal on copy_book.book_id = journal.book_id " +
        "Where journal.user_id = ? AND journal.date_of_input  IS NULL";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      return new BookMapper().rowMapper(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public double getAverageAgeOfUsersByBook(String name) {
    try {
      int id;
      double averageAge = 0;
      int counter = 0;
      String query = "Select id From Book Where title = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      id = resultSet.getInt(1);
      preparedStatement.close();
      resultSet.close();

      query = "Select users.age from Users " +
        "join journal on journal.user_id = users.id " +
        "where journal.book_id = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        averageAge += resultSet.getInt(1);
        counter++;
      }
      return averageAge / counter;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public double getAverageAgeOfUsersByAuthor(String authorName, String authorSecondName,
                                             String authorSurname) {
    try {
      int id;
      double averageAge = 0;
      int counter = 0;
      String query = "Select id From Author Where author_name = ? AND " +
        "author_secondname = ? AND author_surname = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, authorName);
      preparedStatement.setString(2, authorSecondName);
      preparedStatement.setString(3, authorSurname);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      id = resultSet.getInt(1);
      preparedStatement.close();
      resultSet.close();

      query = "Select users.age from Users " +
        "join journal on journal.user_id = users.id " +
        "join copy on journal.book_id = copy.book_id " +
        "where copy.author_id = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        averageAge += resultSet.getInt(1);
        counter++;
      }
      return averageAge / counter;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;

  }

  public User logIn(String login, String password) {
    String query = "Select * From Users " +
      "Where login = ? AND user_password = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();

      User user = new UserMapper().rowMapper(resultSet).get(0);

      return user;
    } catch (SQLException e) {
      return null;
    }
  }
}
