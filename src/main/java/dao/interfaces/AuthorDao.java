package dao.interfaces;

import models.Author;

import java.sql.Connection;

public interface AuthorDao {
  double getAvarageAgeOfUserByAuthor(String nameOfAuthor);
  public void save(Author author);
}
