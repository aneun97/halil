/*---------------------------
- 자산 입출금 메인
---------------------------*/

var dstEvntLst;
var dstEvntDtl;
var dstAsset;
var dstAssetKind;
var dstEvnt;
var dstEvntHcls;
var dstEvntLcls = new Array();
var IOmode;

evntAssetSel_init();




/*
 * 화면 오픈 함수
 */
function evntAssetSel_init(){
	IOmode = 'S';
	fnSetToday();
	set_dstAsset();
	set_dstAssetKind();
	set_dstEvnt();
	set_dstEvntHcls();
	set_dstEvntLcls();
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
 * 조회버튼 클릭
 */
function onClick_btnSer(){
	var fromDt = document.getElementById('FROM_DT').value;
	var toDt = document.getElementById('TO_DT').value;
	var chkPay = document.getElementById('chkPay').checked;
	var chkRcv = document.getElementById('chkRcv').checked;
	var chkEtc = document.getElementById('chkEtc').checked;
	var selAssetKind = document.getElementById('selAssetKind').value;
	var selAsset = document.getElementById('selAsset').value;
	var selEvntHcls = document.getElementById('selEvntHcls').value;

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "lst", true);

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
		+"&EVNT_HCLS="+selEvntHcls
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
 * 신규 입력 팝업 오픈
 */
function onClick_btnIns(){
	IOmode = "I";
	
	document.getElementById('divMan').className = "backDiv";
	document.getElementById('divDtl').style.display = "block";
	
	set_divDtl_disabled(false);
	onchange_dtlSelEvnt();
	
	var pcSel = document.getElementById('dtlSelEvntLcls');	
	set_selEvntLcls(pcSel, "");
	//var pcSel = document.getElementById('selAssetKind');	
	//set_selAssetKind(pcSel);
	
}

/*
 * 수정버튼 클릭
 */
function onClick_btnUpd(){
	IOmode = "U";
	
	document.getElementById('dtlTxtAmt').disabled = false;
	document.getElementById('dtlSelPayAsset').disabled = false;
	document.getElementById('dtlSelRcvAsset').disabled = false;
	document.getElementById('dtlSelEvntHcls').disabled = false;
	document.getElementById('dtlSelEvntLcls').disabled = false;
	document.getElementById('dtlTxtFirm').disabled = false;
	document.getElementById('dtlTxtDtl').disabled = false;
	
	var evntHcls = document.getElementById('dtlSelEvntHcls').value;
	var evntLcls = document.getElementById('dtlSelEvntLcls').value;
	var pcSel = document.getElementById('dtlSelEvntLcls');	
	set_selEvntLcls(pcSel, evntHcls);
	document.getElementById('dtlSelEvntLcls').value = evntLcls;
	
}

/*
 * 삭제버튼 클릭
 */
function onClick_btnDel(){
	IOmode = "D";
	onClick_btnSav();
}

/*
 * 확인버튼 클릭
 */
function onClick_btnChk(){
	IOmode = "U";
	
	dstEvntDtl.chk_YN = "Y";
	onClick_btnSav();
}

/*
 * 상세 팝업 저장
 */
function onClick_btnSav(){
	switch(IOmode){
		case "S" : 	
			alert("IOmode::"+IOmode);
			break;
		case "I" :
			rec_evntAsset("ins");
			break;
		case "U" :
			rec_evntAsset("upd"); 	
			break;
		case "D" :
			rec_evntAsset("del"); 	
			break;
		default	:
			break;
	}
}


/*
 * 상세 팝업 오픈
 */
function onClick_grdRow(sEvntNo){

	IOmode = "S";
	
	document.getElementById('divMan').className = "backDiv";
	document.getElementById('divDtl').style.display = "block";
	// 읽기모드로 팝업창 실행
	

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "viw", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(
		"EVNT_NO="+sEvntNo
		);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		dstEvntDtl = JSON.parse(resText);
		set_divDtl_disabled(true);
		set_divDtlViw();
		onchange_dtlSelEvnt();
	  }
	}

	
}

