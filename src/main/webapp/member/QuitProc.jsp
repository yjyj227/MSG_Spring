<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />
<c:set var="mem_id" value="${sessionScope.idKey}" />
<c:set var="mem_passwd" value="${sessionScope.pwKey}" />
<c:set var="mem_nickname" value="${sessionScope.mem_nickname}" />
<c:set var="mem_point" value="${sessionScope.mem_point}" />
<c:set var="mem_grade" value="${sessionScope.mem_grade}" />

<c:if test="${delete==1}">
	<%session.invalidate();%>
	<script>
		alert("회원탈퇴가 완료되었습니다.\n그동안 이용해주셔서 감사합니다.");
		location.href="Main.do";
	</script>
	</c:if>
	<c:if test="${delete==0}">
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.back();
	</script>
	</c:if>