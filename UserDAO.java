package dao;

import mydatabase.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        String q = "select email from users";
        PreparedStatement ps = con.prepareStatement(q);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            String mail = set.getString("email");
            if (mail.equals(email))
                return true;
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException {
        Connection con = MyConnection.getConnection();
        String q = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        return ps.executeUpdate();
    }

    public static String getPassword(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        String q = "select password from users where email = ?";
        PreparedStatement st = con.prepareStatement(q);
        st.setString(1, email);
        ResultSet set = st.executeQuery();
        String Pass = null;
        if (set.next()) {
            Pass = set.getString("password");
        }
        return Pass;
    }
}
