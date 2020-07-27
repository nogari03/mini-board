<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../nav2.jsp" />
<div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th colspan="4">
                    <p>[<a href="/board?command=write">게시글쓰기</a>]</p>
                </th>
            </tr>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
        </thead>
        <c:forEach items="${list}" var="Board" >
            <tr>
                <th width="50">${Board.row_num}</th>
                <th width="300"><a href="./board?command=get&article_no=${Board.article_no}">${Board.title}</a>
                    <%
                        java.util.Date date = new Date();
                        String regdate = request.getParameter("regdate");
                        System.out.println(regdate);
                        long now = System.currentTimeMillis();
                        long inputDate = date.getTime();
                        String mark = "";
                    %>
                </th>
                <th width="80">${Board.writer_name}</th>
                <th width="80">${Board.read_cnt}</th>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4">
                <jsp:include page="./paging.jsp">
                    <jsp:param value="${paging.currentPage}" name="page"/>
                    <jsp:param value="${paging.beginPage}" name="beginPage"/>
                    <jsp:param value="${paging.endPage}" name="endPage"/>
                </jsp:include>
            </th>
        </tr>
    </table>
</div>
</body>
</html>
