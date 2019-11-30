package dao.interfaces;

import models.Author;

public interface AuthorDao {
  double getAvarageAgeOfUserByAuthor(String nameOfAuthor);
  public void save(Author author);
}
