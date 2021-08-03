/*---------------------------
- 자산 관리 메인
---------------------------*/

var IOmode;

var dstAssetLst;

var dstAsset;
var dstAssetKind;

assetAmtSel_init();


function assetAmtSel_init(){

	fnSetToday();
	set_dstAssetKind();
}



/*
 * 조회버튼 클릭
 */
function onClick_btnSer(){
	
	var wkDt = document.getElementById('WK_DT').value;

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "list", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("WK_DT="+wkDt);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		dstAssetLst = JSON.parse(resText);
	fnSetAssetList();
	  }
	}
}

/*
 * 입력/수정 저장
 */
function onClick_btnSav(){
	
	var sAssetNm = document.getElementById('txtAssetNm').value;
	var sAssetKind = document.getElementById('selAssetKind').value;
	var sFirm = document.getElementById('txtFirm').value;
	var sFromDt = document.getElementById('txtFromDt').value;
	var sToDt = document.getElementById('txtToDt').value;

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "ins", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("KR_NM="+sAssetNm
			+"&ASSET_KIND="+sAssetKind
			+"&FIRM="+sFirm
			+"&FROM_DT="+sFromDt
			+"&TO_DT="+sToDt
			);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		alert("입력성공");
		onClick_btnCls();
	  }
	  else{
		alert("입력실패");
	  }
	}
}


/*
 * 신규 입력 팝업 오픈
 */
function onClick_btnIns(){
	IOmode = "I";
	
	document.getElementById('divMan').className = "backDiv";
	document.getElementById('divDtl').style.display = "block";
	
	var pcSel = document.getElementById('selAssetKind');	
	set_selAssetKind(pcSel);
	
}

/*
 * 상세 팝업 닫기
 */
function onClick_btnCls(){
	document.getElementById('divMan').classList.remove("backDiv");
	document.getElementById('divDtl').style.display = "none";
	set_divDtl_init();
	
	IOmode = "S";
}


/*
 * 상세 팝업 초기화
 */
function set_divDtl_init(){
	document.getElementById('selAssetKind').value = '';
	document.getElementById('txtFirm').value = null;
	document.getElementById('txtFromDt').value = null;
	document.getElementById('txtToDt').value = null;
}


/*
 * 자산 데이터셋 세팅
 */
function set_dstAsset(){
	
	var fromDt = document.getElementById('FROM_DT').value;
	var toDt = document.getElementById('TO_DT').value;
	
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstAsset", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(
	     "FROM_DT	=" +fromDt
		+"&TO_DT	=" +toDt
	);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstAsset = JSON.parse(resText);
		
		var pcSel = document.getElementById('selAsset');	
		
		set_selAsset(pcSel);
	  }
	}
}

/*
 * 자산종류 데이터셋 세팅
 */
function set_dstAssetKind(){
		
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstAssetKind", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstAssetKind = JSON.parse(resText);
	  }
	}
}

/*
 * 자산종류 셀렉트박스 세팅
 */
function set_selAssetKind(pcSel){
	
	dstAssetKind.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.asset_KIND);
		opt.innerText = e.kr_NM;
		pcSel.appendChild(opt);
	})
}

function fnSetAssetList(){
	
	
  document.getElementById('debit').innerText = fnSetComma(dstAssetLst[0].debit);
  document.getElementById('credit').innerText = fnSetComma(dstAssetLst[0].credit);
  document.getElementById('netDebit').innerText = fnSetComma(dstAssetLst[0].net_DEBIT);


const dates = dstAssetLst;

		dates.forEach(function(e, i) { 
			
		dates[i] = `<div class="item" style="border: 2px solid #eee">
					<div class="cell">${e.asset_KIND_NM}</div>
					<div class="cell">${e.asset_NM}</div>
					<div class="cell">${e.firm}</div>
					<div class="cell" style="text-align: right;">${fnSetComma(e.amt)} 원</div>
				</div>`;
			})

		document.querySelector('.container2').innerHTML = dates.join('');
		
}





function fnSetComma(nParam) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;	
	return nParam.toString().replace(regexp, ',');
}



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

