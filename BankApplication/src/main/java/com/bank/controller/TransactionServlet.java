package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.TransactionDAO;
import model.Transaction;
import model.Customer;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDAO transactionDAO;

    public void init() {
        transactionDAO = new TransactionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        try {
            switch (action) {
                case "view":
                    viewTransactions(request, response);
                    break;
                default:
                    response.sendRedirect("customerDashboard.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        try {
            switch (action) {
                case "deposit":
                    jsonResponse = deposit(request, response, session);
                    break;
                case "withdraw":
                    jsonResponse = withdraw(request, response, session);
                    break;
                default:
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Invalid action");
                    break;
            }
        } catch (SQLException e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", e.getMessage());
        }

        out.print(jsonResponse);
        out.flush();
    }

    private JSONObject deposit(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws SQLException {
        String accountNo = request.getParameter("accountNo");
        double amount = Double.parseDouble(request.getParameter("amount"));

        JSONObject jsonResponse = new JSONObject();

        try {
            updateBalance(accountNo, amount, "deposit");
            double newBalance = getBalance(accountNo);

            // Update session with the new balance
            Customer customer = (Customer) session.getAttribute("customer");
            customer.setInitial_balance(newBalance);
            session.setAttribute("customer", customer);

            jsonResponse.put("status", "success");
            jsonResponse.put("newBalance", newBalance);
        } catch (SQLException e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", e.getMessage());
        }

        return jsonResponse;
    }

    private JSONObject withdraw(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws SQLException {
        String accountNo = request.getParameter("accountNo");
        double amount = Double.parseDouble(request.getParameter("amount"));

        JSONObject jsonResponse = new JSONObject();

        try {
            updateBalance(accountNo, -amount, "withdraw");
            double newBalance = getBalance(accountNo);

            // Update session with the new balance
            Customer customer = (Customer) session.getAttribute("customer");
            customer.setInitial_balance(newBalance);
            session.setAttribute("customer", customer);

            jsonResponse.put("status", "success");
            jsonResponse.put("newBalance", newBalance);
        } catch (SQLException e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", e.getMessage());
        }

        return jsonResponse;
    }

    private void viewTransactions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder == null) {
            sortOrder = "desc";
        }
        List<Transaction> transactions = transactionDAO.getLast10Transactions(accountNo, sortOrder);
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/customer/viewTransactions.jsp").forward(request, response);
    }

    private void updateBalance(String accountNo, double amount, String type) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String selectBalanceSql = "SELECT initial_balance FROM customer WHERE account_no = ?";
            String updateBalanceSql = "UPDATE customer SET initial_balance = initial_balance + ? WHERE account_no = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectBalanceSql);
            selectStmt.setString(1, accountNo);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("initial_balance");
                if (type.equals("withdraw") && balance - amount < 0) {
                    throw new SQLException("Insufficient balance");
                }
                PreparedStatement updateStmt = conn.prepareStatement(updateBalanceSql);
                updateStmt.setDouble(1, amount);
                updateStmt.setString(2, accountNo);
                updateStmt.executeUpdate();

                Transaction transaction = new Transaction();
                transaction.setAccountNo(accountNo);
                transaction.setType(type);
                transaction.setAmount(amount);
                transaction.setTransactionDate(new java.util.Date());
                transactionDAO.insertTransaction(transaction);
            }
        }
    }

    private double getBalance(String accountNo) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        double balance = 0.0;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String selectBalanceSql = "SELECT initial_balance FROM customer WHERE account_no = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectBalanceSql);
            selectStmt.setString(1, accountNo);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("initial_balance");
            }
        }

        return balance;
    }
}
