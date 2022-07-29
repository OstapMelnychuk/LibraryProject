package servlets.books;

import models.Author;
import models.Book;
import models.User;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    public static final String NEGATIVE_COUNT = "Count can't be negative";
    public static final String SUCCESS = "Book successfully added";
    public static final String NOT_NUMBER = "Quantity is not a number";

    User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = (User) req.getSession().getAttribute("user");

        System.out.println("User is " + user);

        if (user != null) {
            if (user.getRoleId() == 1) {
                req.setAttribute("admin", ((User) req.getSession().getAttribute("user")).getRoleId());

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("addBook.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/home");
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("admin", user.getRoleId());

        try {
            int count = Integer.parseInt(req.getParameter("quantity"));

            if (count >= 0) {
                String authorName = req.getParameter("name");
                String authorSecondname = req.getParameter("secondname");
                String authorSurname = req.getParameter("surname");
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                String date = req.getParameter("date");

                BookService bookService = new BookService();


                Book book = new Book(1, title, description, date, count, new Author(1, authorName, authorSecondname, authorSurname), true);

                try {
                    bookService.save(book);
                } catch (Exception e) {

                }

                req.setAttribute("message", SUCCESS);

            } else {
                req.setAttribute("message", NEGATIVE_COUNT);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", NOT_NUMBER);
        }

        req.getRequestDispatcher("/addBook.jsp").include(req, resp);

    }
}
