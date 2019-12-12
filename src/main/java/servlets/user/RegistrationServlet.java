package servlets.user;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration-user")
public class RegistrationServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    Integer age = null;
    try {
      String userName = req.getParameter("user_name");
      String email = req.getParameter("email");
      age = Integer.parseInt(req.getParameter("user_age"));
      if (age < 0) {
        throw new Exception("age must not be negative");
      }
      String login = req.getParameter("regist_login");
      String password = req.getParameter("regist_password");
      String passwordConfirm = req.getParameter("confirm_password");
      if (userName.equals("") || email.equals("") || login.equals("") || password.equals("")
      || passwordConfirm.equals("")){
        req.setAttribute("message", "Fill all fields");
        throw new Exception("Fill all fields");
      }
      if (password.equals(passwordConfirm)) {
        User user1 = new User(1, userName, login, password, 2, email, age, null);
        int id = userService.createUser(user1);
        if (id == 0) {
          req.setAttribute("message", "There is such user. Choose another login");
        } else {
          req.setAttribute("message", "User was successfully signed up");
        }
      } else {
        req.setAttribute("message", "Passwords don`t match");
      }
    } catch (NumberFormatException e){
      if(age == null){
        req.setAttribute("message", "Age field must be field");
      }
    } catch (Exception e) {
      if (age == null) {
        req.setAttribute("message", "Age must be a number");
      }
      if (age <= 0) {
        req.setAttribute("message", "Age must be a bigger than 0");
      } else {
        req.setAttribute("message", "Fill all fields");
      }
    }
    req.getRequestDispatcher("/registration.jsp").include(req, resp);
  }
}
