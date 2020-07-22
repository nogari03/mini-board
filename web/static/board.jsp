<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>게시판</title>
    <style>
    table,caption,tr,th,td{
        border: solid 1px black;
    }
    </style>
</head>
<body>
    <table>
        <caption>[<a href="/board?command=write">게시글쓰기</a>]</caption>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${list}" var="BoardVO" >
            <tr>
                <th>${BoardVO.article_no}</th>
                <th><a href="/board?command=select&article_no=${BoardVO.article_no}">${BoardVO.title}</a></th>
                <th>${BoardVO.writer_name}</th>
                <th>${BoardVO.read_cnt}</th>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4" align="left">
                <jsp:include page="/static/paging.jsp">
                    <jsp:param value="${paging.page}" name="page"/>
                    <jsp:param value="${paging.beginPage}" name="beginPage"/>
                    <jsp:param value="${paging.endPage}" name="endPage"/>
                    <jsp:param value="${paging.prev}" name="prev"/>
                    <jsp:param value="${paging.next}" name="next"/>
                </jsp:include>
            </th>
        </tr>
    </table>
</body>
</html>
