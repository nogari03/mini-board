<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%--  <jsp:useBean id="dao" class="Member.MemberDAO"></jsp:useBean>
    <%<%-- int rst =0;  --%>
   <% String name = (String) request.getAttribute("name"); %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMemberOk</title>
</head>
<body>
<%-- <% 
if(rst==1){
%>
<img src="img/duke.png" width=290" height="166">
이미 존재하는 아이디에요ㅠㅠㅠㅠㅠ 
<% } else {%>
<img src="img/duke2.png" width=290" height="166">
사용 가능한 아이디! 회원가입 진행하세요ㅎㅎㅎ
<%} %> --%>
<h1> ${param.name} 님, 회원 가입에 성공했습니다.  <br>환영하지요오오오옹ㅇ!~ </h1>
<a href="/static/main.jsp"> 가랏! 초기화면으로!!! </a>
</body>
</html>