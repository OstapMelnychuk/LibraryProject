package servlets.books;

import models.User;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-copies-title")
public class SearchCopiesByTitleServlet extends HttpServlet {
  BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");

    req.setAttribute("books", bookService.getCopiesByTitle(req.getParameter("book_title")));
    req.setAttribute("title_show2", true);
    req.setAttribute("admin", user.getRoleId());


    req.getRequestDispatcher("books.jsp").include(req, resp);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
