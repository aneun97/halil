<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp테스트</title>
<script type="text/javascript">
function fnSetComma(sId) 
{
  var regexp = /\B(?=(\d{3})+(?!\d))/g;

  document.getElementById(sId).innerText = document.getElementById(sId).innerText.toString().replace(regexp, ',');
}
</script>
</head>
<body>
	<form method="post" action="lst">
		<p>
			<label>조회기간</label>
			<input type="text" name="FROM_DT" id="FROM_DT" value=${FROM_DT}>
			<input type="text" name="TO_DT" id="TO_DT" value=${TO_DT}>
			<input type="submit" value="조회">
		</p>
	</form>
	<div id="total">
	<h1 style="color: blue">
		<span>수입:  </span>
		<span id="RCV">${rstLst[0].RCV}</span>
		<script type="text/javascript">fnSetComma("RCV")</script>
		<span>원</span>
	</h1>
	<h1 style="color: red">
		<span>지출:  </span>
		<span id="PAY">${rstLst[0].PAY}</span>
		<script type="text/javascript">fnSetComma("PAY")</script>
		<span>원</span>
	</h1>
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