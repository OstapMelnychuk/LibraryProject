package dao.interfaces.mappers;

import dao.interfaces.mappers.mapperInterface.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Book;

public class BookMapper implements Mapper {
  @Override
  public Book rowMapper(ResultSet resultSet) {
    Book book;

    try {
      Integer id = resultSet.getInt(1);
      String title = resultSet.getString(2);
      String bookDescription = resultSet.getString(3);
      String dateOfPublishment = resultSet.getString(4);

      book = new Book(id, title, bookDescription, dateOfPublishment);

      return book;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}
