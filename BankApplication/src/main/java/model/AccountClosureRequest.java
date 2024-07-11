package model;

import java.util.Date;

public class AccountClosureRequest {
    private int request_id;
    private String account_no;
    private Date request_date;

    // Getters and Setters
    public int getRequestId() {
        return request_id;
    }

    public void setRequestId(int request_id) {
        this.request_id = request_id;
    }

    public String getAccountNo() {
        return account_no;
    }

    public void setAccountNo(String account_no) {
        this.account_no = account_no;
    }

    public Date getRequestDate() {
        return request_date;
    }

    public void setRequestDate(Date request_date) {
        this.request_date = request_date;
    }
}