<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,GuestBook.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset="UTF-8">
<title>방명록 목록보기</title>
</head>
<body>
		
	<c:if test="${empty guestbooksList }">
			등록 된 글이 없습니다.<br>
			<a href="/MiniProject/guestbookForm.jsp">글쓰기</a>
	</c:if>
	<c:if test="${not empty guestbooksList}">
			
			<table style="margin: 0 auto;"border="1">
		<c:forEach var="GuestbookVO" items="${guestbooksList }"
			varStatus="GuestbookNum">
				<tr>
					<td>메세지 번호: ${GuestbookVO.message_id }<br> 
						손님 이름: ${GuestbookVO.guest_name }<br> 
						메세지: ${GuestbookVO.message }<br>
						<a href = "/MiniProject/delGuestbook.jsp">[삭제하기]</a> 
						<a href="/MiniProject/checkGuest.jsp">[수정하기]</a>
					 <c:if test="${not empty GuestbookVO.fileName}">
						<a href="/MiniProject/download?fileName=${GuestbookVO.fileName}">파일다운</a></c:if> 
					</td>
				</tr>
			</c:forEach>
			</table>
			<br>
			
				<jsp:include page="paging.jsp">
				<jsp:param  value="${paging.page}" name="page"/>
				<jsp:param  value="${paging.beginPage}" name="beginPage"/>
				<jsp:param  value="${paging.endPage}" name="endPage"/>
				<jsp:param value="${paging.prev}" name="prev"/>
				<jsp:param value="${paging.next}" name="next"/>
				</jsp:include>
	</c:if>
</body>
</html>
