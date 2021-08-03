/*---------------------------
- 자산 입출금 메인
---------------------------*/

var dstEvntLst;
var dstAsset;
var dstAssetKind;
var dstEvntTycd;
var IOmode;

evntAssetSel_init();




/*
 * 화면 오픈 함수
 */
function evntAssetSel_init(){
	IOmode = 'I';
	fnSetToday();
	set_dstAsset();
	set_dstAssetKind();
	set_dstEvntTycd();
}

/*
 * 당일 날짜 설정
 */
function fnSetToday() {
 let sToday = new Date();
 var sYear = sToday.getFullYear().toString();
 var sMonth = gfnLpad((sToday.getMonth()+1).toString(), 2, '0');
 var sDate = gfnLpad(sToday.getDate().toString(), 2, '0');
 document.getElementById("FROM_DT").value = sYear+sMonth+"01";
 document.getElementById("TO_DT").value = sYear+sMonth+sDate;
};

/*
 * 신규 입력 팝업 오픈
 */
function btnIns_onClick() {
            var url = "ins";
            var name = "popup test";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
}

/*
 * 이벤트내역 팝업 오픈
 */
function item_onClick(evnt_no) {
            var url = "viw?EVNT_NO="+evnt_no;
            var name = "popup test";
            var option = "width = 500, height = 500, top = 300, left = 200, location = no"
            window.open(url, name, option);		
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



/*
 * 조회버튼 클릭
 */
function btnSel_onClick(){
	
	var fromDt = document.getElementById('FROM_DT').value;
	var toDt = document.getElementById('TO_DT').value;
	var chkPay = document.getElementById('chkPay').checked;
	var chkRcv = document.getElementById('chkRcv').checked;
	var chkEtc = document.getElementById('chkEtc').checked;
	var selAssetKind = document.getElementById('selAssetKind').value;
	var selAsset = document.getElementById('selAsset').value;
	var selEvntTycd = document.getElementById('selEvntTycd').value;

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "list", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(
		"FROM_DT="+fromDt
		+"&TO_DT="+toDt
		+"&PAY_YN="+chkPay
		+"&RCV_YN="+chkRcv
		+"&ETC_YN="+chkEtc
		+"&ASSET_KIND="+selAssetKind
		+"&ASSET="+selAsset
		+"&EVNT_TYCD="+selEvntTycd
		);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		dstEvntLst = JSON.parse(resText);
		set_grdEvntList();
	  }
	}
}

/*
 * 조회버튼 클릭
 */
function set_grdEvntList()
{
	
		var nTotalPayAmt = 0;
		var nTotalRcvAmt = 0;		
		
		dstEvntLst.forEach(function(e, i) 
		{ 
		
			if(e.evnt_NM == "지출")
			{
				nTotalPayAmt += e.amt;
			}
			else if(e.evnt_NM == "수입")
			{
				nTotalRcvAmt += e.amt;
			}

			dstEvntLst[i] = `<div class="item" style="border: 2px solid #eee" onclick="item_onClick('${e.evnt_NO}')">
								<div class="cell">${e.wk_DT}</div>
								<div class="cell">${e.evnt_NM}</div>
								<div class="cell">${e.evnt_TYCD}</div>
								<div class="cell">${e.pay_ASSET}</div>
								<div class="cell">${e.rcv_ASSET}</div>
								<div class="cell" style="text-align: right;">${fnSetComma(e.amt)} 원</div>
						   </div>`;
		}
		)
		document.querySelector('.grdEvntList').innerHTML = dstEvntLst.join('');
		
  		document.getElementById('txtPayAmt').innerText = fnSetComma(nTotalPayAmt);
  		document.getElementById('txtRcvAmt').innerText = fnSetComma(nTotalRcvAmt);
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
		var pcSel = document.getElementById('selAssetKind');	
		set_selAssetKind(pcSel);
	  }
	}
}

/*
 * 이벤트타입 데이터셋 세팅
 */
function set_dstEvntTycd(){
		
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstEvntTycd", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstEvntTycd = JSON.parse(resText);
		var pcSel = document.getElementById('selEvntTycd');	
		set_selEvntTycd(pcSel);
	  }
	}
}

/*
 * 자산 셀렉트박스 세팅
 */
function set_selAsset(pcSel, psAssetKind){
	
	pcSel.options.length = 0;
	
	var opt = document.createElement('option');
	opt.setAttribute('value', '');
	opt.innerText = '-';
	pcSel.appendChild(opt);
	
	dstAsset.forEach(function(e) {
		if(psAssetKind == null || psAssetKind == "" || e.asset_KIND == psAssetKind){
			opt = document.createElement('option');
			opt.setAttribute('value', e.asset);
			opt.innerText = e.kr_NM;
			pcSel.appendChild(opt);
		}
	})
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

/*
 * 이벤트타입 셀렉트박스 세팅
 */
function set_selEvntTycd(pcSel){
	
	dstEvntTycd.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.evnt_HCLS);
		opt.innerText = e.kr_NM;
		pcSel.appendChild(opt);
	})
}

/*
 * 자산종류 셀렉트 변경이벤트
 */
function onchange_selAssetKind() {
	var pcSel = document.getElementById('selAsset');
	var psAssetKind = document.getElementById('selAssetKind').value;
	set_selAsset(pcSel, psAssetKind);
}


/*
 * 전체 체크박스 변경이벤트
 */
function onchange_chkAll(){
	if(document.getElementById("chkAll").checked == true){
		document.getElementById("chkPay").checked = true;
		document.getElementById("chkRcv").checked = true;
		document.getElementById("chkEtc").checked = true;
		
		document.getElementById("chkPay").disabled = true;
		document.getElementById("chkRcv").disabled = true;
		document.getElementById("chkEtc").disabled = true;
	}else{
		document.getElementById("chkPay").checked = false;
		document.getElementById("chkRcv").checked = false;
		document.getElementById("chkEtc").checked = false;
		
		document.getElementById("chkPay").disabled = false;
		document.getElementById("chkRcv").disabled = false;
		document.getElementById("chkEtc").disabled = false;
	}
}