<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<style> 
th{height:30px;} td{height:30px;}
</style>
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 그룹</h1>
	<p>
		<a href = "/">홈</a> | 
		<a href = "/history">위치 히스토리 목록</a> |
		<a href = "/load-wifi">Open API 와이파이 정보 가져오기</a> |
		<a href = "/show-bookmarkList">북마크 보기</a> |
		<a href = "/manage-bookmarkGroup">북마크 그룹 관리</a>
	</p>
	<form action="/add-bookmarkGroup" >
	<input type="submit" value="북마크 그룹 이름 추가" style="font-size:12px">
	</form>
	<table>
	<tr>
		<th scope="col">ID</th>
		<th scope="col">북마크 이름</th>
		<th scope="col">순서</th>
		<th scope="col">등록일자</th>
		<th scope="col">수정일자</th>
		<th scope="col">비고</th>
	</tr>
	<c:forEach var="bookmarkGroup" items="${bookmarkGroupList}">
	<tr>
		<td>${bookmarkGroup['ID']}</td>
		<td>${bookmarkGroup['NAME']}</td>
		<td>${bookmarkGroup['NUM']}</td>
		<td>${bookmarkGroup['GRP_REGISTER_DATE']}</td>
		<td>${bookmarkGroup['GRP_MODIFY_DATE']}</td>
		<td style="text-align:center">
		<a href="/edit-bookmarkGroup?id=${bookmarkGroup['ID']}">수정</a> 
		<a href="/delete-bookmarkGroup?id=${bookmarkGroup['ID']}">삭제</a>
		</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>