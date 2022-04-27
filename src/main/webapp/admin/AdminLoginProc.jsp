<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />
<c:set var="adminLoginCheck" value="${requestScope.adminLoginCheck}" />

<c:choose>
	<c:when test="${adminLoginCheck==true}">
		<c:set var="idKey" value="${requestScope.mem_id}" scope="session" />
		<c:set var="pwKey" value="${requestScope.mem_passwd}" scope="session" />
		<c:set var="mem_nickname" value="${requestScope.mem_nickname}" scope="session" />
		<meta http-equiv="Refresh" content="0;url=Main.do">
	</c:when>
	<c:otherwise>
		<script>
	    	alert("아이디 또는 비밀번호가 일치하지 않습니다.");
	    </script>
	    <meta http-equiv="Refresh" content="0;url=AdminLogin.do">
    </c:otherwise>
</c:choose>