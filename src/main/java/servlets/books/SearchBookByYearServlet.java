package servlets.books;

import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-year")
public class SearchBookByYearServlet extends HttpServlet {
  BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int firstYear = Integer.parseInt(req.getParameter("first-year"));
    int lastYear = Integer.parseInt(req.getParameter("last-year"));

    req.setAttribute("show_year", true);
    req.setAttribute("books", bookService.findAllBooksBetweenDate(firstYear, lastYear));
    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}
