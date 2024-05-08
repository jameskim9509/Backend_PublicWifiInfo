<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<c:choose>
	
	<c:when test="${total_cnt eq -1 && cur_total eq -1}">
	<h1 style="text-align:center;'">WIFI 정보를 다시 불러와주세요.</h1>
	</c:when>
	
	<c:when test="${total_cnt ne -1 && cur_total ne -1}">
	<h1 style="text-align:center;'">${total_cnt}개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	</c:when>
	
	</c:choose>
	<a href = "/" style="display: block;text-align:center;">홈 으로 가기</a>
</body>
</html>