package servlets.user;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-author-age")
public class GetAgeByAuthorServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");

    String authorName = req.getParameter("author-name");
    String authorSecondName = req.getParameter("author-secondname");
    String authorSurname = req.getParameter("author-surname");
    double age = userService.getAverageAgeOfUsersByAuthor(authorName, authorSecondName, authorSurname);
    req.setAttribute("admin", user.getRoleId());
    req.setAttribute("age", age);

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }
}
