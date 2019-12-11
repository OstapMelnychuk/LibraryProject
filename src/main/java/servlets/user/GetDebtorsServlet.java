package servlets.user;

import connector.DaoFactory;
import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/debtors")
public class GetDebtorsServlet extends HttpServlet {
  UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      List<User> users = DaoFactory.userDao().getDebtors();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.setAttribute("admin", ((User) req.getSession().getAttribute("user")).getRoleId());
    req.setAttribute("users", userService.getDebtors());

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
