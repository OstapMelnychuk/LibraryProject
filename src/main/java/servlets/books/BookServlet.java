package servlets.books;

import connector.DaoFactory;
import dao.UserDao;
import dao.interfaces.BookDao;
import dto.BookDto;
import models.User;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserDao.currentUser != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("books.jsp");

            req.setAttribute("isListExist", true);
            req.setAttribute("admin", UserDao.currentUser.getRoleId());

            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
        }
    }
}
