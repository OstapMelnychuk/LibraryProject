package servlets.user;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/average-statistics")
public class GetAverageStatisticsServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");

    String userName = req.getParameter("user-name");
    req.setAttribute("admin", user.getRoleId());
    double age = userService.getAverageUserAge();
    int hits = userService.getUserHits(userName);
    long days = userService.getUserTimeFromStart(userName);

    req.setAttribute("days", days);
    req.setAttribute("age", age);
    req.setAttribute("hits", hits);

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }
}
