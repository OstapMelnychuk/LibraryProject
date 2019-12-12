package servlets.user;

import models.Book;
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

@WebServlet("/user-requests")
public class UserRequestsServlet extends HttpServlet {
  UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }


  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User currentUser = (User) req.getSession().getAttribute("user");
    User user = userService.getUserByName(req.getParameter("name"));


    req.setAttribute("name_show", true);
    req.setAttribute("admin", currentUser.getRoleId());

    req.setAttribute("userh", user);

    System.out.println(user);

      req.setAttribute("time", userService.getUserTimeFromStart(user.getId()));
      req.setAttribute("book1", userService.getUserBooksTaken(user.getId()));
      req.setAttribute("book2", userService.getUserBooksNotReturned(user.getId()));

    req.getRequestDispatcher("/users.jsp").include(req, resp);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
