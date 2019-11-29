package dao;

import dao.interfaces.BookDao;
import dao.interfaces.mappers.BookMapper;
import models.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
  private final Connection connection;

  public BookDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean isBookAvailable(String book) {
    String query = "SELECT * FROM book WHERE title = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, book);
      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();
      return resultSet.getBoolean(1);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public double getAvarageAgeOfUserByBook(String nameOfAuthor) {
    String query = "SELECT AVG(age)\n" +
            "FROM users join journal on users.id = journal.user_id  \n" +
            "join copy on journal.book_id = copy.book_id join book on book.id = copy.book_id " +
            "where book.title = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nameOfAuthor);

      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();

      return resultSet.getDouble(1);
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }

  @Override
  public List<Book> findAllBooksBetweenDate(LocalDate fromDate, LocalDate toDate) {
    String query = "SELECT * FROM book WHERE date_of_publisment BETWEEN ? AND ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setDate(1, Optional.ofNullable((Date.valueOf(fromDate))).orElse(null));
      preparedStatement.setDate(2, Optional.ofNullable((Date.valueOf(toDate))).orElse(null));

      ResultSet resultSet = preparedStatement.executeQuery();

      List<Book> bookList = new ArrayList<>();

      while (resultSet.next()) {
        BookMapper bookMapper = new BookMapper();

        bookList.add(bookMapper.rowMapper(resultSet));
      }

      return bookList;
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }

  @Override
  public List<Book> findAllBooksByAuthor(String nameOfAuthor) {
    String query = "SELECT * FROM book " +
            "JOIN library.copy ON library.book.id = copy.book_id " +
            "JOIN author on library.copy.author_id = author.id " +
            "where author.author_name = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nameOfAuthor);
      ResultSet resultSet = preparedStatement.executeQuery();

      List<Book> bookList = new ArrayList<>();
      while (resultSet.next()) {
        while (resultSet.next()) {
          BookMapper bookMapper = new BookMapper();

          bookList.add(bookMapper.rowMapper(resultSet));
        }
      }

      return bookList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
