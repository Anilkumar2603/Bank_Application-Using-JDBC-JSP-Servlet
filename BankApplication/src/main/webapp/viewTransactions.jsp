<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }
        
        h2 {
            text-align: center;
            color: #007bff; /* Blue */
        }
        
        .sort-form {
            text-align: center;
            margin-bottom: 20px;
        }
        
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        table th, table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        
        table th {
            background-color: #f2f2f2;
        }
        
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        table tr:hover {
            background-color: #e9e9e9;
        }
        
        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff; /* Blue */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s;
        }
        
        a:hover {
            background-color: #0056b3; /* Darker Blue on Hover */
        }
        
        .pdf-form {
            text-align: center;
            margin-top: 20px;
        }
        
        .pdf-form button {
            padding: 10px 20px;
            background-color: #28a745; /* Green */
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .pdf-form button:hover {
            background-color: #218838; /* Darker Green on Hover */
        }
    </style>
</head>
<body>
    <h2>Last Transactions</h2>
    
    <div class="sort-form">
        <form action="${pageContext.request.contextPath}/transaction" method="get">
            <input type="hidden" name="action" value="view">
            <input type="hidden" name="accountNo" value="${sessionScope.customer.accountNo}">
            <label for="sortOrder">Sort by Date: </label>
            <select name="sortOrder" id="sortOrder">
                <option value="asc" ${sortOrder == 'asc' ? 'selected' : ''}>Ascending</option>
                <option value="desc" ${sortOrder == 'desc' ? 'selected' : ''}>Descending</option>
            </select>
            <button type="submit">Sort</button>
        </form>
    </div>
    
    <table>
        <tr>
            <th>Transaction ID</th>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.transactionId}</td>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.transactionType}</td>
                <td>${transaction.amount}</td>
            </tr>
        </c:forEach>
    </table>
    
    <div class="pdf-form">
        <form action="${pageContext.request.contextPath}/generatePDF" method="get">
            <input type="hidden" name="accountNo" value="${sessionScope.customer.accountNo}">
            <button type="submit">Print Mini Statement</button>
        </form>
    </div>
    
    <a href="${pageContext.request.contextPath}/customerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
