<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	
	<p>
		<a href = "/">홈</a> | 
		<a href = "/history">위치 히스토리 목록</a> |
		<a href = "/load-wifi">Open API 와이파이 정보 가져오기</a> |
		<a href = "/show-bookmarkList">북마크 보기</a> |
		<a href = "/manage-bookmarkGroup">북마크 그룹 관리</a>
	</p>
	
	<table>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">X좌표</th>
			<th scope="col">Y좌표</th>
			<th scope="col">조회일자</th>
			<th scope="col">비고</th>
		</tr>
		<c:forEach var="history" items="${historyList}">
		<tr>
			<td>${history['ID']}</td>
			<td>${history['X']}</td>
			<td>${history['Y']}</td>
			<td>${history['SEARCH_DATE']}</td>
			<td style="text-align:center">
				<form action="/delete-history-submit" method="get">
				<input type="hidden" name="id" value="${history['ID']}">
				<input type="submit" value="삭제" style="font-size:10px;margin-top:3px;margin-bottom:3px">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>