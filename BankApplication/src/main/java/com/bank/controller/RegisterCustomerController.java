package com.bank.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registerCustomer")
public class RegisterCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check admin session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp"); // Redirect if not logged in
            return;
        }
        
        // Forward to register customer form
        request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check admin session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp"); // Redirect if not logged in
            return;
        }
        
        String full_name = request.getParameter("full_name").trim();
        String address = request.getParameter("address").trim();
        String mobile_no = request.getParameter("mobile_no").trim();
        String email_id = request.getParameter("email_id").trim();
        String account_type = request.getParameter("account_type").trim();
        double initial_balance = Double.parseDouble(request.getParameter("initial_balance").trim());
        String date_of_birth = request.getParameter("date_of_birth").trim();
        String id_proof = request.getParameter("id_proof").trim();

        String account_no = generateRandomNumber(12);
        String password = generateRandomPassword(8);

        // Save customer details to the database
        saveCustomerToDatabase(full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, account_no, password);

        // Prepare success message
        String successMessage = "Customer registered successfully with Account Number: " + account_no + "<br>"
                + "Temporary Password For the Customer: " + password;
        
        // Set success message as attribute to be displayed on the next page
        request.setAttribute("successMessage", successMessage);
        
        // Forward to success page
        request.getRequestDispatcher("registrationSuccess.jsp").forward(request, response);
    }

    private String generateRandomNumber(int length) {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Append a random digit between 0 and 9
        }
        return sb.toString();
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void saveCustomerToDatabase(String full_name, String address, String mobile_no, String email_id, String account_type, double initial_balance, String date_of_birth, String id_proof, String account_no, String password) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        String sql = "INSERT INTO customer (full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, account_no, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, full_name);
            pstmt.setString(2, address);
            pstmt.setString(3, mobile_no);
            pstmt.setString(4, email_id);
            pstmt.setString(5, account_type);
            pstmt.setDouble(6, initial_balance);
            pstmt.setString(7, date_of_birth);
            pstmt.setString(8, id_proof);
            pstmt.setString(9, account_no);
            pstmt.setString(10, password);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
