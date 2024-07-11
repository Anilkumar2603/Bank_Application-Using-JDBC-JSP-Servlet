<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA'); /* Replace with your image path */
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }
        
        .container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        h2 {
            text-align: center;
            color: #007bff;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            font-weight: bold;
        }
        
        .form-group input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 14px;
        }
        
        .form-group button {
            padding: 10px 20px;
            background-color: #f44336; /* Red */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .form-group button:hover {
            background-color: #d32f2f; /* Darker Red on Hover */
        }
        
        .form-group a {
            display: block;
            margin-top: 10px;
            text-align: center;
            color: #007bff;
            text-decoration: none;
        }
        
        .customer-info {
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        
        .customer-info table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .customer-info table th, .customer-info table td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        
        .customer-info table th {
            background-color: #f2f2f2;
        }
        
        .customer-info table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        .customer-info table tbody tr:hover {
            background-color: #e9e9e9;
        }
        
        /* Styling for balance */
        .balance {
            color: red; /* Default red color */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Close Account</h2>
        
        <c:if test="${sessionScope.customer != null}">
            <div class="customer-info">
                <table>
                    <tr>
                        <th>Full Name</th>
                        <td>${sessionScope.customer.full_name}</td>
                    </tr>
                    <tr>
                        <th>Account Number</th>
                        <td>${sessionScope.customer.accountNo}</td>
                    </tr>
                    <tr>
                        <th>Address</th>
                        <td>${sessionScope.customer.address}</td>
                    </tr>
                    <tr>
                        <th>Mobile Number</th>
                        <td>${sessionScope.customer.mobile_no}</td>
                    </tr>
                    <tr>
                        <th>Email ID</th>
                        <td>${sessionScope.customer.email_id}</td>
                    </tr>
                    <tr>
                        <th>Account Type</th>
                        <td>${sessionScope.customer.account_type}</td>
                    </tr>
                    <tr>
                        <th>Date of Birth</th>
                        <td>${sessionScope.customer.date_of_birth}</td>
                    </tr>
                    <tr>
                        <th>ID Proof</th>
                        <td>${sessionScope.customer.id_proof}</td>
                    </tr>
                    <tr>
                        <th>Balance</th>
                        <td class="balance" style="color: <c:choose>
                                                      <c:when test='${sessionScope.customer.initial_balance > 0}'>red</c:when>
                                                      <c:otherwise>green</c:otherwise>
                                                      </c:choose>;">
                            ${sessionScope.customer.initial_balance}
                        </td>
                    </tr>
                </table>
            </div>
            
            <form action="${pageContext.request.contextPath}/closeAccount" method="post">
                <div class="form-group">
                    <label for="accountNo">Confirm Your Account Number:</label>
                    <input type="text" id="accountNo" name="accountNo" required>
                </div>
                
                <div class="form-group">
                    <button type="submit">Close Account</button>
                </div>
                
                <div class="form-group">
                    <a href="${pageContext.request.contextPath}/customerDashboard.jsp">Back To Dashboard</a>
                </div>
            </form>
        </c:if>
        
        <c:if test="${empty sessionScope.customer}">
            <p>Please login to close your account.</p>
            <a href="${pageContext.request.contextPath}/customerLogin.jsp">Login Now!</a>
        </c:if>
    </div>
</body>
</html>
