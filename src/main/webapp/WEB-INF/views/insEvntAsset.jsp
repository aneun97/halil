<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.test.vo.InsEvntAssetVo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지출입력</title>
</head>
<body>
	<form method="post" action="isrt">
		<p><label>발생일 <input type="text" name="WK_DT" id="WK_DT"></label></p>
		<p>
			<label>이벤트 
			<select name="EVNT" id="EVNT" onchange="evnt_onchange()">
			<option value=''>-</option>
			<c:forEach var="item" items="${EVNT}" varStatus="idx">
			<option value=${item.EVNT} payTycd=${item.PAY_TYCD} rcvTycd=${item.RCV_TYCD}>${item.KR_NM}</option>
			</c:forEach>
			</select>
			</label>
		</p>
		<p>
			<label id="labPAY_ASSET">출금처 
			<select name="PAY_ASSET" id="PAY_ASSET" class="PAY_ASSET">
			<option value=''>-</option>
			<c:forEach var="item" items="${ASSET}" varStatus="idx">
			<option value=${item.ASSET}>${item.KR_NM}</option>
			</c:forEach>
			</select>
			</label>
		</p>
		<p>
			<label id="labRCV_ASSET">입금처 
			<select name="RCV_ASSET" id="RCV_ASSET" class="RCV_ASSET">
			<option value=''>-</option>
			<c:forEach var="item" items="${ASSET}" varStatus="idx">
			<option value=${item.ASSET}>${item.KR_NM}</option>
			</c:forEach>
			</select>
			</label>
		</p>
		<p><label>액수 <input type="text" name="AMT"></label></p>
		<p><label>이벤트타입 <input type="text" name="EVNT_TYCD"></label></p>
		<p><label>거래처 <input type="text" name="FIRM"></label></p>
		<p><label>설명 <input type="text" name="DTL"></label></p>
		<p><input type="submit" value="저장"></p>
	</form>
</body>
<script type="text/javascript">
function fnSetData()
{
};

function fnSetToday()
{
 let sToday = new Date();
 var sYear = sToday.getFullYear().toString();
 var sMonth = gfnLpad((sToday.getMonth()+1).toString(), 2, '0');
 var sDate = gfnLpad(sToday.getDate().toString(), 2, '0');
 document.getElementById("WK_DT").value = sYear+sMonth+sDate;
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


function evnt_onchange() {

	var sEvnt = document.getElementById("EVNT"); 
	var sEvntValue = sEvnt.options[sEvnt.selectedIndex].value; 
	var sEvntPayTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('payTycd');
	var sEvntRcvTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('rcvTycd');
	
	if(sEvntPayTycd == "N") {
		document.getElementById("PAY_ASSET").style.display = "none";
		document.getElementById("labPAY_ASSET").style.display = "none";
	}
	else {
		document.getElementById("PAY_ASSET").style.display = "block";
		document.getElementById("labPAY_ASSET").style.display = "block";
	}
	
	if(sEvntRcvTycd == "N") {
		document.getElementById("RCV_ASSET").style.display = "none";
		document.getElementById("labRCV_ASSET").style.display = "none";
	}
	else {
		document.getElementById("RCV_ASSET").style.display = "block";
		document.getElementById("labRCV_ASSET").style.display = "block";
	}
}


fnSetData();
fnSetToday();
</script>
</html>