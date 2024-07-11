<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modify Customer</title>
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
                            <a href="${pageContext.request.contextPath}/admin/modifyCustomer?action=edit&accountNo=${customer.accountNo}">Edit</a>
                            <form action="${pageContext.request.contextPath}/admin/modifyCustomer" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="accountNo" value="${customer.accountNo}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/adminDashboard.jsp">Go back to Dashboard</a>
    <% } else { %>
        <%-- Redirect to login page if session not found --%>
        <% response.sendRedirect("adminLogin.jsp"); %>
    <% } %>
</body>
</html>
