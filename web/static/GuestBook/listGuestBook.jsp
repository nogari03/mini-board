<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,GuestBook.*"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>
   
<html>
<head>
   <meta  charset="UTF-8">
   <title>방명록 목록보기</title>
<style>
     .cls1 {
       font-size:20px;
       text-align:left;
     }
    
     .cls2 {
       font-size:20px;
       text-align:left;
     }
  </style>
  
</head>
<body>
 <p class="cls1">방명록 목록</p>
   <table align="left" border="1" >
      <tr align="left" bgcolor="skyblue">
         <td><b>아이디</b></td>
         <td><b>이름</b></td>
         <td><b>비밀번호</b></td>
         <td><b>내용</b></td>
   </tr>

<c:choose>
    <c:when test="${ membersList==null}" >
      <tr>
        <td colspan=5>
          <b>등록된 글이 없습니다.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${guestList != null }" >
      <c:forEach  var="mem" items="${guestList }" >
        <tr align="center">
          <td>${mem.id }</td>
          <td>${mem.name }</td>
          <td>${mem.pass}</td>     
          <td>${mem.pessage }</td>     
    
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
 <a href="${contextPath}/member/memberForm.do"><p class="cls2">목록보기</p></a>
</body>
</html>
