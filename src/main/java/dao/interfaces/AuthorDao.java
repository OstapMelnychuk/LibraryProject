package dao.interfaces;

import models.Author;
import models.Book;

import java.sql.Connection;

public interface AuthorDao {
  double getAvarageAgeOfUserByAuthor(String nameOfAuthor);
  public void save(Author author);
  void update(Author author);
  void delete(Author author);
}
