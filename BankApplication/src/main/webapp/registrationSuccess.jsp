<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?eufvy=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA'); /* Replace with your image path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        
        .container {
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.8); /* Transparent white background for content */
            padding: 20px;
        }
        
        h1 {
            color: #333;
        }
        
        .message-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 20px auto;
        }
        
        .message-container p {
            margin: 10px 0;
        }
        
        .links-container {
            margin-top: 20px;
        }
        
        .links-container a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        
        .links-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Successful</h1>
        <div class="message-container">
            <%-- Display success message from the request attribute --%>
            <p><%= request.getAttribute("successMessage") %></p>
        </div>
        <div class="links-container">
            <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
            <a href="registerCustomer.jsp">Register Another Customer</a>
        </div>
    </div>
</body>
</html>
