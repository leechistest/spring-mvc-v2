<%--
  Created by IntelliJ IDEA.
  User: leechis
  Date: 2026-03-29
  Time: 오후 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    pageContext.setAttribute("CRLF", "\r\n");
    pageContext.setAttribute("LF", "\n");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>블로그 컨텐츠 읽기</title>
</head>
<body>
<p>글번호: ${blogCont.blg_cont_seq}</p>
<p>제목: ${blogCont.title}</p>
<hr/>
<div>
    ${fn:replace(fn:replace(blogCont.cont_bdy, CRLF, '<br/>'), LF, '<br/>')}
</div>
<hr/>
<p>입력일: <fmt:formatDate value="${blogCont.insert_dt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
</body>
</html>
