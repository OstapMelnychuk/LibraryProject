package service;

import connector.DaoFactory;

public class AuthorService {

  public double getAvarageAgeOfUserByAuthor(String nameOfAuthor) {
    return DaoFactory.authorDao().getAvarageAgeOfUserByAuthor(nameOfAuthor);
  }
}
