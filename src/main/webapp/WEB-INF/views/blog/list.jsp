<%--
  Created by IntelliJ IDEA.
  User: leechis
  Date: 2026-03-30
  Time: 오후 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>블로그 컨텐츠 목록</title>
</head>
<body>
<div>
    <form>
        <label>
            <input type="text" name="search" value="${blogListRequestVO.search}" style="width: 50%;"/>
        </label>
        <input type="submit" name="search_button" value="검색"/>
    </form>
</div>

<c:if test="${not empty blogListResponseVOList}">
    <table>
        <thead>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>입력일</th>
            <th>수정</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="blogListResponseVO" items="${blogListResponseVOList}">
            <tr>
                <td>${blogListResponseVO.blgContSeq}</td>
                <td>${blogListResponseVO.title}</td>
                <td>${blogListResponseVO.insertDtFormat}</td>
                <td>
                    <a href="/edit/${blogListResponseVO.blgContSeq}">수정</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty blogListResponseVOList}">
    <strong>검색 결과가 없습니다.</strong>
</c:if>
</body>
</html>
