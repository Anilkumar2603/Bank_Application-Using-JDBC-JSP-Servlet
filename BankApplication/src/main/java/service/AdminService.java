package service;

import dao.AdminDAO;
import model.Admin;

import java.sql.SQLException;

public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();

    public void addAdmin(Admin admin) throws SQLException {
        adminDAO.addAdmin(admin);
    }

    public Admin getAdminByUsername(String username) throws SQLException {
        return adminDAO.getAdminByUsername(username);
    }

    // Add more service methods as needed (update, delete, etc.)
}
