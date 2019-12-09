package dao.interfaces.mappers;

import connector.DaoFactory;
import dao.interfaces.mappers.mapperInterface.Mapper;
import dto.AuthorDto;
import dto.BookDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRatingMapper implements Mapper {
  @Override
  public List<BookDto> rowMapper(ResultSet resultSet) {
    try {
      List<BookDto> bookList = new ArrayList<BookDto>();

      while (resultSet.next()) {
        String title = resultSet.getString(2);
        String bookDescription = resultSet.getString(3);
        String dateOfPublishment = resultSet.getString(4);
        String name = resultSet.getString(5);
        String secondname = resultSet.getString(6);
        String surname = resultSet.getString(7);
        int count = resultSet.getInt(8);
        boolean isAvailable = DaoFactory.bookDao().isBookAvailable(title);

        BookDto book = new BookDto(title, bookDescription, dateOfPublishment, new AuthorDto(name, secondname, surname), isAvailable);

        bookList.add(book);
      }


      return bookList;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}
