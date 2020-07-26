<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
<script type="text/javascript">
	function pwdCheck() {
		var pwdCheck = document.editPwd;
		var password = pwdCheck.password.value;
		var newPassword = pwdCheck.newPassword.value;
		if (password.length == 0 || password == "") {
			alert("비밀번호는 필수입니다. 입력하세욧!!");
		} else if (newPassword.length == 0 || newPassword == "") {
			alert("새 비밀번호는 필수입니다. 입력하세욧!!");
		} else {
			editPwd.method = "post";
			editPwd.action = "member";
			editPwd.submit();
		}
	}
	</script>
</head>
<body>
	<form name="editPwd" action="member" method="post">
		현재 암호:<br>
		<input type="password" name="password"><br>
		새 암호:<br>
		<input type="password" name="newPassword"><br>
		<input type="button" value="변경" onclick="pwdCheck()">
		<input type="hidden" name="command" value="editPwd.do" />
	</form>
</body>
</html>