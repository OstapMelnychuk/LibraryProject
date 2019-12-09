package dao;

import dao.interfaces.BookDao;
import dao.interfaces.mappers.BookMapper;
import dao.interfaces.mappers.BookRatingMapper;
import dto.BookDto;
import models.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final Connection connection;

    public BookDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BookDto> findAll() {
        String query = "SELECT title, book_description, date_of_publisment, author_name, author_secondname, author_surname, count  FROM book " +
                "join copy on book.id = copy.book_id " +
                "join author a on copy.author_id = a.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            BookMapper bookMapper = new BookMapper();

            return bookMapper.rowMapper(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<BookDto> findAllBookByTitle(String title) {
        String query = "SELECT title, book_description, date_of_publisment, author.author_name, " +
                "author.author_secondname, author.author_surname, count" +
                " FROM book " +
                "join copy on copy.book_id = book.id" +
                " join author on copy.author_id = author.id " +
                "Where title  like ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            BookMapper bookMapper = new BookMapper();

            return bookMapper.rowMapper(resultSet);
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
    public List<BookDto> findAllBooksByAuthor(String nameOfAuthor) {
        String query = "SELECT title, book_description, date_of_publisment," +
                "author.author_name, author.author_secondname, author.author_surname, book.count FROM book " +
                "JOIN copy ON book.id = copy.book_id " +
                "JOIN author ON library.copy.author_id = author.id " +
                "where author.author_name like ? GROUP BY book.title";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + nameOfAuthor + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            BookMapper bookMapper = new BookMapper();

            return bookMapper.rowMapper(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<BookDto> findAllBooksBetweenDate(int firstYear, int secondYear) {
        String query = "SELECT title, book_description, date_of_publisment, author_name, author_secondname, author_surname, count FROM book " +
                "join copy on copy.book_id = book.id " +
                "join author on copy.author_id = author.id " +
                "WHERE YEAR(date_of_publisment) BETWEEN ? AND ?";
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
        return getBookRating("asc");
    }

    @Override
    public List<BookDto> getTenTheMostUnPopularBook() {
        return getBookRating("desc");
    }

    private List<BookDto> getBookRating(String orderBy) {
        String query = "SELECT count(journal.book_id), book.title, book.book_description, book.date_of_publisment," +
                "author.author_name, author.author_secondname, author.author_surname, count FROM book" +
                " left join copy_book on book.id = copy_book.id " +
                "left join journal on journal.book_id = copy_book.id " +
                "join copy on copy.book_id = book.id " +
                "join author on copy.author_id = author.id " +
                "group by book.id order by count(journal.book_id)" + orderBy + ", title " + orderBy;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            BookRatingMapper bookRatingMapper = new BookRatingMapper();

            return bookRatingMapper.rowMapper(resultSet);
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
            preparedStatement.setDate(3, Date.valueOf(LocalDate.of(2019, 11, 19)));
            preparedStatement.setInt(4, book.getCount());
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                AuthorDaoImpl authorDao = new AuthorDaoImpl(connection);

                if (keys.next()) {
                    book.setId(keys.getInt(1));
                }

                if (authorDao.isAuthorExist(book)) {
                    connectBookWithAuthor(book);
                } else {
                    authorDao.save(book.getAuthor());
                    connectBookWithAuthor(book);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) {
        System.out.println("This method is not applicable");
    }

    @Override
    public void delete(Book book) {
        System.out.println("This method is not applicable");
    }

    private boolean isBookExist(BookDto bookDto){
        String query = "Select * from book where title = ? AND book_description = ? AND date_of_publisment = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, bookDto.getTitle());
            preparedStatement.setString(2, bookDto.getDescription());
            preparedStatement.setString(3, bookDto.getDateOfPublishment());

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void AddBookCopy(){
    }


    private void connectBookWithAuthor(Book book) {
        String queryAddAuthor = "INSERT INTO copy (author_id, book_id) VALUES(?,?)";

        try (PreparedStatement statement = connection.prepareStatement(queryAddAuthor)) {

            System.out.println(book.getAuthor().getId() + " " + book.getId());

            statement.setInt(1, book.getAuthor().getId());
            statement.setInt(2, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}