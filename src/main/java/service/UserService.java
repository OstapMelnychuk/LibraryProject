package service;

import connector.DaoFactory;
import models.Book;
import models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
  public List<User> readUserById(Integer id){
    try {
      return DaoFactory.userDao().readUserById(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<User> readUserByLogin(String login){
    try {
      return DaoFactory.userDao().readUserByLogin(login);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void deleteUserById(Integer id){
    try {
      DaoFactory.userDao().deleteUserById(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteUserByLogin(String login){
    try {
      DaoFactory.userDao().deleteUserByLogin(login);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int createUser(User user){
    try {
      return DaoFactory.userDao().createUser(user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public void updateUserById(Integer id, User user){
    try {
      DaoFactory.userDao().updateUserById(id, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public long getUserTimeFromStart(Integer id){
    try {
      return DaoFactory.userDao().getUserTimeFromStart(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public List<Book> getUserBooksTaken(Integer id){
    try {
      return DaoFactory.userDao().getUserBooksTaken(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Book> getUserBooksNotReturned(Integer id){
    try {
      return DaoFactory.userDao().getUserBooksNotReturned(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public double getAverageAgeOfUsersByBook(String name){
    try {
      return DaoFactory.userDao().getAverageAgeOfUsersByBook(name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1d;
  }

  public double getAverageAgeOfUsersByAuthor(String authorName, String authorSecondName,
                                             String authorSurname){
    try {
      return DaoFactory.userDao().getAverageAgeOfUsersByAuthor(authorName,
        authorSecondName, authorSurname);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public Integer logIn(String login, String password){
    try {
      return DaoFactory.userDao().logIn(login, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
