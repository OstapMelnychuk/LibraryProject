package servlets.user;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/userStatistic")
public class UserStatisticServlet extends HttpServlet {
  UserService userService;

  @Override
  public void init() throws ServletException{
    userService = new UserService();
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setAttribute("user", userService.getUserByName(req.getParameter("nick_name")));

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
