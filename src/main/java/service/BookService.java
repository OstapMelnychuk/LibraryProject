package service;

import connector.DaoFactory;
import dao.interfaces.BookDao;
import dto.BookDto;
import dto.StatisticsBookDto;
import models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private BookDao bookDao = DaoFactory.bookDao();

    /**
     * Default constructor
     */
    public BookService() {
    }

    /**
     * Method for finding all books
     *
     * @return all books in database
     */
    public List<BookDto> findAllBooks() {
        return bookDao.findAll();
    }

    /**
     * Method for finding all books by title
     *
     * @param title title of book
     * @return all books with title
     */
    public List<BookDto> findAllBookByTitle(String title) throws Exception {
        return bookDao.findAllBookByTitle(title);
    }

    /**
     * The method for finding whether there are copies of the book
     *
     * @param nameOfBook name of book
     * @return true if it exist and false if doesn't
     */
    public boolean isBookAvailable(String nameOfBook) {
        return bookDao.isBookAvailable(nameOfBook);
    }

    /**
     * Method of finding a book that came out between dates
     *
     * @param firstYear first year
     * @param lastYear  last year
     * @return all books that came out between firstYear and lastYear
     */
    public List<BookDto> findAllBooksBetweenDate(int firstYear, int lastYear) {
        if (lastYear >= firstYear) {
            return bookDao.findAllBooksBetweenDate(firstYear, lastYear);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Method for finding all books by author.
     *
     * @param nameOfAuthor name of author
     * @return all books are written by the author
     */
    public List<BookDto> findAllBooksByAuthor(String nameOfAuthor) {
        return bookDao.findAllBooksByAuthor(nameOfAuthor);
    }

    /**
     * A method for finding the 10 worst books.
     *
     * @return the 10 worst books
     */
    public List<BookDto> getTenTheMostUnpopularBook() {
        return bookDao.getTenTheMostUnPopularBook().stream().limit(10).collect(Collectors.toList());
    }

    /**
     * A method for finding the 10 best books.
     *
     * @return the 10 best book
     */
    public List<BookDto> getTenTheMostPopularBook() {
        return bookDao.getTenTheMostPopularBook().stream().limit(10).collect(Collectors.toList());
    }

    /**
     * Method for saving a book.
     *
     * @param book the book you want to keep
     */
    public void save(Book book) throws Exception {
        bookDao.save(book);
    }

    /**
     * Method for checking whether the books are maintained in the correct order.
     *
     * @param firstYear first year
     * @param lastYear  last year
     * @return true if in the correct order and false if it isn't
     */
    public boolean isEnteredInTheOrderOfYears(int firstYear, int lastYear) {
        return lastYear >= firstYear;
    }

    /**
     * Method for checking whether the years are not negative
     *
     * @param firstYear first year
     * @param lastYear  last year
     * @return true if one of them is negative and false if they are positive
     */
    public boolean isNegative(int firstYear, int lastYear) {
        if ((firstYear < 0) || (lastYear < 0)) {
            return true;
        } else {
            return false;
        }
    }

    public StatisticsBookDto getStatisticOfBook(BookDto book) {
        return DaoFactory.bookDao().getStatisticOfBook(book);
    }
}
