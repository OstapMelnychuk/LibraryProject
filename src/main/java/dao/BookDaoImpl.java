package dao;

import dao.interfaces.BookDao;
import dao.interfaces.mappers.BookMapper;
import dto.BookDto;
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
  public List<BookDto> findAll() {
    String query = "SELECT title, book_description, date_of_publisment FROM book";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      ResultSet resultSet = preparedStatement.executeQuery();

      List<BookDto> books = new ArrayList<>();

      while (resultSet.next()) {
        String title = resultSet.getString(1);
        String bookDescription = resultSet.getString(2);
        String dateOfPublishment = resultSet.getString(3);

        BookDto book = new BookDto(title, bookDescription, dateOfPublishment);

        books.add(book);
      }

      return books;
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public List<BookDto> findAllBookByTitle(String title) {
    String query = "SELECT book_description, date_of_publisment FROM book Where title = ?;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, title);
      ResultSet resultSet = preparedStatement.executeQuery();

      List<BookDto> books = new ArrayList<>();

      while (resultSet.next()) {
        String bookDescription = resultSet.getString(1);
        String dateOfPublishment = resultSet.getString(2);

        BookDto book = new BookDto(title, bookDescription, dateOfPublishment);

        books.add(book);
      }

      return books;
    } catch (SQLException e) {
      return null;
    }
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
  public List<BookDto> findAllBooksByAuthor(String nameOfAuthor) {
    String query = "SELECT title, book_description, date_of_publisment FROM book " +
            "JOIN copy ON book.id = copy.book_id " +
            "JOIN author on library.copy.author_id = author.id " +
            "where author.author_name = ? GROUP BY book.title";
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
  public List<BookDto> findAllBooksBetweenDate(int firstYear, int secondYear) {
    String query = "SELECT title, book_description, date_of_publisment FROM book WHERE YEAR(date_of_publisment) BETWEEN ? AND ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, firstYear);
      preparedStatement.setInt(2, secondYear);
      ResultSet resultSet = preparedStatement.executeQuery();

      BookMapper bookMapper = new BookMapper();

      return bookMapper.rowMapper(resultSet);
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }

  @Override
  public List<BookDto> getTenTheMostPopularBook() {
    return getBookRating("desc");
  }

  @Override
  public List<BookDto> getTenTheMostUnPopularBook() {
    return getBookRating("asc");
  }

  private List<BookDto> getBookRating(String orderBy) {
    String query = "SELECT count(title), book.title, book.book_description, book.date_of_publisment FROM book " +
            "join copy_book on book.id = copy_book.book_id\n" +
            "join journal on journal.book_id = copy_book.book_id " +
            "group by copy_book.book_id order by count(title) " + orderBy + ";";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      List<BookDto> books = new ArrayList<BookDto>();

      while (resultSet.next()) {
        String title = resultSet.getString(2);
        String description = resultSet.getString(3);
        String date = resultSet.getString(4);

        books.add(new BookDto(title, description, date));
      }

      return books;

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


  private void connectBookWithAuthor(Book book) {
    String queryAddAuthor = "INSERT INTO copy (author_id, book_id) VALUES(?,?)";

    try (PreparedStatement statement = connection.prepareStatement(queryAddAuthor, Statement.RETURN_GENERATED_KEYS);) {

      System.out.println(book.getAuthor().getId() + " " + book.getId());

      statement.setInt(1, book.getAuthor().getId());
      statement.setInt(2, book.getId());

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}