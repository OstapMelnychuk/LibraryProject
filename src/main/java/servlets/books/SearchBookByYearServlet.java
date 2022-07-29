package servlets.books;

import models.User;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-year")
public class SearchBookByYearServlet extends HttpServlet {
    public static final String NEGATIVE_YEAR = "The years cannot be negative";
    public static final String YEARS_ARE_NOT_IN_ORDER = "Years are not in order, perhaps you meant ";
    public static final String YEARS_ARE_INCORECT = "Your year is too big or it isn't number. The maximum number is 2147483647";

    BookService bookService;


    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        try {
            int firstYear = Integer.parseInt(req.getParameter("first-year"));
            int lastYear = Integer.parseInt(req.getParameter("last-year"));

            if (!bookService.isEnteredInTheOrderOfYears(firstYear, lastYear) && !bookService.isNegative(firstYear, lastYear)) {
                req.setAttribute("error", YEARS_ARE_NOT_IN_ORDER + lastYear + " and " + firstYear);
            } else if (bookService.isNegative(firstYear, lastYear)) {
                req.setAttribute("error", NEGATIVE_YEAR);
            } else {
                req.setAttribute("books", bookService.findAllBooksBetweenDate(firstYear, lastYear));
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", YEARS_ARE_INCORECT);
        }

        req.setAttribute("year_show", true);
        req.setAttribute("admin", user.getRoleId());

        req.getRequestDispatcher("/books.jsp").include(req, resp);
    }
}
