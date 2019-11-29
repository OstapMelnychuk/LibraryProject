package dao;

import models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAOInterface {
  Optional<User> readUserById(Integer id) throws SQLException;
  Optional<User> readUserByLogin(String login) throws SQLException;
  void deleteUserById(Integer id) throws SQLException;
  void deleteUserByLogin(String login) throws SQLException;
  int createUser(User user) throws SQLException;
  void updateUserById(Integer id, User user) throws SQLException;
}
