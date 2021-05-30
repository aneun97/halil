
var resParse;

evntAssetSel_init();





function evntAssetSel_init(){
	fnSetToday();
}

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

function fnSetComma(nParam) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;	
	return nParam.toString().replace(regexp, ',');
}



function test(){
	
	var fromDt = document.getElementById('FROM_DT').value;
	var toDt = document.getElementById('TO_DT').value;

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "list", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("FROM_DT="+fromDt+"&TO_DT="+toDt);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		resParse = JSON.parse(resText);
	fnSetEvntList();
	  }
	}
}

function fnSetEvntList(){
	
		resParse.forEach(function(e, i) { 
			
		resParse[i] = `<div class="item" style="border: 2px solid #eee">
					<div class="cell">${e.wk_DT}</div>
					<div class="cell">${e.evnt_NM}</div>
					<div class="cell">${e.evnt_TYCD}</div>
					<div class="cell" style="text-align: right;">${fnSetComma(e.amt)} 원</div>
				</div>`;
			})

		document.querySelector('.container2').innerHTML = resParse.join('');
}