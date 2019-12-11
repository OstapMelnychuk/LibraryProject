package dao.interfaces;

import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDaoInterface {
  List<User> readUserById(Integer id);
  List<User> readUserByLogin(String login);
  void deleteUserById(Integer id);
  void deleteUserByLogin(String login);
  int createUser(User user);
  void updateUserById(Integer id, User user);
}
