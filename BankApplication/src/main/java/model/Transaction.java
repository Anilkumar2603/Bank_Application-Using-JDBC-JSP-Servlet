package model;

import java.util.Date;

public class Transaction {
    private int transaction_id;
    private String account_no;
    private Date transactionDate;
    private String transactionType; // Ensure this field exists
    private double amount;

    // Getters and Setters
    public int getTransactionId() {
        return transaction_id;
    }

    public void setTransactionId(int transactionId) {
        this.transaction_id = transactionId;
    }

    public String getAccountNo() {
        return account_no;
    }

    public void setAccountNo(String account_no) {
        this.account_no = account_no;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
