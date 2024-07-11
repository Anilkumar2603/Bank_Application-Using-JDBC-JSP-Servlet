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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA'); /* Replace with your image path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        
        .dashboard-container {
            max-width: 100%; /* Changed to 100% to occupy full width */
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent white background */
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #000000;
        }
        
        .dashboard-info {
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        
        .dashboard-info table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 10px;
        }
        
        .dashboard-info table th, .dashboard-info table td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        
        .dashboard-info table th {
            background-color: #f2f2f2;
        }
        
        .dashboard-info table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        .dashboard-info table tbody tr:hover {
            background-color: #e9e9e9;
        }
        
        .transaction-buttons-top {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        
        .transaction-buttons-top button {
            padding: 10px 20px;
            background-color: #f44336; /* Red */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .transaction-buttons-bottom {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        
        .transaction-buttons-bottom button {
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .transaction-buttons-bottom button.deposit {
            background-color: #4CAF50; /* Green */
            color: #fff;
        }
        
        .transaction-buttons-bottom button.withdraw {
            background-color: #f44336; /* Red */
            color: #fff;
        }
        
        .logout-form {
            text-align: right;
            margin-top: 20px;
        }
        
        .logout-form button {
            padding: 10px 20px;
            background-color: #f44336; /* Red */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .close-account-form {
            text-align: right;
            margin-bottom: 20px;
        }
        
        .close-account-form button {
            padding: 10px 20px;
            background-color: #f44336; /* Red */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .view-transactions-form {
            text-align: left;
            margin-top: 20px;
        }
        
        .view-transactions-form button {
            padding: 10px 20px;
            background-color: #007bff; /* Blue */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .change-pass {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .change-pass button {
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .change-pass button.change-password {
    background-color: #ffc107; /* Amber */
    color: #fff;
}
        
    </style>
</head>
<body>
    <div class="dashboard-container">
        <!-- Logout and Close Account buttons -->
        <div class="logout-form">
            <form action="logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
        
        <h1>Welcome, <%= customer.getFull_name() %></h1>
        
        <!-- Close Account form -->
        <div class="close-account-form">
            <form action="closeAccount.jsp" method="post">
                <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
                <button type="submit">Close Account</button>
            </form>
        </div>
        
        <!-- View Transactions button -->
        <div class="view-transactions-form">
            <form action="<%= request.getContextPath() %>/transaction" method="get">
                <input type="hidden" name="action" value="view">
                <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
                <button type="submit">View Transactions</button>
            </form>
        </div>
        
        <div class="dashboard-info">
            <table>
                <tr>
                    <th>Account No</th>
                    <td><%= customer.getAccountNo() %></td>
                </tr>
                <tr>
                    <th>Initial Balance</th>
                    <td><span id="balance"><%= customer.getInitial_balance() %></span></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= customer.getAddress() %></td>
                </tr>
                <tr>
                    <th>Mobile No</th>
                    <td><%= customer.getMobile_no() %></td>
                </tr>
                <tr>
                    <th>Email ID</th>
                    <td><%= customer.getEmail_id() %></td>
                </tr>
                <tr>
                    <th>Account Type</th>
                    <td><%= customer.getAccount_type() %></td>
                </tr>
                <tr>
                    <th>Date of Birth</th>
                    <td><%= customer.getDate_of_birth() %></td>
                </tr>
                <tr>
                    <th>ID Proof</th>
                    <td><%= customer.getId_proof() %></td>
                </tr>
            </table>
        </div>
        <div class="change-pass">
        <form action="changePassword.jsp" method="get">
        <button type="submit" class="change-password">Change Password</button>
    </form>
    </div>
        <!-- Transaction buttons -->
<div class="transaction-buttons-bottom">
    <form action="<%= request.getContextPath() %>/transaction" method="post">
        <button type="submit" name="action" value="deposit" class="deposit">Deposit</button>
    </form>
    
    
    
    <form action="<%= request.getContextPath() %>/transaction" method="post">
        <button type="submit" name="action" value="withdraw" class="withdraw">Withdraw</button>
    </form>
</div>
    </div>
    
    
    <!-- jQuery for AJAX -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <!-- JavaScript for AJAX transaction handling -->
    <script>
        $(document).ready(function() {
            $(".transaction-buttons-bottom button").click(function(e) {
                e.preventDefault();
                var action = $(this).val();
                performTransaction(action);
            });
            
            function performTransaction(action) {
                var amount = prompt("Enter amount:");
                if (amount == null || amount.trim() === "") {
                    return;
                }

                $.ajax({
                    url: "<%= request.getContextPath() %>/transaction",
                    type: "POST",
                    data: {
                        action: action,
                        amount: amount,
                        accountNo: "<%= customer.getAccountNo() %>"
                    },
                    dataType: "json",
                    success: function(response) {
                        if (response.status === "success") {
                            alert(action.charAt(0).toUpperCase() + action.slice(1) + " successful!");
                            $("#balance").text(response.newBalance.toFixed(2)); // Update balance dynamically
                        } else {
                            alert(response.message);
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("An error occurred: " + error);
                    }
                });
            }
        });
    </script>
</body>
</html>
