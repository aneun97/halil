<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp테스트</title>
</head>
<body>
	<h1 style="color: white">혬니 바부! ${name}</h1>
	<div>JSP List Test</div>
	<c:forEach var="item" items="${list}" varStatus="idx">
	<p>${idx.index}, ${item.name}</p>
	</c:forEach>
</body>
</html>