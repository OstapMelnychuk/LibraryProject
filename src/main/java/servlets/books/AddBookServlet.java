package servlets.books;

import models.Author;
import models.Book;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("addBook.jsp");
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String authorName = req.getParameter("name");
    String authorSecondname = req.getParameter("secondname");
    String authorSurname = req.getParameter("surname");
    String title = req.getParameter("title");
    String description = req.getParameter("description");
    String date = req.getParameter("date");
    int count = Integer.parseInt(req.getParameter("quantity"));

    BookService bookService = new BookService();

    Book book = new Book(1, title, description, date, count, new Author(1, authorName, authorSecondname, authorSurname), true);

    bookService.save(book);

    req.getRequestDispatcher("/addBook.jsp").include(req, resp);

  }
}
