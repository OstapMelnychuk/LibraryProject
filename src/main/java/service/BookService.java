package service;

import connector.DaoFactory;
import models.Book;

import java.time.LocalDate;
import java.util.List;

public class BookService {

  public boolean isBookAvailable(String nameOfBook) {
    return DaoFactory.bookDao().isBookAvailable(nameOfBook);

  }

  public double getAvarageAgeOfUserByBook(String nameOfAuthor) {
    return DaoFactory.bookDao().getAvarageAgeOfUserByBook(nameOfAuthor);
  }

  public List<Book> findAllBooksBetweenDate(LocalDate fromDate, LocalDate toDate) {
    return DaoFactory.bookDao().findAllBooksBetweenDate(fromDate, toDate);
  }

  public List<Book> findAllBooksByAuthor(String nameOfAuthor) {
    return DaoFactory.bookDao().findAllBooksByAuthor(nameOfAuthor);
  }
}
