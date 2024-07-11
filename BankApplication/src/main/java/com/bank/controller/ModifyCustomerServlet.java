package com.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

@WebServlet("/modifyCustomer")
public class ModifyCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp"); // Redirect if not logged in
            return;
        }
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String accountNo = request.getParameter("accountNo");
            Customer customer = getCustomerByAccountNo(accountNo);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/admin/editCustomer.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/modifyCustomer?error=Customer+not+found");
            }
        } else {
            List<Customer> customers = getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/admin/modifyCustomer.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp"); // Redirect if not logged in
            return;
        }
        String action = request.getParameter("action");

        if ("update".equals(action)) {
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
        } else if ("delete".equals(action)) {
            String accountNo = request.getParameter("accountNo");
            if (deleteCustomer(accountNo)) {
                response.sendRedirect(request.getContextPath() + "/modifyCustomer?message=Customer+deleted+successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/modifyCustomer?error=Failed+to+delete+customer");
            }
        }
    }

       
        // Handle other actions (edit, etc.) if needed
    

    private List<Customer> getAllCustomers() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String sql = "SELECT account_no, full_name, address, mobile_no, email_id, account_type, date_of_birth, id_proof FROM customer";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccount_no(rs.getString("account_no"));
                customer.setFull_name(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile_no(rs.getString("mobile_no"));
                customer.setEmail_id(rs.getString("email_id"));
                customer.setAccount_type(rs.getString("account_type"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setId_proof(rs.getString("id_proof"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer getCustomerByAccountNo(String accountNo) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String sql = "SELECT account_no, full_name, address, mobile_no, email_id, account_type, date_of_birth, id_proof FROM customer WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setAccount_no(rs.getString("account_no"));
                customer.setFull_name(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile_no(rs.getString("mobile_no"));
                customer.setEmail_id(rs.getString("email_id"));
                customer.setAccount_type(rs.getString("account_type"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setId_proof(rs.getString("id_proof"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
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

    private boolean deleteCustomer(String accountNo) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
        String jdbcUser = "root";
        String jdbcPassword = "Anilkumar@ak@567";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String sql = "DELETE FROM customer WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNo);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
