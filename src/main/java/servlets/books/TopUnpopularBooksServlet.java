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
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BookService bookService = new BookService();

    req.setAttribute("books", bookService.getTenTheMostUnpopularBook());
    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}
