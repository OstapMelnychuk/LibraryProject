package dao.interfaces;

import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDaoInterface {
  List<User> readUserById(Integer id) throws SQLException;
  List<User> readUserByLogin(String login) throws SQLException;
  void deleteUserById(Integer id) throws SQLException;
  void deleteUserByLogin(String login) throws SQLException;
  int createUser(User user) throws SQLException;
  void updateUserById(Integer id, User user) throws SQLException;
  List<User> getDebtors() throws SQLException;
  int getIdByName(String name) throws SQLException;
}
