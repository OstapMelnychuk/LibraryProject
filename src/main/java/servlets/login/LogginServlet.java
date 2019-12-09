package servlets.login;

import connector.DaoFactory;
import dao.UserDao;
import models.User;
import servlets.home.HomeServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserDao.currentUser = null;

    req.setAttribute("admin", 0);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserDao currentUser = DaoFactory.userDao();

    String login =  req.getParameter("login");
    String password = req.getParameter("password");

    currentUser.logIn(login, password);

    if(UserDao.currentUser != null) {
      resp.sendRedirect("/home");
    }
  }
}
