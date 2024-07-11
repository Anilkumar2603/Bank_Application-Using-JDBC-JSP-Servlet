package com.bank.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/generatePDF")
public class GeneratePDFServlet extends HttpServlet {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String jdbcUrl = "jdbc:mysql://localhost:3306/bankdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "Anilkumar@ak@567";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String sql = "SELECT * FROM transaction WHERE account_no = ? ORDER BY transaction_date DESC LIMIT 10";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, accountNo);
                try (ResultSet rs = pstmt.executeQuery()) {

                    // Prepare PDF document
                    Document document = new Document();
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=\"transaction_history.pdf\"");
                    PdfWriter.getInstance(document, response.getOutputStream());

                    document.open();

                    // Add title
                    Paragraph title = new Paragraph("Transaction History");
                    document.add(title);

                    // Add table
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Add table headers
                    PdfPCell cell = new PdfPCell(new Paragraph("Transaction Date"));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("Transaction Type"));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("Amount"));
                    table.addCell(cell);

                    // Populate table with data
                    while (rs.next()) {
                        table.addCell(DATE_FORMAT.format(rs.getTimestamp("transaction_date")));
                        table.addCell(rs.getString("transaction_type"));
                        table.addCell(DECIMAL_FORMAT.format(rs.getDouble("amount")));
                    }

                    document.add(table);

                    document.close();
                }
            }
        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
            response.getWriter().println("Failed to retrieve transactions or generate PDF.");
        }
    }
}
