<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${command eq 'write'}">
        <form name="boardForm" method="post" action="/board">
            <p>제목 :</p>
            <p><input type="text" name="title"></p>
            <p>비밀번호 :</p>
            <p><input type="password" name="password"></p>
            <p>내용 :</p>
            <p><textarea name="content" ></textarea></p>
            <p><input type="submit" value="새 글 등록"></p>
            <p><input type="hidden" name="command" value="add"></p>
        </form>
    </c:when>
    <c:when test="${command eq 'select'}">
            <p>제목 :</p>
            <p><input type="text" name="title" value="${title}"></p>
            <p>내용 :</p>
            <p><textarea name="content" >${content}</textarea></p>
    </c:when>
</c:choose>

</body>
</html>
