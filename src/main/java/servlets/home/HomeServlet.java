package servlets.home;

import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BookService bookService = new BookService();

    req.setAttribute("books", bookService.findAllBook());

    System.out.println(bookService.findAllBook().get(0).isAvailable());

    RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");

    requestDispatcher.forward(req, resp);
  }
}
