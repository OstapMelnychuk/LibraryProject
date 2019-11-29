package service;

import dao.BookDaoImpl;
import models.Book;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class BookService {
  private final Connection connection;

  public BookService(Connection connection) {
    this.connection = connection;
  }

  public boolean isBookAvailable(String nameOfBook){
    return new BookDaoImpl(connection).isBookAvailable(nameOfBook);
  }

  double getAvarageAgeOfUserByBook(String nameOfAuthor){
    return new BookDaoImpl(connection).getAvarageAgeOfUserByBook(nameOfAuthor);
  }

  List<Book> findAllBooksBetweenDate(LocalDate fromDate, LocalDate toDate){
    return new BookDaoImpl(connection).findAllBooksBetweenDate(fromDate, toDate);
  }

  List<Book> findAllBooksByAuthor(String nameOfAuthor){
    return new BookDaoImpl(connection).findAllBooksByAuthor(nameOfAuthor);
  }
}
