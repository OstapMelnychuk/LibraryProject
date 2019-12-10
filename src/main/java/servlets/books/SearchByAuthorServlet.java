package servlets.books;

import models.User;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-author")
public class SearchByAuthorServlet extends HttpServlet {
    BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        req.setAttribute("books", bookService.findAllBooksByAuthor(req.getParameter("author")));
        req.setAttribute("author_show", true);
        req.setAttribute("admin", user.getRoleId());


        req.getRequestDispatcher("/books.jsp").include(req, resp);
    }
}
