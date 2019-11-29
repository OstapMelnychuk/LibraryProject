package servlets;

import connector.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class Servlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Connector connector = new Connector();
    Connector.connectToDB();
    req.setAttribute("users",connector.firstStatement());
    req.getRequestDispatcher("users.jsp").forward(req,resp);

  }
}
