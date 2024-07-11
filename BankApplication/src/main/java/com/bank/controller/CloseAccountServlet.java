package com.bank.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.AccountClosureRequestDAO;
import model.AccountClosureRequest;

@WebServlet("/closeAccount")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountClosureRequestDAO accountClosureRequestDAO;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception or log appropriately
        }
        accountClosureRequestDAO = new AccountClosureRequestDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        try {
            if (canCloseAccount(accountNo)) {
                closeAccount(accountNo);
                response.sendRedirect("customer/accountClosureSuccess.jsp");
            } else {
                response.sendRedirect("customer/accountClosureError.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private boolean canCloseAccount(String accountNo) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String checkBalanceSql = "SELECT initial_balance FROM customer WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(checkBalanceSql);
            pstmt.setString(1, accountNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("initial_balance");
                return balance == 0; // Adjust your balance check logic as needed
            }
        }
        return false;
    }

    private void closeAccount(String accountNo) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            // Delete transactions first
            String deleteTransactionsSql = "DELETE FROM transaction WHERE account_no = ?";
            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteTransactionsSql)) {
                pstmt2.setString(1, accountNo);
                pstmt2.executeUpdate();
            }

            // Then delete customer
            String deleteCustomerSql = "DELETE FROM customer WHERE account_no = ?";
            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteCustomerSql)) {
                pstmt1.setString(1, accountNo);
                pstmt1.executeUpdate();
            }

            // Insert closure request
            AccountClosureRequest request = new AccountClosureRequest();
            request.setAccountNo(accountNo);
            request.setRequestDate(new java.util.Date());
            accountClosureRequestDAO.insertAccountClosureRequest(request);
        }
    }

}
