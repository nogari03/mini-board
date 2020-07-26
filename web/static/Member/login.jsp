<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="/Login" method="post">
	아이디:<br>
	<input type="text" name="member_id" ><br>
    <br>
	암호: <br>
	<input type="password" name="password" ><br>
	<br>
    <input type="submit" value="로그인">
    <input type="hidden" name="command" value="loginCheck.do">
	</form>
</body>
</html>