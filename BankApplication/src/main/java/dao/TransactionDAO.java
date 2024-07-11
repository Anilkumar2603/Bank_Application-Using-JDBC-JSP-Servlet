package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class TransactionDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "Anilkumar@ak@567";

    private static final String INSERT_TRANSACTION_SQL = "INSERT INTO transaction (account_no, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?)";

    public void insertTransaction(Transaction transaction) throws SQLException {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_TRANSACTION_SQL)) {
            pstmt.setString(1, transaction.getAccountNo());
            pstmt.setString(2, transaction.getTransactionType());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setTimestamp(4, new Timestamp(transaction.getTransactionDate().getTime()));
            pstmt.executeUpdate();
        }
    }

    public List<Transaction> getLast10Transactions(String accountNo, String sortOrder) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE account_no = ? ORDER BY transaction_date " + 
                     ("asc".equalsIgnoreCase(sortOrder) ? "ASC" : "DESC") + " LIMIT 10";
        
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNo(rs.getString("account_no"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transaction.setType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getDouble("amount"));
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
