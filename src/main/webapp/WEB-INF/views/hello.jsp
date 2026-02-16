<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring</title>
</head>
<body>
    <h2>결과 화면</h2>
    <p>${message}</p>
    <hr>
    <p>현재 서버 시간: <%= new java.util.Date() %></p>
</body>
</html>