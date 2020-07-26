<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴창</title>
<script type="text/javascript">
	function Check() {
		var Check = document.delMember;
		var member_id = Check.member_id.value;
		var password = Check.password.value;
		if (member_id.length == 0 || member_id == "") {
			alert("아이디 입력은 필수입니다. 입력하세욧!!");
		} else if (password.length == 0 || password == "") {
			alert("비밀번호는 입력은 필수입니다. 입력하세욧!!");
		} else {
			delMember.method = "post";
			delMember.action = "member";
			delMember.submit();
		}
	}
	</script>
</head>
<body>
	<form name="delMember" action="member" method="post">
	아이디<input type="text" name="member_id">
	비밀번호<input type="password" name="password">
	<input type="button" value="탈퇴" onclick="Check()">
		<input type="hidden" name="command" value="delMember.do" />
	</form>
</body>
</html>