package dao;

import dao.interfaces.AuthorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDaoImpl implements AuthorDao {
  private final Connection connection;

  public AuthorDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public double getAvarageAgeOfUserByAuthor(String nameOfAuthor) {
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
