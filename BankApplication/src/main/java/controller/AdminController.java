package controller;

import service.AdminService;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/adminLogin")
public class AdminController extends HttpServlet {
    private AdminService adminService = new AdminService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adminLogin.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Admin admin = adminService.getAdminByUsername(username);
            if (admin != null && admin.getPassword().equals(password)) {
                HttpSession session = req.getSession(true); // Create a new session or return existing
                session.setAttribute("admin", admin); // Store admin object in session
                resp.sendRedirect("adminDashboard.jsp");
            } else {
                resp.sendRedirect("adminLogin.jsp?error=Invalid+credentials");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
