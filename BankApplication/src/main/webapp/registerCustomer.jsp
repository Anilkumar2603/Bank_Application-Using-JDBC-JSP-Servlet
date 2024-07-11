<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Customer</title>
    <style>
        /* Background image */
        body {
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA');
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
        }

        /* Form container */
        .form-container {
            background-color: rgba(255, 255, 255, 0.9);
            width: 400px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        /* Form inputs */
        .form-container label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .form-container input[type="text"],
        .form-container input[type="email"],
        .form-container input[type="number"],
        .form-container input[type="date"],
        .form-container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
            font-size: 16px;
        }

        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }
                .back-link {
            display: inline-block;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            color: #fff; /* Text color changed to white */
            background-color: #007bff; /* Box background color */
            padding: 10px 20px; /* Padding for the box */
            border-radius: 5px; /* Rounded corners for the box */
            transition: background-color 0.3s;
        }
        
        .back-link:hover {
            background-color: #0056b3; /* Darker color on hover */
            text-decoration: none;
        }
    </style>
</head>
<body>
    <%-- Check if admin session exists --%>
    <% if (session.getAttribute("admin") != null) { %>
        <div class="form-container">
            <h1>Register Customer</h1>
            <form action="registerCustomer" method="post">
                <label for="full_name">Full Name:</label>
                <input type="text" id="full_name" name="full_name" required><br><br>
                
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required><br><br>
                
                <label for="mobile_no">Mobile No:</label>
                <input type="text" id="mobile_no" name="mobile_no" required><br><br>
                
                <label for="email_id">Email ID:</label>
                <input type="email" id="email_id" name="email_id" required><br><br>
                
                <label for="account_type">Account Type:</label>
                <select id="account_type" name="account_type" required>
                    <option value="Savings">Savings Account</option>
                    <option value="Current">Current Account</option>
                </select><br><br>
                
                <label for="initial_balance">Initial Balance:</label>
                <input type="number" id="initial_balance" name="initial_balance" min="1000" required><br><br>
                
                <label for="date_of_birth">Date of Birth:</label>
                <input type="date" id="date_of_birth" name="date_of_birth" required><br><br>
                
                <label for="id_proof">ID Proof:</label>
                <input type="text" id="id_proof" name="id_proof" required><br><br>
                
                <input type="submit" value="Register">
                <a class="back-link" href="${pageContext.request.contextPath}/adminDashboard.jsp">Go back to Dashboard</a>
            </form>
        </div>
    <% } else { %>
        <%-- Redirect to login page if session not found --%>
        <% response.sendRedirect("adminLogin.jsp"); %>
    <% } %>
</body>
</html>
