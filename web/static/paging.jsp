<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>paging module</title>
</head>
<body>
<div id="paging">
    <c:url var="action" value="/board" />
    <c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
        <c:choose>
            <c:when test="${param.page==index}">
                ${index}
            </c:when>
            <c:otherwise>
                <a href="${action}?page=${index}">${index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
</body>
</html>
