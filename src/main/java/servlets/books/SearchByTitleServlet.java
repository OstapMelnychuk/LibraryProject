package servlets.books;

import models.User;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-title")
public class SearchByTitleServlet extends HttpServlet {
    BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        try {
            req.setAttribute("books", bookService.findAllBookByTitle(req.getParameter("title")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("title_show", true);
        req.setAttribute("admin", user.getRoleId());

        req.getRequestDispatcher("/books.jsp").include(req, resp);
    }
}

