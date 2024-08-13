package com.teachmeskills.servlet;

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
            try (Connection connection = PostgresDriverManager.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name,login) VALUES(?,?)")) {

                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userLogin);

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