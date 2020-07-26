<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action ="/guestBookPro/guestbook/addGuest.do">
		이름: <input type = "text" name = "guest_name" >	<br>
		암호: <input type = "text" name = "password">	<br>
		메시지 : <textarea name = message rows = "5" cols = "40" ></textarea> <br>
				<input type = "submit" value = "메시지 남기기"><br>
				<hr>
	</form>
		<div id = "listGuestForm" >
			<form method = "post" name = "listForm" action = "">
			<input type = "hidden" name = "pro">
			
			<hr>
			
			<div id = "comment">
				<label>메시지번호</label><br>
				메시지 <br>
				<a href = "#">삭제하기</a>&nbsp;
				<a href = "#">수정하기</a><br>
				
				</div>			

<!-- 		<h1 style = "text-align:center">회원가입창</h1>
		<table>
			<tr>
			<td width = "200">
				<p align = "right">아이디
			</td>
			
			<td width = "400"><input type = "text" name = "id"></td>
			</tr>
			<tr>
			<td width = "200">
				<p align = "right">비밀번호
			</td>
			
			<td width = "400"><input type = "password" name = "pwd"></td>
			</tr>
			<tr>
			<td width = "200">
				<p align = "right">이름
			</td>
			
			<td width = "400"><input type = "text" name = "name"></td>
			</tr>
			<tr>
			<td width = "200">
				<p align = "right">이메일
			</td>
			
			<td width = "400"><input type = "text" name = "email"></td>
			</tr>
			<tr>
				<td width = "200">
				<p>&nbsp;</p>
				</td>
				<td width = "400">
					<input type = "submit"	value = "가입하기">
					<input type = "reset"	value = "다시입력">
				</td>
			</tr>		
		</table>-->
	</form>
</body>
</html>