/*
 * 상세 팝업 닫기
 */
function onClick_btnCls(){
	document.getElementById('divMan').classList.remove("backDiv");
	document.getElementById('divDtl').style.display = "none";
		
	var pcSel = document.getElementById('dtlSelEvntLcls');	
	set_selEvntLcls(pcSel);
	
	set_divDtl_init();
	
	IOmode = "S";
}


/*
 * 상세팝업 이벤트선택지 변경이벤트
 */
function onchange_dtlSelEvnt() {

	var sEvnt = document.getElementById("dtlSelEvnt"); 
	var sEvntPayTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('payTycd');
	var sEvntRcvTycd = sEvnt.options[sEvnt.selectedIndex].getAttribute('rcvTycd');
	
	if(sEvntPayTycd == "N") {
		document.getElementById("dtlLabPayAsset").style.display = "none";
		document.getElementById("dtlSelPayAsset").style.display = "none";
		document.getElementById("dtlSelPayAsset").selectedIndex = 0;
	}
	else {
		document.getElementById("dtlLabPayAsset").style.display = "block";
		document.getElementById("dtlSelPayAsset").style.display = "block";
	}
	
	if(sEvntRcvTycd == "N") {
		document.getElementById("dtlLabRcvAsset").style.display = "none";
		document.getElementById("dtlSelRcvAsset").style.display = "none";
		document.getElementById("dtlSelRcvAsset").selectedIndex = 0;
	}
	else {
		document.getElementById("dtlLabRcvAsset").style.display = "block";
		document.getElementById("dtlSelRcvAsset").style.display = "block";
	}
}

/*
 * 상세팝업 이벤트분류(상위)선택지 변경이벤트
 */
function onchange_dtlSelEvntHcls() {
	var evntHcls = document.getElementById('dtlSelEvntHcls').value;
	var pcSel = document.getElementById('dtlSelEvntLcls');	
	set_selEvntLcls(pcSel, evntHcls);
}

/*
 * 상세 팝업 초기화
 */
function set_divDtl_init(){
	document.getElementById('dtlTxtWkDt').value = null;
	document.getElementById('dtlSelEvnt').value = '';
	document.getElementById('dtlSelPayAsset').value = '';
	document.getElementById('dtlSelRcvAsset').value = '';
	document.getElementById('dtlTxtAmt').value = null;
	document.getElementById('dtlSelEvntHcls').value = '';
	document.getElementById('dtlSelEvntLcls').value = '';
	document.getElementById('dtlTxtFirm').value = null;
	document.getElementById('dtlTxtDtl').value = null;
}


/*
 * 상세 팝업 입력불가설정
 */
function set_divDtl_disabled(b){
	document.getElementById('dtlTxtWkDt').disabled = b;
	document.getElementById('dtlSelEvnt').disabled = b;
	document.getElementById('dtlSelPayAsset').disabled = b;
	document.getElementById('dtlSelRcvAsset').disabled = b;
	document.getElementById('dtlTxtAmt').disabled = b;
	document.getElementById('dtlSelEvntHcls').disabled = b;
	document.getElementById('dtlSelEvntLcls').disabled = b;
	document.getElementById('dtlTxtFirm').disabled = b;
	document.getElementById('dtlTxtDtl').disabled = b;
}
	
/*
 * 상세 팝업 세팅
 */
