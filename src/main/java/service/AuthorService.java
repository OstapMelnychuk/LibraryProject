package service;

import connector.DaoFactory;
import dao.interfaces.AuthorDao;
import models.Author;
import models.Book;

public class AuthorService {
    AuthorDao authorDao = DaoFactory.authorDao();

    /**
     * Default constructor
     */
    public AuthorService() {
    }


    /**
     * Method for creating an address in database
     *
     * @param author address that must be created
     */
    public void create(Author author) {
        authorDao.save(author);
    }

    /**
     * Method for updating author in database
     *
     * @param author author for updating
     * @return true if author was updated
     * or false if he doesn't exist in database
     */
    public boolean update(Author author) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method for updating address in database
     *
     * @param author author for updating
     * @return true if author was updated
     * or false if he doesn't exist in database
     */
    public boolean delete(Author author) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to check if the user exists
     * @param book author's book title
     * @return true is exists and false if he doesn't
     */
    public boolean isAuthorExist(Book book){
        return authorDao.isAuthorExist(book);
    }


}
