<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        /* Additional styles specific to the admin dashboard */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA');
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }

        .dashboard-container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 0 10px rgba(90, 34, 139, 0.3);
        }

        .dashboard-container h1 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .dashboard-container ul {
            list-style-type: none;
            padding: 0;
        }

        .dashboard-container ul li {
            margin-bottom: 10px;
        }

        .dashboard-container ul li a {
            display: block;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .dashboard-container ul li a:hover {
            background-color: #3e8e41;
        }

        .logout-form {
            margin-top: 20px;
        }

        .logout-form button {
            background-color: #f44336;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .logout-form button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <%-- Check if admin session exists --%>
    <% String adminName = (String) session.getAttribute("admin"); %>
    <% if (adminName != null) { %>
        <div class="dashboard-container">
            <h1>Welcome, <%= adminName %>!</h1>
            <ul>
                <li><a href="registerCustomer">Register New Customer</a></li>
                <li><a href="modifyCustomer">Modify Existing Customer</a></li>
            </ul> 
            <!-- Logout form -->
            <form class="logout-form" action="adminLogout" method="get">
                <button type="submit">Logout</button>
            </form>
        </div>
    <% } else { %>
        <%-- Redirect to login page if session not found --%>
        <% response.sendRedirect("adminLogin.jsp"); %>
    <% } %>
</body>
</html>
