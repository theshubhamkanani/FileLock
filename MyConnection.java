package mydatabase;

import java.sql.*;

public class MyConnection {

    public static Connection getConnection() {
        Connection con = null;

        String URL = "jdbc:postgresql://localhost/";
        String Database = "filelock";
        String User = "postgres";
        String Password = "Password";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL + Database, User, Password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}