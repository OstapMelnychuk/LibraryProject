package dao.impl;

import connector.Connector;
import dao.UserDAOInterface;
import models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class UserDAO implements UserDAOInterface {
  private Connection connection;

  UserDAO(Connection connection) {
    this.connection = connection;
  }

  public static void main(String[] args) {
    Connector.connectToDB();
    User user = new User(3, "User", "user",
      "123", 2, "@", 12, "1271-11-11");
    UserDAO userDAO = new UserDAO(Connector.getConnection());
    try {
      System.out.println(userDAO.readUserById(userDAO.createUser(user)).get());
      userDAO.updateUserById(3 , userDAO.readUserByLogin("login").get());
      System.out.println(userDAO.readUserById(3));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<User> readUserById(Integer id) throws SQLException {
    User user = null;
    PreparedStatement preparedStatement = connection.prepareStatement("Select * From USERS Where id = " + id);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    user = new User(resultSet.getInt(1), resultSet.getString(2),
      resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
      resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8));
    return Optional.ofNullable(user);
  }

  @Override
  public Optional<User> readUserByLogin(String login) throws SQLException {
    User user = null;

    PreparedStatement preparedStatement = connection.prepareStatement("Select * From USERS Where login = " + login);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    user = new User(resultSet.getInt(1), resultSet.getString(2),
      resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
      resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8));
    return Optional.ofNullable(user);
  }

  @Override
  public void deleteUserById(Integer id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE id = " + id);
    preparedStatement.execute();
  }

  @Override
  public void deleteUserByLogin(String login) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE login = " + login);
    preparedStatement.execute();
  }

  @Override
  public int createUser(User user) throws SQLException {
    String query = "INSERT INTO USERS"
      + "(id, nick_name, login, user_password, role_id, email, age, start_date) "
      + "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

  @Override
  public void updateUserById(Integer id, User user) throws SQLException {
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

  }
}
