package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        if (newPassword.equals(confirmPassword) && isValidPassword(newPassword)) {
            Customer customer = (Customer) session.getAttribute("customer");
            String accountNo = customer.getAccountNo();

            if (updatePassword(accountNo, newPassword)) {
                customer.setPassword(newPassword);
                session.setAttribute("customer", customer);

                // Set success message
                request.setAttribute("message", "Password changed successfully");
            } else {
                // Set error message if update fails
                request.setAttribute("error", "Failed to change password");
            }
        } else {
            // Set error message if passwords don't match or requirements are not met
            request.setAttribute("error", "Passwords do not match or do not meet requirements");
        }

        // Forward to the changePassword.jsp with appropriate message or error
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least one uppercase letter, one special character, one number, and be at least 8 characters long
        return password.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.*[0-9]).{8,}$");
    }

    private boolean updatePassword(String accountNo, String newPassword) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String sql = "UPDATE customer SET password = ? WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, accountNo);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
