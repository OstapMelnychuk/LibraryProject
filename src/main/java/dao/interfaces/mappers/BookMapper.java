package dao.interfaces.mappers;

import dao.interfaces.mappers.mapperInterface.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
import models.Book;

public class BookMapper implements Mapper {
    public List<BookDto> rowMapper(ResultSet resultSet) {
      try {
        List<BookDto> bookList = new ArrayList<BookDto>();
        while (resultSet.next()) {
          String title = resultSet.getString(1);
          String bookDescription = resultSet.getString(2);
          String dateOfPublishment = resultSet.getString(3);

          BookDto book = new BookDto(title, bookDescription, dateOfPublishment);

          bookList.add(book);
        }


        return bookList;
      } catch (SQLException e) {
        e.printStackTrace();
      }

      return null;
    }
}
