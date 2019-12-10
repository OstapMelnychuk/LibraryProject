package servlets.user;

import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            if (user.getRoleId() == 1) {
                req.setAttribute("admin", ((User) req.getSession().getAttribute("user")).getRoleId());

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("users.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/home");
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
        }
    }
}
