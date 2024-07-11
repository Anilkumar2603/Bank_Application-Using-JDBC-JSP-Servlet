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

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request (if needed)
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request (update customer details)
        String accountNo = request.getParameter("accountNo");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        if (updateCustomer(accountNo, fullName, address, mobileNo, emailId, accountType, dateOfBirth, idProof)) {
            response.sendRedirect(request.getContextPath() + "/modifyCustomer?message=Customer+updated+successfully");
        } else {
            response.sendRedirect(request.getContextPath() + "/modifyCustomer?error=Failed+to+update+customer");
        }
    }

    private boolean updateCustomer(String accountNo, String fullName, String address, String mobileNo, String emailId, String accountType, String dateOfBirth, String idProof) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String sql = "UPDATE customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, date_of_birth = ?, id_proof = ? WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, address);
            pstmt.setString(3, mobileNo);
            pstmt.setString(4, emailId);
            pstmt.setString(5, accountType);
            pstmt.setString(6, dateOfBirth);
            pstmt.setString(7, idProof);
            pstmt.setString(8, accountNo);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
