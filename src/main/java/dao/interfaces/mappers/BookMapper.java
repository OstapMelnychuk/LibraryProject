package dao.interfaces.mappers;

import dao.interfaces.mappers.mapperInterface.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Book;

public class BookMapper implements Mapper {
  @Override
  public List<Book> rowMapper(ResultSet resultSet) {
    try {
      List<Book> bookList = new ArrayList<>();
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String bookDescription = resultSet.getString(3);
        String dateOfPublishment = resultSet.getString(4);

        Book book = new Book(id, title, bookDescription, dateOfPublishment);

        bookList.add(book);
      }


      return bookList;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}
