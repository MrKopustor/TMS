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

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/changeLogin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID").trim();
        String newLogin = req.getParameter("login").trim();

        if (userID == null || userID.isEmpty()) {
            getServletContext().getRequestDispatcher("/WEB-INF/changeLogin.jsp").forward(req, resp);
        } else {
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            try {
                PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
                connection = driverManager.getConnection();

                preparedStatement = connection.prepareStatement("UPDATE users SET login = ? WHERE id = ?");
                preparedStatement.setString(1, newLogin);
                preparedStatement.setInt(2, Integer.parseInt(userID));

                int prepareResultSet = preparedStatement.executeUpdate();

                if (prepareResultSet > 0) {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>Success change login</h1>");
                    writer.println("<a href=\"main\">Go to the main page</a>");
                } else {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>Error change login</h1>");
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
                }catch (Exception e) {
                    resp.setContentType("text/html");
                    var writer = resp.getWriter();
                    writer.println("<h1>There was an unforeseen error</h1>");
                    writer.println("<a href=\"main\">Go to the main page</a>");
                }
            }
        }
    }
}


