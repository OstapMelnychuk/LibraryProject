package servlets.books;

import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back-top")
public class TopUnpopularBooksServlet extends HttpServlet {
  BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("books", bookService.getTenTheMostUnpopularBook());
    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}
