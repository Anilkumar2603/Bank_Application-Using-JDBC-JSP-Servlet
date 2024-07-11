<%@ page import="model.Customer" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    // Get existing session without creating a new one
    if (session == null || session.getAttribute("customer") == null) {
        response.sendRedirect("customerLogin.jsp");
        return;
    }
    Customer customer = (Customer) session.getAttribute("customer");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function performTransaction(action) {
            var amount = prompt("Enter amount:");
            if (amount == null || amount == "") {
                return;
            }

            $.ajax({
                url: "transaction",
                type: "POST",
                data: {
                    action: action,
                    amount: amount,
                    accountNo: "<%= customer.getAccountNo() %>"
                },
                dataType: "json",
                success: function(response) {
                    if (response.status === "success") {
                        $("#balance").text("Balance: " + response.newBalance);
                        alert(action.charAt(0).toUpperCase() + action.slice(1) + " successful!");
                    } else {
                        alert(response.message);
                    }
                },
                error: function(xhr, status, error) {
                    alert("An error occurred: " + error);
                }
            });
        }
    </script>
</head>
<body>
    <h1>Welcome, <%= customer.getFull_name() %></h1>
    <p>Account No: <%= customer.getAccountNo() %></p>
    <p id="balance">Balance: <%= customer.getInitial_balance() %></p>

    <button onclick="performTransaction('deposit')">Deposit</button>
    <button onclick="performTransaction('withdraw')">Withdraw</button>

    <form action="<%= request.getContextPath() %>/transaction" method="get">
        <input type="hidden" name="action" value="view">
        <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
        <button type="submit">View Transactions</button>
    </form>

    <form action="${pageContext.request.contextPath}/generatePDF" method="get">
        <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
        <button type="submit">Print Mini Statement</button>
    </form>

    <form action="closeAccount.jsp" method="post">
        <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
        <button type="submit">Close Account</button>
    </form>

    <form action="logout" method="post">
        <button type="submit">Logout</button>
    </form>

    <form action="changePassword.jsp" method="get">
        <button type="submit">Change Password</button>
    </form>
</body>
</html>
