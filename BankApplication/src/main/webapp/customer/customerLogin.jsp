<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
</head>
<body>
    <h1>Customervcb Login</h1>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Invalid credentials. Please try again.</p>
    <% } %>
    <form action="customerLogin" method="post">
        <label for="account_no">Account No:</label>
        <input type="text" id="account_no" name="accountNo" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>
