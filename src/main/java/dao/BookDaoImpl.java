package dao;

import dao.interfaces.BookDao;
import dao.interfaces.mappers.BookMapper;
import dao.interfaces.mappers.BookRatingMapper;
import dto.BookDto;
import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final Connection connection;

    public BookDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for finding all books
     * @return all books in database
     */
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

    /**
     * Method for finding all books by title
     * @param title title of book
     * @return all books with title
     */
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

    /**
     * The method for finding whether there are copies of the book
     * @return true if it exist and false if doesn't
     */
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

    /**
     * Method for finding all books by author.
     * @param nameOfAuthor name of author
     * @return all books are written by the author
     */
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

    /**
     * Method of finding a book that came out between dates
     * @param firstYear first year
     * @param secondYear last year
     * @return all books that came out between firstYear and lastYear
     */
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

    /**
     * A method for finding the 10 best books.
     * @return the 10 best book
     */
    @Override
    public List<BookDto> getTenTheMostPopularBook() {
        return getBookRating("asc");
    }

    /**
     * A method for finding the 10 worst books.
     * @return the 10 worst books
     */
    @Override
    public List<BookDto> getTenTheMostUnPopularBook() {
        return getBookRating("desc");
    }

    /**
     * A method for finding the 10 worst or best books.
     * @return the 10 worst or best books
     */
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

    /**
     * Method for saving a book.
     * @param book the book you want to keep
     */
    @Override
    public void save(Book book) {
        String query = "INSERT INTO book "
                + "(title, book_description, date_of_publisment, count)"
                + "VALUE (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(isBookExist(book));
            if (isBookExist(book)) {
                update(book, findCountOfBook(book));
            } else {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setString(3, book.getDateOfPublishment());
                preparedStatement.setInt(4, book.getCount());
                preparedStatement.executeUpdate();

                AuthorDaoImpl authorDao = new AuthorDaoImpl(connection);

                if (authorDao.isAuthorExist(book)) {
                    connectBookWithAuthor(book);
                } else {
                    authorDao.save(book.getAuthor());
                    connectBookWithAuthor(book);
                }
            }

            addAllExemplars(book.getCount(), book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for updating Book object.
     * @param book book you want to update
     */
    public void update(Book book) {
        System.out.println("This method is not applicable");
    }

    /**
     * Method for updating Book object.
     * @param book book you want to update
     * @param count count new copy of book
     */
    @Override
    public void update(Book book, int count) {
        String query = "Update book set count = ? where title = ? AND book_description = ? AND date_of_publisment = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, book.getCount() + count);
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getDateOfPublishment());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting Book object.
     * @param book book you want to delete
     */
    @Override
    public void delete(Book book) {
        System.out.println("This method is not applicable");
    }

    /**
     * Method to check if a book exists
     * @param book book
     * @return true if book exists and false if dean't
     */
    public boolean isBookExist(Book book) {
        String query = "Select * from book join copy on copy.book_id = book.id " +
                "join author on copy.author_id = author.id " +
                "where title = ? AND book_description = ? AND date_of_publisment = ? AND author_name = ? AND author_secondname = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getDateOfPublishment());
            preparedStatement.setString(4, book.getAuthor().getFirstname());
            preparedStatement.setString(5, book.getAuthor().getSecondname());

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to connect a tablet book to a tablet author.
     * @param book book
     */
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

    /**
     * Method for finding the number of instances.
     * @param book book
     * @return count of instances
     */
    private int findCountOfBook(Book book) {
        String query = "Select count from book where title = ? AND book_description = ? AND date_of_publisment = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getDateOfPublishment());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            int count = resultSet.getInt(1);

            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Method to create all instances.
     * @param quantity count of instances
     * @param book the book you want to copy
     */
    private void addAllExemplars(int quantity, Book book) {
        String query = "INSERT INTO copy_book (is_availible, book_id) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < quantity; i++) {
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, getIdBook(book));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to finding a id by book
     * @param book book
     * @return id of book
     */
    private int getIdBook(Book book) {
        String query = "SELECT id from book where title = ? and date_of_publisment = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDateOfPublishment());

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}