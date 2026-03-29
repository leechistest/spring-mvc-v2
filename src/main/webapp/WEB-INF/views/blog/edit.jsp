<%--
  Created by IntelliJ IDEA.
  User: leechis
  Date: 2026-03-29
  Time: 오후 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    pageContext.setAttribute("CRLF", "\r\n");
    pageContext.setAttribute("LF", "\n");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>블로그 컨텐츠 수정</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="_method" value="put"/>
    <p>글번호: ${blogCont.blg_cont_seq}</p>
    <p>
        컨텐츠 제목:
        <label>
            <input type="text" name="title" style="width:80%" value="${blogCont.title}"/>
        </label>
    </p>
    <p>
        본문
    </p>
    <p>
        <label>
            <textarea rows="10" name="contBdy" style="width:100%">${blogCont.cont_bdy}</textarea>
        </label>
    </p>
    <p>입력일: <fmt:formatDate value="${blogCont.insert_dt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
    <p>
        <input type="submit" name="create" value="블로그 컨텐츠 수정"/>
    </p>
</form>
</body>
</html>
