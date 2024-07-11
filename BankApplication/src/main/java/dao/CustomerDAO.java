package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDAO {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "Anilkumar@ak@567";

    private static final String SELECT_CUSTOMER_BY_ACCOUNT_AND_PASSWORD = 
            "SELECT * FROM Customer WHERE account_no = ? AND password = ?";

    public Customer getCustomerByAccountAndPassword(String accountNo, String password) {
        Customer customer = null;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_CUSTOMER_BY_ACCOUNT_AND_PASSWORD)) {
            pstmt.setString(1, accountNo);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}