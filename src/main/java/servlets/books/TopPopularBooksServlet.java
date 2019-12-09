package servlets.books;

import dao.UserDao;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/top")
public class TopPopularBooksServlet extends HttpServlet {
  BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("admin", UserDao.currentUser.getRoleId());
    req.setAttribute("books", bookService.getTenTheMostPopularBook());

    req.getRequestDispatcher("/books.jsp").include(req, resp);
  }
}