function set_divDtlViw(){
	
	document.getElementById('dtlTxtWkDt').value = dstEvntDtl.wk_DT;
	document.getElementById('dtlSelEvnt').value = dstEvntDtl.evnt;
	document.getElementById('dtlSelPayAsset').value = dstEvntDtl.pay_ASSET;
	document.getElementById('dtlSelRcvAsset').value = dstEvntDtl.rcv_ASSET;
	document.getElementById('dtlTxtAmt').value = dstEvntDtl.amt;
	document.getElementById('dtlSelEvntHcls').value = dstEvntDtl.evnt_HCLS
	document.getElementById('dtlSelEvntLcls').value = dstEvntDtl.evnt_LCLS
	document.getElementById('dtlTxtFirm').value = dstEvntDtl.firm;
	document.getElementById('dtlTxtDtl').value = dstEvntDtl.dtl;
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
		
			if(e.chk_YN != "D" && e.evnt_NM == "지출")
			{
				nTotalPayAmt += e.amt;
			}
			else if(e.chk_YN != "D" && e.evnt_NM == "수입")
			{
				nTotalRcvAmt += e.amt;
			}

			if(e.chk_YN == "N")
			{
				dstEvntLst[i] = `<div class="item" style="border: 2px solid #eee; background-color: yellow;" onclick="onClick_grdRow('${e.evnt_NO}')">
									<div class="cell">${e.wk_DT}</div>
									<div class="cell">${e.evnt_NM}</div>
									<div class="cell">${e.evnt_HCLS}</div>
									<div class="cell">${e.pay_ASSET}</div>
									<div class="cell">${e.rcv_ASSET}</div>
									<div class="cell" style="text-align: right;">${gfnSetComma(e.amt)} 원</div>
							   </div>`;
			}
			else if(e.chk_YN == "D")
			{
				dstEvntLst[i] = `<div class="item" style="border: 2px solid #eee; background-color: red;" onclick="onClick_grdRow('${e.evnt_NO}')">
									<div class="cell">${e.wk_DT}</div>
									<div class="cell">${e.evnt_NM}</div>
									<div class="cell">${e.evnt_HCLS}</div>
									<div class="cell">${e.pay_ASSET}</div>
									<div class="cell">${e.rcv_ASSET}</div>
									<div class="cell" style="text-align: right;">${gfnSetComma(e.amt)} 원</div>
							   </div>`;
			}
			else
			{
				dstEvntLst[i] = `<div class="item" style="border: 2px solid #eee;" onclick="onClick_grdRow('${e.evnt_NO}')">
									<div class="cell">${e.wk_DT}</div>
									<div class="cell">${e.evnt_NM}</div>
									<div class="cell">${e.evnt_HCLS}</div>
									<div class="cell">${e.pay_ASSET}</div>
									<div class="cell">${e.rcv_ASSET}</div>
									<div class="cell" style="text-align: right;">${gfnSetComma(e.amt)} 원</div>
							   </div>`;
			}
		}
		)
		document.querySelector('.grdEvntList').innerHTML = dstEvntLst.join('');
		
  		document.getElementById('txtPayAmt').innerText = gfnSetComma(nTotalPayAmt);
  		document.getElementById('txtRcvAmt').innerText = gfnSetComma(nTotalRcvAmt);
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
	     "FROM_DT=" +fromDt
		+"&TO_DT=" +toDt
	);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstAsset = JSON.parse(resText);
		
		var pcSel = document.getElementById('selAsset');		
		set_selAsset(pcSel);
		
		pcSel = document.getElementById('dtlSelPayAsset');		
		set_selAsset(pcSel);
		
		pcSel = document.getElementById('dtlSelRcvAsset');		
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
 * 이벤트 데이터셋 세팅
 */
function set_dstEvnt(){
		
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
		
		dstEvnt = JSON.parse(resText);
		var pcSel = document.getElementById('dtlSelEvnt');	
		set_selEvnt(pcSel);
	  }
	}
}

/*
 * 이벤트타입 데이터셋 세팅
 */
function set_dstEvntHcls(){
		
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstEvntHcls", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstEvntHcls = JSON.parse(resText);
		var pcSel = document.getElementById('selEvntHcls');	
		set_selEvntHcls(pcSel);
		
		var pcSel = document.getElementById('dtlSelEvntHcls');	
		set_selEvntHcls(pcSel);
	  }
	}
}

