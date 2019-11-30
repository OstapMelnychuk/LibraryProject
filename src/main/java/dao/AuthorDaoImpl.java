package dao;

import dao.interfaces.AuthorDao;
import models.Author;

import java.sql.*;
import java.time.LocalDate;

public class AuthorDaoImpl implements AuthorDao {
  private final Connection connection;

  public AuthorDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public double getAvarageAgeOfUserByAuthor(String nameOfAuthor) {
    String query = "SELECT AVG(age)\n" +
            "FROM users join journal on users.id = book_id \n" +
            "join copy_book on journal.book_id = copy_book.id " +
            "join copy on copy_book.book_id = copy.book_id " +
            "join author on copy.author_id = author.id " +
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

  @Override
  public void save(Author author) {
    String query = "INSERT INTO author "
            + "(author_name, author_secondname, author_surname)"
            + "VALUE (?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, author.getFirstname());
      preparedStatement.setString(2, author.getSecondname());
      preparedStatement.setString(3, author.getSurname());
      preparedStatement.executeUpdate();
      try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
        if (keys.next()) {
          author.setId(keys.getInt(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
