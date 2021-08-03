
const date = new Date();

const viewYear = date.getFullYear();
const viewMonth = date.getMonth();
var resParse;



const prevLast = new Date(viewYear, viewMonth, 0);
const thisLast = new Date(viewYear, viewMonth + 1, 0);

const PLDate = prevLast.getDate();
const PLDay = prevLast.getDay();

const TLDate = thisLast.getDate();
const TLDay = thisLast.getDay();

const prevDates = [];
const thisDates = [...Array(TLDate + 1).keys()].slice(1);
const nextDates = [];




home_init();





function home_init(){
	fnSetMonthTitle();
	fnSetMonthDate();
	
	
	
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "lstCash", true);

	var startDate;	
	var endDate;
	
	if(prevDates.length > 0){
		startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	}
	else{
		startDate = viewYear.toString().concat(gfnLpad((viewMonth+1).toString(),2,"0"),gfnLpad(thisDates[0].toString(),2,"0"));
	}
	
	if(nextDates.length > 0){
		endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));
	}
	else{
		endDate = viewYear.toString().concat(gfnLpad((viewMonth+1).toString(),2,"0"),gfnLpad(thisDates[thisDates.length-1].toString(),2,"0"));
	}

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("startDate="+startDate+"&endDate="+endDate);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		resParse = JSON.parse(resText);
	fnSetDate();
	  }
	}

}










function fnSetMonthTitle(){
	let cMonth = document.getElementById('staMonthTitle');
	cMonth.innerText = viewYear+"년 "+(viewMonth+1)+"월";
}




function fnSetMonthDate(){
	

	if (PLDay !== 6) {
	  for (let i = 0; i < PLDay + 1; i++) {
	    prevDates.unshift(PLDate - i);
	  }
	}
	
	for (let i = 1; i < 7 - TLDay; i++) {
	  nextDates.push(i);
	}
}





function fnSetDate(){
	
	
	const dates = prevDates.concat(thisDates, nextDates);
	dates.forEach((date, i) => {
	  dates[i] = `<div class="date">
					<div class="dateNum" onclick="test()">${date}</div>
					<div class="cash">${fnSetComma(resParse[i].debit)}</div>
					<div class="evnt"></div>
				</div>`;
	})
	
	document.querySelector('.dates').innerHTML = dates.join('');
}




function test(){
	console.log("확인");
}




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