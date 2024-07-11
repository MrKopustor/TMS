package com.teachmeskills.hw_35.database;

import java.sql.*;

public class Sample {
    public static void main(String[] args) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        connection.setAutoCommit(false);


        PreparedStatement preparedStatement = null;
        ResultSet preparedResultSet = null;
        try {

            preparedStatement = connection.prepareStatement("select * from users where id > ? and id < ?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 4);

            preparedResultSet = preparedStatement.executeQuery();


            while (preparedResultSet.next()) {
                System.out.println(preparedResultSet.getInt("id"));
                System.out.println(preparedResultSet.getString("name"));
                System.out.println(preparedResultSet.getInt(3));

                connection.commit();
            }
        } catch (Exception ex) {
            connection.rollback();
            ex.printStackTrace();
            System.out.println("Exception!!!");
        } finally {

            if (connection != null) {
                connection.close();
            }
        }
    }
}
