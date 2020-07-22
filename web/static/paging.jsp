<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="paging">
    <c:url var="action" value="/board/*" />
    <c:if test="${param.prev}">
        <a href="${action}?page=${param.beginPage-1}">prev</a>
    </c:if>
    <c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
        <c:choose>
            <c:when test="${param.page==index}">
                <%
                    System.out.println("1");
                %>
                ${index}
            </c:when>
            <c:otherwise>
                <%
                    System.out.println("2");
                %>
                <a href="${action}?page=${index+1}">${index+1}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${param.next}">
        <a href="${action}?page=${param.endPage+1}">next</a>
    </c:if>
</div>
</body>
</html>
