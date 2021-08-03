/*---------------------------
- 자산 입출금 상세 팝업
---------------------------*/

var resParse1;
var resParse2;
var resParse3;
var resParse4;

var evntNo = document.getElementById("EVNT_NO").value;

insEvntAsset_init();


/*
 * 화면 오픈 함수
 */
function insEvntAsset_init(){
	
	if(evntNo == null || evntNo == undefined || evntNo == "")
	{		
		fnSetToday();
		fnSetCond1();
		fnSetCond2();
		fnSetCond3();
	}
	else{
		fnSetCond4();
	}
}





function fnSetToday()
{
 let sToday = new Date();
 var sYear = sToday.getFullYear().toString();
 var sMonth = gfnLpad((sToday.getMonth()+1).toString(), 2, '0');
 var sDate = gfnLpad(sToday.getDate().toString(), 2, '0');
 document.getElementById("WK_DT").value = sYear+sMonth+sDate;
};


function fnSetCond1(){
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstEvnt", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		resParse1 = JSON.parse(resText);
		fnSetEvnt();
	  }
	}
}

function fnSetCond2(){
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstAsset", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		resParse2 = JSON.parse(resText);
		fnSetAsset();
	  }
	}
}

function fnSetCond3(){
	
	var wkDt = document.getElementById('WK_DT').value;
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstEvntTycd", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(		
	     "WK_DT	=" +wkDt
	);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		resParse3 = JSON.parse(resText);
		fnSetEvntHcls();
	  }
	}
}

function fnSetCond4(){
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "viwDtl", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("EVNT_NO="+evntNo);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		resParse4 = JSON.parse(resText);
		fnSetDtl();
	  }
	}
}

function fnSetEvnt(){
	
	var targetSel = document.getElementById('EVNT')

	resParse1.forEach(function(e) {
		var opt = document.createElement('option')
		opt.setAttribute('value', e.evnt)
		opt.setAttribute('payTycd', e.pay_TYCD)
		opt.setAttribute('rcvTycd', e.rcv_TYCD)
		opt.innerText = e.kr_NM
		targetSel.appendChild(opt)
	})
}


function fnSetAsset(){
	
	var targetSel1 = document.getElementById('PAY_ASSET');
	var targetSel2 = document.getElementById('RCV_ASSET');

	resParse2.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.asset);
		opt.innerText = e.kr_NM;
		targetSel1.appendChild(opt);
	})

	resParse2.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.asset);
		opt.innerText = e.kr_NM;
		targetSel2.appendChild(opt);
	})
}

function fnSetEvntHcls(){
	
	var targetSel = document.getElementById('EVNT_TYCD')

	resParse3.forEach(function(e) {
		var opt = document.createElement('option')
		opt.setAttribute('value', e.evnt_HCLS)
		opt.innerText = e.kr_NM
		targetSel.appendChild(opt)
	})
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


function evnt_onchange() {

	var sEvnt = document.getElementById("EVNT"); 
	var sEvntValue = sEvnt.options[sEvnt.selectedIndex].value; 
	var sEvntPayTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('payTycd');
	var sEvntRcvTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('rcvTycd');
	
	if(sEvntPayTycd == "N") {
		document.getElementById("labPAY_ASSET").style.display = "none";
		document.getElementById("PAY_ASSET").style.display = "none";
		document.getElementById("PAY_ASSET").selectedIndex = 0;
	}
	else {
		document.getElementById("PAY_ASSET").style.display = "block";
		document.getElementById("labPAY_ASSET").style.display = "block";
	}
	
	if(sEvntRcvTycd == "N") {
		document.getElementById("labRCV_ASSET").style.display = "none";
		document.getElementById("RCV_ASSET").style.display = "none";
		document.getElementById("RCV_ASSET").selectedIndex = 0;
	}
	else {
		document.getElementById("RCV_ASSET").style.display = "block";
		document.getElementById("labRCV_ASSET").style.display = "block";
	}
}


function fnSetDtl(){
	
	var targetSel = document.getElementById('EVNT')

	resParse1.forEach(function(e) {
		var opt = document.createElement('option')
		opt.setAttribute('value', e.evnt)
		opt.setAttribute('payTycd', e.pay_TYCD)
		opt.setAttribute('rcvTycd', e.rcv_TYCD)
		opt.innerText = e.kr_NM
		targetSel.appendChild(opt)
	})
}


function test(){
	
	var wkDt = document.getElementById('WK_DT').value;
	var sEvnt = document.getElementById('EVNT').value;
	var sPayAsset = document.getElementById('PAY_ASSET').value;
	var sRcvAsset = document.getElementById('RCV_ASSET').value;
	var nAmt = document.getElementById('AMT').value;
	var sEvntTycd = document.getElementById('EVNT_TYCD').value;
	var sFirm = document.getElementById('FIRM').value;
	var sDtl = document.getElementById('DTL').value;
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "isrt", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("WK_DT="+wkDt
			+"&EVNT="+sEvnt
			+"&PAY_ASSET="+sPayAsset
			+"&RCV_ASSET="+sRcvAsset
			+"&AMT="+nAmt
			+"&EVNT_TYCD="+sEvntTycd
			+"&FIRM="+sFirm
			+"&DTL="+sDtl
			);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		alert("입력성공");
		fnSetInit();
	  }
	  else{
		alert("입력실패");
	  }
	}
}

function fnSetInit(){
	
	fnSetToday();
	document.getElementById('EVNT').value = '';
	evnt_onchange();
	document.getElementById('PAY_ASSET').value = '';
	document.getElementById('RCV_ASSET').value = '';
	document.getElementById('AMT').value = null;
	document.getElementById('EVNT_TYCD').value = '';
	document.getElementById('FIRM').value = null;
	document.getElementById('DTL').value = null;
}