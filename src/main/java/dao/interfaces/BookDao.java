package dao.interfaces;

import models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {
  boolean isBookAvailable(String book);
  double getAvarageAgeOfUserByBook(String nameOfAuthor);
  List<Book> findAllBooksBetweenDate(LocalDate fromDate, LocalDate toDate);
  List<Book> findAllBooksByAuthor(String nameOfAuthor);
  public void save(Book book);
}
