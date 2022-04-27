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

<c:if test="${mem_id!='admin'}">
	<script>
		history.back();
	</script>
</c:if>

<c:if test="${mem_id=='admin'}">

<c:if test="${delete==1}">
	<script>
		alert("회원 삭제가 완료되었습니다.");
		location.href="AdminPage.do";
	</script>
</c:if>
<c:if test="${delete==0}">
	<script>
	  alert("비밀번호가 일치하지 않습니다.");
	  history.back();
	</script>
</c:if>

</c:if>