package service;

import connector.DaoFactory;
import dto.BookDto;
import models.Book;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
  public List<User> readUserById(Integer id) {
    return DaoFactory.userDao().readUserById(id);
  }

  public List<User> readUserByLogin(String login) {
    return DaoFactory.userDao().readUserByLogin(login);
  }

  public void deleteUserById(Integer id) {
    DaoFactory.userDao().deleteUserById(id);
  }

  public void deleteUserByLogin(String login) {
    DaoFactory.userDao().deleteUserByLogin(login);
  }

  public int createUser(User user) {
    return DaoFactory.userDao().createUser(user);
  }

  public void updateUserById(Integer id, User user) {
    DaoFactory.userDao().updateUserById(id, user);
  }

  public long getUserTimeFromStart(String name) {
    return DaoFactory.userDao().getUserTimeFromStart(name);
  }

  public List<BookDto> getUserBooksTaken(Integer id) {
    return DaoFactory.userDao().getUserBooksTaken(id);
  }

  public List<BookDto> getUserBooksNotReturned(Integer id) {
    return DaoFactory.userDao().getUserBooksNotReturned(id);
  }

  public double getAverageAgeOfUsersByBook(String name) {
    return DaoFactory.userDao().getAverageAgeOfUsersByBook(name);
  }

  public double getAverageAgeOfUsersByAuthor(String authorName, String authorSecondName,
                                             String authorSurname) {
    return DaoFactory.userDao().getAverageAgeOfUsersByAuthor(authorName,
      authorSecondName, authorSurname);
  }

  public List<User> readUserByName(String name) {
    return DaoFactory.userDao().readUserByName(name);
  }

  public void logIn(String login, String password) {
    DaoFactory.userDao().logIn(login, password);
  }

  public double getAverageUserAge(){
    return DaoFactory.userDao().getAverageUserAge();
  }

  public int getUserHits(String userName){
    return DaoFactory.userDao().getUserHits(userName);
  }
}
