package dao.interfaces.mappers;

import connector.DaoFactory;
import dao.BookDaoImpl;
import dao.interfaces.mappers.mapperInterface.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AuthorDto;
import dto.BookDto;
import models.Book;

public class BookMapper implements Mapper {
    public List<BookDto> rowMapper(ResultSet resultSet) throws SQLException {
        try {
            List<BookDto> bookList = new ArrayList<BookDto>();

            while (resultSet.next()) {
                String title = resultSet.getString(1);
                String bookDescription = resultSet.getString(2);
                String dateOfPublishment = resultSet.getString(3);
                String name = resultSet.getString(4);
                String secondname = resultSet.getString(5);
                String surname = resultSet.getString(6);
                boolean isAvailable = DaoFactory.bookDao().isBookAvailable(title);

                BookDto book = new BookDto(title, bookDescription, dateOfPublishment, new AuthorDto(name, secondname, surname), isAvailable);

                bookList.add(book);
            }


            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}
