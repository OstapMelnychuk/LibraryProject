package servlets.books;

import dao.interfaces.BookDao;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search-author")
public class SearchByAuthorServlet extends HttpServlet {
  BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("show_author", true);
    req.setAttribute("books", bookService.findAllBooksByAuthor(req.getParameter("author")));
    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}
