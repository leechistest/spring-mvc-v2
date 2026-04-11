<%--
  Created by IntelliJ IDEA.
  User: leechis
  Date: 2026-03-29
  Time: 오후 12:37
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

<div>
    <form method="post" action="/delete">
        <input type="hidden" name="_method" value="delete"/>
        <input type="hidden" name="blogContSeq" value="${blogCont.blg_cont_seq}"/>
        <input type="submit" name="delete_button" value="삭제"/>
    </form>
</div>

<div>
    <input type="text" id="cmtBdy" style="width:40%"/>
    <input type="password" id="tmpPw" style="width:40%"/>
    <input type="button" id="btn_comment_add" value="댓글 쓰기"/>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    $("#btn_comment_add").click(function () {
        let blgContSeq = ${blogCont.blg_cont_seq};
        let cmtBdy = $("#cmtBdy").val();
        let tmpPw = $("#tmpPw").val();

        let send_data = {};
        if (tmpPw === "") {
            send_data = {
                "blgContSeq": blgContSeq,
                "cmtBdy": cmtBdy
            };
        } else {
            send_data = {
                "blgContSeq": blgContSeq,
                "cmtBdy": cmtBdy,
                "tmpPw": tmpPw,
            };
        }

        $.post("/comment/add", send_data, function (data) {
            console.log(data);
        });
    });
</script>
</body>
</html>
