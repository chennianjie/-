package test.humantask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 1/7/2021
 */
public class OracleConnection {

    private String driverName = "";
    private String dbURL = "";
    private static String user = "";
    private String password = "";
    public static OracleConnection connection = null;

    private OracleConnection() {
        driverName = "oracle.jdbc.driver.OracleDriver";
        dbURL = "jdbc:oracle:thin:@10.35.62.25:1521:IQMNAV";
        user = "IQM_HUMANTASK";
        password = "IQM_HUMANTASK";
    }

    public static String getUser() {
        return user;
    }

    public static Connection getConnection() {
        Connection conn = null;
        if (connection == null) {
            try {
                connection = new OracleConnection();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        try {
            Class.forName(connection.driverName);
            conn = DriverManager.getConnection(connection.dbURL,
                    connection.user, connection.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void close(Statement statement, Connection con) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        Connection connection = OracleConnection.getConnection();
//        System.out.println(connection);
        System.out.println(System.getProperty("user.dir"));
    }
}

