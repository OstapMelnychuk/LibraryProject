package servlets.books;

import dto.BookDto;
import dto.StatisticsBookDto;
import models.User;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/statistic")
public class StatisticOfBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            BookService bookService = new BookService();

            List<BookDto> statisticsOfBooks = bookService.findAllBooks();
            List<StatisticsBookDto> statisticsBookDtos = new ArrayList<>();

            for (BookDto statistic : statisticsOfBooks) {
                System.out.println(statistic);
                statisticsBookDtos.add(new StatisticsBookDto(statistic, bookService.getStatisticOfBook(statistic)));

            }

            req.setAttribute("books", statisticsBookDtos);
            req.setAttribute("admin", ((User) req.getSession().getAttribute("user")).getRoleId());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("books.jsp");

            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
        }
    }
}
