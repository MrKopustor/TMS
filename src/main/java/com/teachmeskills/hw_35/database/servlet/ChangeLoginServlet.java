package com.teachmeskills.hw_35.database.servlet;

import com.teachmeskills.database.PostgresDriverManager;
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
            try (Connection connection = PostgresDriverManager.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET login = ? WHERE id = ?")) {

                preparedStatement.setString(1, newLogin);
                preparedStatement.setInt(2, Integer.parseInt(userID));

                int prepareResultSet = preparedStatement.executeUpdate();

                if (prepareResultSet > 0) {
                    getServletContext().getRequestDispatcher("/WEB-INF/successInsert.jsp").forward(req, resp);
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unknown Error");
                getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
            }
        }
    }
}


