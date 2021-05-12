<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp테스트</title>
<style type="text/css">
.container {
	display: grid;
}
.item {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr;
}
</style>
<script type="text/javascript">
function fnSetComma(sId) 
{
  var regexp = /\B(?=(\d{3})+(?!\d))/g;

  document.getElementById(sId).innerText = document.getElementById(sId).innerText.toString().replace(regexp, ',');
}
</script>
</head>
<body>
	<form method="post" action="list">
		<p>
			<label>조회기간</label>
			<input type="text" name="FROM_DT" id="FROM_DT" value=${FROM_DT}>
			<input type="text" name="TO_DT" id="TO_DT" value=${TO_DT}>
			<input type="submit" value="조회">
		</p>
	</form>
	<div class="container">
		<div class="item" style="border: 2px dashed maroon">
			<div class="cell">발생일</div>
			<div class="cell">이벤트</div>
			<div class="cell">분류</div>
			<div class="cell">금액</div>
		</div>
		<c:forEach var="ROW" items="${LIST}" varStatus="idx">
		<div class="item" style="border: 2px solid #eee">
			<div class="cell">${ROW.WK_DT}</div>
			<div class="cell">${ROW.EVNT_NM}</div>
			<div class="cell">${ROW.EVNT_TYCD}</div>
			<div class="cell" id="amt${idx.index}" style="text-align: right;">${ROW.AMT} 원</div>
			<script type="text/javascript">fnSetComma("amt${idx.index}")</script>
		</div>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
function fnSetToday()
{
 let sToday = new Date();
 var sYear = sToday.getFullYear().toString();
 var sMonth = gfnLpad((sToday.getMonth()+1).toString(), 2, '0');
 var sDate = gfnLpad(sToday.getDate().toString(), 2, '0');
 document.getElementById("FROM_DT").value = sYear+sMonth+"01";
 document.getElementById("TO_DT").value = sYear+sMonth+sDate;
};

/**
 * 좌측문자열채우기
 * @params
 *  - str : 원 문자열
 *  - padLen : 최대 채우고자 하는 길이
 *  - padStr : 채우고자하는 문자(char)
 */
function gfnLpad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
        return str;
    }
    str += ""; // 문자로
    padStr += ""; // 문자로
    while (str.length < padLen)
        str = padStr + str;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}


fnSetToday();
</script>
</html>