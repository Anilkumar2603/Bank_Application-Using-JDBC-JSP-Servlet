<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Closure Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            text-align: center;
            padding: 40px;
        }
        
        h1 {
            color: #dc3545; /* Red */
        }
        
        p {
            font-size: 18px;
            margin-bottom: 20px;
        }
        
        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff; /* Blue */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin: 0 10px;
            transition: background-color 0.3s;
        }
        
        a:hover {
            background-color: #0056b3; /* Darker Blue on Hover */
        }
    </style>
</head>
<body>
    <h1>Account Closure Error</h1>
    <p>There was an error while closing your account. Please check the following information and try again later:</p>
    <p>1) Check Your Account Number</p>
    <p>2) Ensure that Your Account has 0 Balance before Closure</p>
    <a href="/BankApplication/customerDashboard.jsp">Back to Dashboard</a>
    <a href="/BankApplication/closeAccount.jsp">Retry</a>
</body>
</html>
