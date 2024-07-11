<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
</head>
<body>
    <h2>Close Account</h2>
    <c:if test="${sessionScope.customer != null}">
        <form action="${pageContext.request.contextPath}/closeAccount" method="post">
            Confirm Your Account Number: <input type="text" name="accountNo"><br><br>
            <button type="submit">Close Account</button>
            <a href="/BankApplication/customerDashboard.jsp">Back To Dashboard</a>
        </form>
    </c:if>
    <c:if test="${empty sessionScope.customer}">
        <p>Please login to close your account.</p>
        <a href="/BankApplication/customerLogin.jsp">Login Now!</a>
    </c:if>
</body>
</html>
