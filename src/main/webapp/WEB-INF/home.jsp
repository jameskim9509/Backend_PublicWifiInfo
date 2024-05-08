<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
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
	
	<form action="/" method="get">
	<p>
	LAT : <input id="lat" type="text" name="lat" value="${lat}">
	,LNT : <input id="lnt" type="text" name="lnt" value="${lnt}">
	<button type="button" onclick="getLoc()">내 위치 가져오기</button>
	<input type="submit" value="근처 WIFI 정보 보기">
	</p>
	</form>
	
	<table>
	<tr>
		<th scope="col" style="width:3%;">거리<br/>(Km)</th>
		<th scope="col" style="width:6%;">관리번호</th>
		<th scope="col" style="width:3%;">자치구</th>
		<th scope="col" style="width:8%;">와이파이명</th>
		<th scope="col" style="width:17%;">도로명주소</th>
		<th scope="col" style="width:13%;">상세주소</th>
		<th scope="col" style="width:4%;">설치위치<br/>(층)</th>
		<th scope="col" style="width:6%;">설치유형</th>
		<th scope="col" style="width:4%;">설치기관</th>
		<th scope="col" style="width:4%;">서비스구분</th>
		<th scope="col" style="width:4%;">망종류</th>
		<th scope="col" style="width:3%;">설치년도</th>
		<th scope="col" style="width:3%;">실내외구분</th>
		<th scope="col" style="width:4%;">WIFI접속환경</th>
		<th scope="col" style="width:6%;">X좌표</th>
		<th scope="col" style="width:6%;">Y좌표</th>
		<th scope="col" style="width:6%;">작업일자</th>
	</tr>
	<c:choose>
	
	<c:when test="${fn:length(lat) gt 1 && fn:length(lnt) gt 1}">
	<c:forEach var="wifiInfo" items="${PublicWifiInfoList}">
	<tr>
		<td style="width:3%;">${wifiInfo['DISTANCE']}</td>
		<td style="width:6%;">${wifiInfo['X_SWIFI_MGR_NO']}</td>
		<td style="width:3%;">${wifiInfo['X_SWIFI_WRDOFC']}</td>
		<td style="width:8%;">
		<a href="/detail?mgrNo=${wifiInfo['X_SWIFI_MGR_NO']}" >${wifiInfo['X_SWIFI_MAIN_NM']}</a>
		</td>
		<td style="width:17%;">${wifiInfo['X_SWIFI_ADRES1']}</td>
		<td style="width:13%;">${wifiInfo['X_SWIFI_ADRES2']}</td>
		<td style="width:4%;">${wifiInfo['X_SWIFI_INSTL_FLOOR']}</td>
		<td style="width:6%;">${wifiInfo['X_SWIFI_INSTL_TY']}</td>
		<td style="width:4%;">${wifiInfo['X_SWIFI_INSTL_MBY']}</td>
		<td style="width:4%;">${wifiInfo['X_SWIFI_SVC_SE']}</td>
		<td style="width:4%;">${wifiInfo['X_SWIFI_CMCWR']}</td>
		<td style="width:3%;">${wifiInfo['X_SWIFI_CNSTC_YEAR']}</td>
		<td style="width:3%;">${wifiInfo['X_SWIFI_INOUT_DOOR']}</td>
		<td style="width:4%;">${wifiInfo['X_SWIFI_REMARS3']}</td>
		<td style="width:6%;">${wifiInfo['LAT']}</td>
		<td style="width:6%;">${wifiInfo['LNT']}</td>
		<td style="width:6%;">${wifiInfo['WORK_DTTM']}</td>
	</tr>
	</c:forEach>
	</c:when>
	
	<c:when test="${fn:length(lat) le 1 && fn:length(lnt) le 1}">
		<tr>
			<td colspan="17" style="text-align:center;height:50px;">위치 정보를 입력한 후에 조회해 주세요.</td>
		</tr>
	</c:when> 
	
	</c:choose>
	<tr>
	</table>
	
	<script>
	function getLoc()
	{
		function geoCallback(position)
		{
			document.getElementById('lat').value = position.coords.latitude;
			document.getElementById('lnt').value = position.coords.longitude;
		}
		
		function geoErrorCallback(position)
		{
			alert('위치 정보를 가져오는데 실패했습니다. (위치 권한 허용 또는 지원하는 웹 브라우저를 이용해주세요)');
		}
		
		navigator.geolocation.getCurrentPosition(geoCallback, geoErrorCallback);
	}
	</script>
</body>
</html>