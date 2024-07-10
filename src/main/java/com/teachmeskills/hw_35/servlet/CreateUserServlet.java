package com.teachmeskills.hw_35.servlet;

import com.teachmeskills.hw_35.database.PostgresDriverManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name").trim();
        String userLogin = req.getParameter("login").trim();

        if (userLogin == null || userLogin.isEmpty() || userName == null || userName.isEmpty()) {
            getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(req, resp);
        } else {
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            try {
                PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
                connection = driverManager.getConnection();

                preparedStatement = connection.prepareStatement("INSERT INTO users(name,login) VALUES(?,?)");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userLogin);

                int prepareResultSet = preparedStatement.executeUpdate();

                if (prepareResultSet > 0) {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>Success insert</h1>");
                    writer.println("<a href=\"main\">Go to the main page</a>");
                } else {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>Error insert</h1>");
                    writer.println("<a href=\"main\">Go to the main page</a>");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>There was an unforeseen error</h1>");
                    writer.println("<a href=\"main\">Go to the main page</a>");
                }
            }
        }
    }
}
