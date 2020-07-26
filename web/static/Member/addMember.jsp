<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import=" java.util.*,Member.*"%>
<%--  <jsp:useBean id="dao" class="Member.MemberDAO"></jsp:useBean> --%>
   <%--  <%
    boolean check1 = false;
    check1 = (Boolean)request.getAttribute("idDup");%>
    <%
    boolean check2 = false;
    check2 = (Boolean)request.getAttribute("pwdDup");%> --%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>

<!-- <script >
	function idcheck(){
		var member_id = document.addMemberForm.member_id.value;
		if (member_id.length==0 || member_id==null) {
			alert("체크 할 ID를 입력하세요");
			return false;
		}else{
		var url="addMemberIdcheck.jsp?id=" + member_id;
			/* alter("중복된 아이디입니다. "); */
		window.open(url, "get", "height =180, width=300");
		}
	</script> -->
</head>
<body>
	<form name="addMemberForm" method="post" action="/member">
		<table>
			<tr>
			<tr>
				<td>아이디:</td>
				<td><input type="text" name="member_id"></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>암호:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>확인:</td>
				<td><input type="password" name="confirm"></td>				
			</tr>
		</table>
		<input type="submit" value="가입">
		<input type="hidden" name="command" value="addMember.do">
	</form>
</body>
</html>

