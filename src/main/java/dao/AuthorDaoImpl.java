package dao;

import dao.interfaces.AuthorDao;


import models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
  private final Connection connection;

  public AuthorDaoImpl(Connection connection) {
    this.connection = connection;
  }


  @Override
  public List<Book> findAllBooksByMainAuthor(String nameOfAuthor) {
    String query = "SELECT * FROM book " +
            "JOIN library.copy ON library.book.id = copy.book_id " +
            "JOIN author on library.copy.author_id = author.id " +
            "where author.author_name = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nameOfAuthor);
      ResultSet resultSet = preparedStatement.executeQuery();

      List<Book> bookList = new ArrayList<>();
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String bookDescription = resultSet.getString(3);
        String dateOfPublishment = resultSet.getString(4);

        bookList.add(new Book(id, title, bookDescription, dateOfPublishment));
      }

      return bookList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public double getAvarageOfUserByAuthor(String nameOfAuthor) {
    String query = "SELECT AVG(age)\n" +
            "FROM users join journal on users.id = book_id \n" +
            "join copy on journal.book_id = copy.book_id join author on copy.author_id = author.id " +
            "where author.author_name = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nameOfAuthor);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      return resultSet.getDouble(1);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
