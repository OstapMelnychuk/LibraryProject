package dao.interfaces;

import models.Book;
import java.util.List;

public interface AuthorDao {
  List<Book> findAllBooksByMainAuthor(String nameOfAuthor);
  double getAvarageOfUserByAuthor(String nameOfAuthor);

}
