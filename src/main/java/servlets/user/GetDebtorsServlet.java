package servlets.user;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/debtors")
public class GetDebtorsServlet extends HttpServlet {
  UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");

    req.setAttribute("admin", user.getRoleId());
    System.out.println(userService.getDebtors().get(0));
    req.setAttribute("users", userService.getDebtors());

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
