<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />

<c:set var="idKey" value="${requestScope.idKey}" scope="session" />
<c:set var="pwKey" value="${requestScope.pwKey}" scope="session" />
<c:set var="mem_nickname" value="${requestScope.mem_nickname}" scope="session" />
<c:set var="mem_point" value="${requestScope.mem_point}" scope="session" />
<c:set var="mem_grade" value="${requestScope.mem_grade}" scope="session" />
<meta http-equiv="Refresh" content="0;url=Main.do">
