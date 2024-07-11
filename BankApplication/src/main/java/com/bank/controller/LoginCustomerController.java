package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

@WebServlet("/customerLogin")
public class LoginCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String password = request.getParameter("password");

        Customer customer = validateUser(accountNo, password);
        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("customerDashboard.jsp");
        } else {
            response.sendRedirect("customerLogin.jsp?error=Invalid+credentials");
        }
    }

    private Customer validateUser(String accountNo, String password) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String sql = "SELECT * FROM customer WHERE account_no = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNo);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setFull_name(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile_no(rs.getString("mobile_no"));
                customer.setEmail_id(rs.getString("email_id"));
                customer.setAccount_type(rs.getString("account_type"));
                customer.setInitial_balance(rs.getDouble("initial_balance"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setId_proof(rs.getString("id_proof"));
                customer.setAccount_no(rs.getString("account_no"));
                customer.setPassword(rs.getString("password"));
                return customer;
            } else {
                return null; // User does not exist with given credentials
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
