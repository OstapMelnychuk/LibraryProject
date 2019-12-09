package servlets.home;

import dao.UserDao;
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
        if (UserDao.currentUser != null) {

            BookService bookService = new BookService();

            req.setAttribute("books", bookService.findAllBook());
            req.setAttribute("admin", UserDao.currentUser.getRoleId());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");

            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
        }
    }
}
