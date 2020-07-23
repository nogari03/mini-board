<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <style>
        table,th,tr,td{
            border: solid 1px black;
        }
        th {
            width: 50px;
        }
        td {
            width: 300px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${command eq 'write'}">
        <form name="boardForm" method="post" action="/board">
            <p >제목 :</p>
            <p><input type="text" name="title"></p>
            <p>비밀번호 :</p>
            <p><input type="password" name="password"></p>
            <p>내용 :</p>
            <p><textarea name="content" ></textarea></p>
            <p><input type="submit" value="새 글 등록"></p>
            <p><input type="hidden" name="command" value="add"></p>
        </form>
    </c:when>
    <c:when test="${command eq 'get'}">
        <table>
            <tr>
                <th>번호</th>
                <td>${vo.article_no}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${vo.writer_name}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${vo.title}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${vo.content}</td>
            </tr>
            <tr>
                <td colspan="2">
                    [<a href="/board">목록</a>] [<a href="/board?command=updateCheck&article_no=${vo.article_no}">게시글수정</a>] [<a href="/board?command=deleteCheck&article_no=${vo.article_no}">게시글삭제</a>]
                </td>
            </tr>
        </table>
    </c:when>
    <c:when test="${command eq 'updateForm'}">
        <form name="updateForm" method="post" action="/board">
            <p >번호 :</p>
            <p>${vo.article_no}</p>
            <p>제목 :</p>
            <p><input type="text" name="title" value="${vo.title}"></p>
            <p>내용 :</p>
            <p><textarea name="content" >${vo.content}</textarea></p>
            <p><input type="submit" value="글 수정"></p>
            <p><input type="hidden" name="command" value="update"></p>
            <p><input type="hidden" name="article_no" value="${vo.article_no}"></p>
        </form>
    </c:when>
</c:choose>

</body>
</html>
