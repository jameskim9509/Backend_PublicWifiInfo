<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<style> 
th{width:30%;height:30px} td{width:70%;}
</style>
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	
	<p>
		<a href = "/">홈</a> | 
		<a href = "/history">위치 히스토리 목록</a> |
		<a href = "/load-wifi">Open API 와이파이 정보 가져오기</a> |
		<a href = "/show-bookmarkList">북마크 보기</a> |
		<a href = "/manage-bookmarkGroup">북마크 그룹 관리</a>
	</p>
	
	<form action="/add-bookmark-submit" onsubmit="return beforeSubmit()" method="get">
		<input type="hidden" name="mgrNo" value="${wifiInfo['X_SWIFI_MGR_NO']}">
		
		<select name="id" id="bookmarkGroupName">
			<option value="" selected disabled>북마크 그룹 이름 선택</option>
			<c:forEach var="bookmarkGroup" items="${bookmarkGroupList}">
			<option value="${bookmarkGroup['ID']}">${bookmarkGroup['NAME']}</option>
			</c:forEach>
		</select>
		<input type="submit" value="북마크 추가하기" style="font-size:12px">
	</form>
	
	<script>
	    function beforeSubmit(){
	        var select = document.getElementById('bookmarkGroupName');
	        var option = select.options[select.selectedIndex];
	        if(option.value==""){
	            alert("북마크 그룹을 선택해 주세요.");
	            return false;
	        }
	        else{
	            return true;
	        }
	    }
	</script>
	
	<table>
		<tr>
			<th scope="row">거리(Km)</th>
			<td>${wifiInfo['DISTANCE']}</td>
		</tr>
		<tr>
			<th scope="row">관리번호</th>
			<td>${wifiInfo['X_SWIFI_MGR_NO']}</td>
		</tr>
		<tr>
			<th scope="row">자치구</th>
			<td>${wifiInfo['X_SWIFI_WRDOFC']}</td>
		</tr>
		<tr>
			<th scope="row">와이파이명</th>
			<td><a href="/detail?mgrNo=${wifiInfo['X_SWIFI_MGR_NO']}">${wifiInfo['X_SWIFI_MAIN_NM']}</a></td>
		</tr>
		<tr>
			<th scope="row">도로명주소</th>
			<td>${wifiInfo['X_SWIFI_ADRES1']}</td>
		</tr>
		<tr>
			<th scope="row">상세주소</th>
			<td>${wifiInfo['X_SWIFI_ADRES2']}</td>
		</tr>
		<tr>
			<th scope="col">설치위치(층)</th>
			<td>${wifiInfo['X_SWIFI_INSTL_FLOOR']}</td>
		</tr>
		<tr>
			<th scope="col">설치유형</th>
			<td>${wifiInfo['X_SWIFI_INSTL_TY']}</td>
		</tr>
		<tr>
			<th scope="col">설치기관</th>
			<td>${wifiInfo['X_SWIFI_INSTL_MBY']}</td>
		</tr>
		<tr>
			<th scope="col">서비스구분</th>
			<td>${wifiInfo['X_SWIFI_SVC_SE']}</td>
		</tr>
		<tr>
			<th scope="col">망종류</th>
			<td>${wifiInfo['X_SWIFI_CMCWR']}</td>
		</tr>
		<tr>
			<th scope="col">설치년도</th>
			<td>${wifiInfo['X_SWIFI_CNSTC_YEAR']}</td>
		</tr>
		<tr>
			<th scope="col">실내외구분</th>
			<td>${wifiInfo['X_SWIFI_INOUT_DOOR']}</td>
		</tr>
		<tr>
			<th scope="col">WIFI접속환경</th>
			<td>${wifiInfo['X_SWIFI_REMARS3']}</td>
		</tr>
		<tr>
			<th scope="col">X좌표</th>
			<td>${wifiInfo['LAT']}</td>
		</tr>
		<tr>
			<th scope="col">Y좌표</th>
			<td>${wifiInfo['LNT']}</td>
		</tr>
		<tr>
			<th scope="col">작업일자</th>
			<td>${wifiInfo['WORK_DTTM']}</td>
		</tr>
	</table>
</body>
</html>