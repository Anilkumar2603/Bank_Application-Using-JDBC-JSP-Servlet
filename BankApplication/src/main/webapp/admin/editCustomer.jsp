<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
             background-image: url('https://media.licdn.com/dms/image/D5612AQHZbnxf_vAqtg/article-cover_image-shrink_720_1280/0/1684958850351?e=1726099200&v=beta&t=detD4aw_IBIlFA-b6tAYq2wZDBxbd4f7-FLiHvBWSEA');
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        
        h1 {
            text-align: center;
            color: #fff;
            margin-bottom: 20px;
        }
        
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        input[type=text], input[type=email] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        
        button[type=submit] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        
        button[type=submit]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Edit Customer Details</h1>
    <form action="${pageContext.request.contextPath}/updateCustomer" method="post">
        <input type="hidden" name="accountNo" value="${customer.accountNo}">
        
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${customer.full_name}" required><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${customer.address}"><br>
        
        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" value="${customer.mobile_no}"><br>
        
        <label for="emailId">Email ID:</label>
        <input type="email" id="emailId" name="emailId" value="${customer.email_id}"><br>
        
        <label for="accountType">Account Type:</label>
        <input type="text" id="accountType" name="accountType" value="${customer.account_type}"><br>
        
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="text" id="dateOfBirth" name="dateOfBirth" value="${customer.date_of_birth}"><br>
        
        <label for="idProof">ID Proof:</label>
        <input type="text" id="idProof" name="idProof" value="${customer.id_proof}"><br>
        
        <button type="submit">Update Customer</button>
    </form>
</body>
</html>
