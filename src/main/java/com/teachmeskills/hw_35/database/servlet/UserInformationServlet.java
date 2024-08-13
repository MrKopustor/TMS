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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user-information")
public class UserInformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");

        if (userID == null || userID.isEmpty()) {
            getServletContext().getRequestDispatcher("/WEB-INF/getUser.jsp").forward(req, resp);
        } else {
            try (Connection connection = PostgresDriverManager.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where id = ?")) {
                preparedStatement.setInt(1, Integer.parseInt(userID));
                ResultSet prepareResultSet = preparedStatement.executeQuery();

                if (prepareResultSet.next()) {
                    req.setAttribute("login", prepareResultSet.getString("login"));
                    req.setAttribute("name", prepareResultSet.getString("name"));
                    getServletContext().getRequestDispatcher("/WEB-INF/info.jsp").forward(req, resp);
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