/*
 * 이벤트타입(하위) 데이터셋 세팅
 */
function set_dstEvntLcls(){
		
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstEvntLcls", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstEvntLcls = JSON.parse(resText);
		
		var pcSel = document.getElementById('dtlSelEvntLcls');	
		set_selEvntLcls(pcSel);
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
 * 이벤트 셀렉트박스 세팅
 */
function set_selEvnt(pcSel){
	
	dstEvnt.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.evnt)
		opt.setAttribute('payTycd', e.pay_TYCD)
		opt.setAttribute('rcvTycd', e.rcv_TYCD)
		opt.innerText = e.kr_NM
		pcSel.appendChild(opt);
	})
}

/*
 * 이벤트타입(상위) 셀렉트박스 세팅
 */
function set_selEvntHcls(pcSel){
	
	dstEvntHcls.forEach(function(e) {
		var opt = document.createElement('option');
		opt.setAttribute('value', e.evnt_HCLS);
		opt.innerText = e.kr_NM;
		pcSel.appendChild(opt);
	})
}

/*
 * 이벤트타입(하위) 셀렉트박스 세팅
 */
function set_selEvntLcls(pcSel, evntHcls){
	
	pcSel.options.length = 0;
	
	var opt = document.createElement('option');
	opt.setAttribute('value', '');
	opt.innerText = '-';
	pcSel.appendChild(opt);
	
	dstEvntLcls.forEach(function(e) {
		if(evntHcls == null){
			var opt = document.createElement('option');
			opt.setAttribute('value', e.evnt_LCLS);
			opt.innerText = e.kr_NM;
			pcSel.appendChild(opt);
		}
		else if(evntHcls == e.evnt_HCLS){
			var opt = document.createElement('option');
			opt.setAttribute('value', e.evnt_LCLS);
			opt.innerText = e.kr_NM;
			pcSel.appendChild(opt);
		}
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





/*
 * 자산변동 기록
 */
function rec_evntAsset(sParam){
	
	var sWkDt = document.getElementById('dtlTxtWkDt').value;
	var sEvnt = document.getElementById('dtlSelEvnt').value;
	var sPayAsset = document.getElementById('dtlSelPayAsset').value;
	var sRcvAsset = document.getElementById('dtlSelRcvAsset').value;
	var nAmt = document.getElementById('dtlTxtAmt').value;
	var sEvntHcls = document.getElementById('dtlSelEvntHcls').value;
	var sEvntLcls = document.getElementById('dtlSelEvntLcls').value;
	var sFirm = document.getElementById('dtlTxtFirm').value;
	var sDtl = document.getElementById('dtlTxtDtl').value;
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", sParam, true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	if(sParam == "ins"){
		xhr.send("WK_DT="+sWkDt
				+"&EVNT="+sEvnt
				+"&PAY_ASSET="+sPayAsset
				+"&RCV_ASSET="+sRcvAsset
				+"&AMT="+nAmt
				+"&EVNT_HCLS="+sEvntHcls
				+"&EVNT_LCLS="+sEvntLcls
				+"&FIRM="+sFirm
				+"&DTL="+sDtl
				);
	}
	else{
		xhr.send("EVNT_NO="+dstEvntDtl.evnt_NO
				+"&WK_DT="+sWkDt
				+"&EVNT="+sEvnt
				+"&PAY_ASSET="+sPayAsset
				+"&RCV_ASSET="+sRcvAsset
				+"&AMT="+nAmt
				+"&EVNT_HCLS="+sEvntHcls
				+"&EVNT_LCLS="+sEvntLcls
				+"&FIRM="+sFirm
				+"&DTL="+sDtl
				+"&CHK_YN="+dstEvntDtl.chk_YN
				);
	}
			
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		alert("입력성공");
		onClick_btnCls();
		onClick_btnSer();
	  }
	  else{
		alert("입력실패");
	  }
	}
}