package dao;

import java.sql.*;
import model.AccountClosureRequest;

public class AccountClosureRequestDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "Anilkumar@ak@567";

    private static final String INSERT_ACCOUNT_CLOSURE_REQUEST_SQL = "INSERT INTO account_closure_request (account_no, request_date) VALUES (?, ?)";

    public void insertAccountClosureRequest(AccountClosureRequest request) throws SQLException {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_ACCOUNT_CLOSURE_REQUEST_SQL)) {
            pstmt.setString(1, request.getAccountNo());
            pstmt.setTimestamp(2, new Timestamp(request.getRequestDate().getTime()));
            pstmt.executeUpdate();
        }
    }
}
