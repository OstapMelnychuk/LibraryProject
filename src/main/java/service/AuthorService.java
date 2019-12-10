package service;

import connector.DaoFactory;

public class AuthorService {

  /**
   * Default constructor
   */
  public AuthorService() {
  }

  /**
   * The method of finding the middle age of the reader, according to some author
   * @param nameOfAuthor name of author
   * @return avarage age of user
   */
  public double getAvarageAgeOfUserByAuthor(String nameOfAuthor) {
    return DaoFactory.authorDao().getAvarageAgeOfUserByAuthor(nameOfAuthor);
  }
}
