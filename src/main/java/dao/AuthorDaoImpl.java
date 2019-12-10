package dao;

import dao.interfaces.AuthorDao;
import dto.AuthorDto;
import models.Author;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
  private final Connection connection;

  public AuthorDaoImpl(Connection connection) {
    this.connection = connection;
  }

  /**
   * Method for finding all authors
   * @return all authors in database
   */
  @Override
  public List<AuthorDto> findAll() {
    String query = "Select * from author";

    try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
      ResultSet resultSet = preparedStatement.executeQuery();

      List<AuthorDto> authors = new ArrayList<>();

      while (resultSet.next()){
        String name = resultSet.getString(2);
        String secondname = resultSet.getString(3);
        String surname = resultSet.getString(4);

        resultSet.getString(3);

        authors.add(new AuthorDto(name, secondname, surname));
      }

      return authors;
    } catch (SQLException e) {
      return null;
    }
  }

  /**
   * The method of finding the middle age of the reader, according to some author
   * @param nameOfAuthor name of author
   * @return avarage age of user
   */
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

  /**
   * Method for saving a author.
   * @param author the author you want to save
   */
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

  /**
   * Method for updating Author object.
   * @param author author you want to update
   */
  @Override
  public void update(Author author) {
    System.out.println("This method is not applicable");
  }

  /**
   * Method for deleting Author object.
   * @param author author you want to delete
   */
  @Override
  public void delete(Author author) {
    System.out.println("This method is not applicable");
  }

  /**
   * Method to check if a book exists
   * @param book book
   * @return true if book exists and false if dean't
   */
  @Override
  public boolean isAuthorExist(Book book) {
    String query = "Select * from author where author_name = ? AND author_secondname = ? AND author_surname = ?";

    try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
      preparedStatement.setString(1, book.getAuthor().getFirstname());
      preparedStatement.setString(2, book.getAuthor().getSecondname());
      preparedStatement.setString(3, book.getAuthor().getSurname());

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        book.getAuthor().setId(resultSet.getInt(1));

        return true;
      }

    }catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
