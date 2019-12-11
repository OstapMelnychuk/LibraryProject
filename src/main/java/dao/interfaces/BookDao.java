package dao.interfaces;

import dto.BookDto;
import models.Book;

import java.util.List;

public interface BookDao {
  List<BookDto> findAll();
  List<BookDto> findAllBookByTitle(String title);
  boolean isBookAvailable(String book);
  List<BookDto> findAllBooksBetweenDate(int firtYear, int secondYear);
  List<BookDto> findAllBooksByAuthor(String nameOfAuthor);
  List<BookDto> getTenTheMostPopularBook();
  List<BookDto> getTenTheMostUnPopularBook();
  void save(Book book);
  void update(Book book, int count);
  void delete(Book book);
  List<BookDto> getCopiesByTitle(String title);
}
