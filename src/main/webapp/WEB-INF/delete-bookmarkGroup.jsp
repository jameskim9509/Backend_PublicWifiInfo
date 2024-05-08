<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<style> 
th{width:20%; height:30px;} td{height:30px;}
</style>
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 삭제</h1>
	
	<p>
		<a href = "/">홈</a> | 
		<a href = "/history">위치 히스토리 목록</a> |
		<a href = "/load-wifi">Open API 와이파이 정보 가져오기</a> |
		<a href = "/show-bookmarkList">북마크 보기</a> |
		<a href = "/manage-bookmarkGroup">북마크 그룹 관리</a>
	</p>
	
	<p>북마크 그룹을 삭제하시겠습니까?</p>
	
	<form action="/delete-bookmarkGroup-submit" method="get">
		<input type="hidden" name="id" value="${id}">
		<table>
		<tr>
			<th scope="row">북마크 이름</th>
			<td><input type="text" name="name" value="${name}" readonly></td>
		</tr>
		<tr>
			<th scope="row">순서</th>
			<td><input type="text" name="num" value="${num}" readonly></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center">
			<a href="/manage-bookmarkGroup">돌아가기</a>|
			<input type="submit" value="삭제" style="font-size:12px">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>