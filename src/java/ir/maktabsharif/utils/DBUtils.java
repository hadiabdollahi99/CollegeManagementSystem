package ir.maktabsharif.utils;

import java.sql.*;

public class DBUtils {

    public static Connection getConnection () throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/college_management";
        String username = "postgres";
        String password = "9976";

        return DriverManager.getConnection(url,username,password);
    }

    public static void executeUpdate (String sql,Object...parameter) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            for (int i = 0; i < parameter.length; i++) {
                preparedStatement.setObject(i + 1, parameter[i]);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

}
