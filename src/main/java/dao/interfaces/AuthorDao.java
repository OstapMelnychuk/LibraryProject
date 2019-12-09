package dao.interfaces;

import dto.AuthorDto;
import models.Author;
import models.Book;

import java.util.List;

public interface AuthorDao {
  List<AuthorDto> findAll();
  double getAvarageAgeOfUserByAuthor(String nameOfAuthor);
  public void save(Author author);
  void update(Author author);
  void delete(Author author);
  boolean isAuthorExist(Book book);
}
