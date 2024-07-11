package dao;

import model.Admin;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public void addAdmin(Admin admin) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String query = "INSERT INTO Admin (username, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, admin.getUsername());
        stmt.setString(2, admin.getPassword());
        stmt.executeUpdate();
    }

    public Admin getAdminByUsername(String username) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String query = "SELECT * FROM Admin WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("admin_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            return admin;
        }
        return null;
    }

    // Add more methods as needed (update, delete, etc.)
}
