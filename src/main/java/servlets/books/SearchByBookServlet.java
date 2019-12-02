package servlets.books;

import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-books")
public class SearchByBookServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BookService bookService = new BookService();
    req.setAttribute("isAvailable", bookService.isBookAvailable(req.getParameter("title")));
    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}

