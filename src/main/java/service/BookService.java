package service;

import connector.DaoFactory;
import dao.interfaces.BookDao;
import dto.BookDto;
import models.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

  public List<BookDto> findAllBook() {
    return DaoFactory.bookDao().findAll();
  }

  public List<BookDto> findAllBookByTitle(String title) {
    return DaoFactory.bookDao().findAllBookByTitle(title);
  }

  public boolean isBookAvailable(String nameOfBook) {
    return DaoFactory.bookDao().isBookAvailable(nameOfBook);
  }

  public double getAvarageAgeOfUserByBook(String nameOfAuthor) {
    return DaoFactory.bookDao().getAvarageAgeOfUserByBook(nameOfAuthor);
  }

  public List<BookDto> findAllBooksBetweenDate(int firstYear, int lastYear) {
    if (lastYear >= firstYear) {
      return DaoFactory.bookDao().findAllBooksBetweenDate(firstYear, lastYear);
    } else {
      return new ArrayList<>();
    }
  }

  public List<BookDto> findAllBooksByAuthor(String nameOfAuthor) {
    return DaoFactory.bookDao().findAllBooksByAuthor(nameOfAuthor);
  }

  public List<BookDto> getTenTheMostUnpopularBook() {
    return DaoFactory.bookDao().getTenTheMostUnPopularBook().stream().limit(10).collect(Collectors.toList());
  }

  public List<BookDto> getTenTheMostPopularBook() {
    return DaoFactory.bookDao().getTenTheMostPopularBook().stream().limit(10).collect(Collectors.toList());
  }

  public void save(Book book) {
    DaoFactory.bookDao().save(book);
  }

  public boolean isEnteredInTheOrderOfYears(int firstYear, int lastYear) {
    return lastYear >= firstYear;
  }

  public boolean isNegative(int firstYear, int lastYear) {
    if ((firstYear < 0) || (lastYear < 0)) {
      return true;
    } else {
      return false;
    }

  }
}
