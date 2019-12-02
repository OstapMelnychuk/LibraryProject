package dao;

import dao.interfaces.BookDao;
import dao.interfaces.mappers.BookMapper;
import models.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
  private final Connection connection;

  public BookDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean isBookAvailable(String book) {
    String query = "SELECT *, count FROM book WHERE title = ? AND count != 0";

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
            "join copy_book on journal.book_id = copy_book.id " +
            "join copy on copy_book.book_id = copy.book_id " +
            "join book on book.id = copy.book_id " +
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
  public List<Book> findAllBooksByAuthor(String nameOfAuthor) {
    String query = "SELECT * FROM book " +
            "JOIN copy ON book.id = copy.book_id " +
            "JOIN author on library.copy.author_id = author.id " +
            "where author.author_name = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nameOfAuthor);
      ResultSet resultSet = preparedStatement.executeQuery();

      BookMapper bookMapper = new BookMapper();

      return bookMapper.rowMapper(resultSet);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public List<Book> findAllBooksBetweenDate(LocalDate fromDate, LocalDate toDate) {
    String query = "SELECT * FROM book WHERE date_of_publisment BETWEEN ? AND ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDate(1, Optional.ofNullable((Date.valueOf(fromDate))).orElse(null));
      preparedStatement.setDate(2, Optional.ofNullable((Date.valueOf(toDate))).orElse(null));

      ResultSet resultSet = preparedStatement.executeQuery();

      BookMapper bookMapper = new BookMapper();

      return bookMapper.rowMapper(resultSet);
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }

  public void getTenTheMostPopularBook(){
    String query = "SELECT count(title), book.title FROM book join copy_book on book.id = copy_book.book_id\n" +
            "join journal on journal.book_id = copy_book.book_id group by copy_book.book_id order by count(title) desc;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();


      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String title = resultSet.getString(2);

        System.out.println(id + " " + title);
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void getTenTheMostUnopularBook(){
    String query = "SELECT count(title), book.title FROM book join copy_book on book.id = copy_book.book_id\n" +
            "join journal on journal.book_id = copy_book.book_id group by copy_book.book_id order by count(title) asc ;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();


      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String title = resultSet.getString(2);

        System.out.println(id + " " + title);
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void save(Book book) {
    String query = "INSERT INTO book "
            + "(title, book_description, date_of_publisment, count)"
            + "VALUE (?,?,?,?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getDescription());
      preparedStatement.setDate(3, Date.valueOf(LocalDate.of(2015, 12, 31)));
      preparedStatement.setInt(4, book.getCount());
      preparedStatement.executeUpdate();



      try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
        if (keys.next()) {
          book.setId(keys.getInt(1));
        }
        connectBookWithAuthor(book);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  private void connectBookWithAuthor(Book book){
    String queryAddAuthor = "INSERT INTO copy (author_id, book_id) VALUES(?,?)";

    try (PreparedStatement statement = connection.prepareStatement(queryAddAuthor, Statement.RETURN_GENERATED_KEYS);){

      System.out.println(book.getAuthor().getId() + " " + book.getId());

      statement.setInt(1, book.getAuthor().getId());
      statement.setInt(2, book.getId());

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}