<%--
  Created by IntelliJ IDEA.
  User: leechis
  Date: 2026-02-16
  Time: 오후 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>블로그 컨텐츠 쓰기</title>
</head>
<body>
<form method="post">
    <p>
        컨텐츠 제목:
        <input type="text" name="title" style="width:80%"/>
    </p>
    <p>
        본문
    </p>
    <p>
        <textarea rows="10" name="content_body" style="width:100%"></textarea>
    </p>
    <p>
        <input type="submit" name="create" value="블로그 컨텐츠 쓰기"/>
    </p>
</form>
</body>
</html>
