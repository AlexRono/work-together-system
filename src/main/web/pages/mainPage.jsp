<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
    <style>

        body {
            font-family: 'Open Sans', sans-serif;
            background: url("../media/background_1.jpg");
            margin: 0;
            width:100%;
            text-align:center;
        }
        .parent {
            margin: 10%;
        }
        form{
            background: white;
            width: 300px;
            border-radius: 6px;
            margin: 0 auto 0 auto;
            padding: 10px 10px 10px 10px;
            border: #2980b9 4px solid;
            height: 80px;
        }
        .insideform{
            width: inherit;
            margin: auto;
            color: black;
            font-weight: bold;
        }

    </style>
</head>
<body>

<div class="parent">
    <h1 style="color:white;">Welcome to the project cooperation system!</h1>
    <h2 style="color:white;">The project was implemented for educational purposes by Aleksandr Kliushin</h2>
    <h3 style="color:white;">Please enter your login and password</h3>

    <form method="post" action="/login">
        <div class="insideform">
            <p>Login: <input type="text" name="login" value="${param['login']}"></p>
            <p>Password: <input type="password" name="password"></p>
            <br>
            <p><input type="submit"></p>
        </div>
    </form>

</div>
</body>
</html>