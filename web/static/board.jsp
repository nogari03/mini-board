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
        <tr>
            <th colspan="4">
                [<a href="/board?command=write">게시글쓰기</a>]
            </th>
        </tr>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
        </tr>
        <c:forEach items="${list}" var="Board" >
            <tr>
                <th width="50">${Board.row_num}</th>
                <th width="300"><a href="/board?command=get&article_no=${Board.article_no}">${Board.title}</a></th>
                <th width="80">${Board.writer_name}</th>
                <th width="80">${Board.read_cnt}</th>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4" align="left">
                <jsp:include page="/static/paging.jsp">
                    <jsp:param value="${paging.currentPage}" name="page"/>
                    <jsp:param value="${paging.beginPage}" name="beginPage"/>
                    <jsp:param value="${paging.endPage}" name="endPage"/>
                </jsp:include>
            </th>
        </tr>
    </table>
</body>
</html>
