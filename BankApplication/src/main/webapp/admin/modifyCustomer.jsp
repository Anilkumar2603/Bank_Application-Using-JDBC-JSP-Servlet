<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #fff; /* Title color changed to white */
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
        }
        
        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        
        table th {
            background-color: #f2f2f2;
        }
        
        table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        table tbody tr:hover {
            background-color: #e9e9e9;
        }
        
        .action-links {
            display: inline-block;
            margin-right: 10px;
            text-decoration: none;
            color: #007bff;
            padding: 5px 10px;
            border-radius: 3px;
            background-color: #28a745; /* Green background color */
            color: #fff; /* Text color */
        }
        
        .action-links:hover {
            text-decoration: underline;
        }
        
        .delete-form {
            display: inline;
            margin: 0;
        }
        
        .delete-form button {
            background-color: #dc3545;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .delete-form button:hover {
            background-color: #c82333;
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
        <h1>Modify Customer</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Account No</th>
                    <th>Full Name</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>Email ID</th>
                    <th>Account Type</th>
                    <th>Date of Birth</th>
                    <th>ID Proof</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.accountNo}</td>
                        <td>${customer.full_name}</td>
                        <td>${customer.address}</td>
                        <td>${customer.mobile_no}</td>
                        <td>${customer.email_id}</td>
                        <td>${customer.account_type}</td>
                        <td>${customer.date_of_birth}</td>
                        <td>${customer.id_proof}</td>
                        <td>
                            <a class="action-links" href="${pageContext.request.contextPath}/modifyCustomer?action=edit&accountNo=${customer.accountNo}">Edit</a>
                            <form class="delete-form" action="${pageContext.request.contextPath}/modifyCustomer" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="accountNo" value="${customer.accountNo}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="back-link" href="${pageContext.request.contextPath}/adminDashboard.jsp">Go back to Dashboard</a>
    <% } else { %>
        <%-- Redirect to login page if session not found --%>
        <% response.sendRedirect("adminLogin.jsp"); %>
    <% } %>
</body>
</html>
