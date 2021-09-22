/*---------------------------
- 자산 입출금 메인
---------------------------*/

var dstFixEvntAssetLst;

var dstAsset;
var dstEvnt;
var dstEvntTycd;
var IOmode;

fixEvntAssetMgr_init();




/*
 * 화면 오픈 함수
 */
function fixEvntAssetMgr_init(){
	IOmode = 'S';
	set_dstAsset();
	set_dstEvnt();
	set_dstEvntTycd();
}




/*
 * 조회버튼 클릭
 */
function onClick_btnSer(){

	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "lst", true);

	//var startDate = viewYear.toString().concat(gfnLpad(viewMonth.toString(),2,"0"),gfnLpad(prevDates[0].toString(),2,"0"));
	//var endDate = viewYear.toString().concat(gfnLpad((viewMonth+2).toString(),2,"0"),gfnLpad(nextDates[nextDates.length-1].toString(),2,"0"));

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(
		);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		dstFixEvntAssetLst = JSON.parse(resText);
		set_grdFixEvntAssetLst();
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
			upd_fixEvntAsset("ins");
			break;
		case "U" :
			upd_fixEvntAsset("upd"); 	
			break;
		case "D" :
			upd_fixEvntAsset("del"); 	
			break;
		default	:
			break;
	}
}


/*
 * 상세 팝업 오픈
 */
function onClick_grdRow(sEvntNo){
/*	
	console.log(sEvntNo);
	IOmode = "S";
	
	document.getElementById('divMan').className = "backDiv";
	document.getElementById('divDtl').style.display = "block";
*/
	
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
	document.getElementById('dtlSelEvnt').value = '';
	document.getElementById('dtlSelPayAsset').value = '';
	document.getElementById('dtlSelrcvAsset').value = '';
	document.getElementById('dtlTxtAmt').value = null;
	document.getElementById('dtlSelEvntTycd').value = '';
	document.getElementById('dtlTxtFirm').value = null;
	document.getElementById('dtlTxtDtl').value = null;
	document.getElementById('dtlTxtEvntPer').value = null;
	document.getElementById('dtlTxtEvntDt').value = null;
	document.getElementById('dtlTxtFromDt').value = null;
	document.getElementById('dtlTxtToDt').value = null;
}

/*
 * 조회버튼 클릭
 */
function set_grdFixEvntAssetLst()
{
			
		dstFixEvntAssetLst.forEach(function(e, i) 
		{ 


				dstFixEvntAssetLst[i] = `<div class="item" style="border: 2px solid #eee;" onclick="onClick_grdRow('${e.fix_EVNT_NO}')">
									<div class="cell">${e.from_DT}</div>
									<div class="cell">${e.to_DT}</div>
									<div class="cell">${e.evnt_NM}</div>
									<div class="cell">${e.evnt_TYCD}</div>
									<div class="cell">${e.pay_ASSET}</div>
									<div class="cell">${e.rcv_ASSET}</div>
									<div class="cell" style="text-align: right;">${gfnSetComma(e.amt)} 원</div>
							   </div>`;
		}
		)
		document.querySelector('.grdEvntList').innerHTML = dstFixEvntAssetLst.join('');
		
}







/*
 * 자산 데이터셋 세팅
 */
function set_dstAsset(){
		
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/syCommonsel/lstAsset", true);
	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(
	     "WK_DT=" +gfnGetToday()
	);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		
		dstAsset = JSON.parse(resText);
		
		pcSel = document.getElementById('dtlSelPayAsset');		
		set_selAsset(pcSel);
		
		pcSel = document.getElementById('dtlSelrcvAsset');		
		set_selAsset(pcSel);
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
		
		var pcSel = document.getElementById('dtlSelEvntTycd');	
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
 * 신규입력
 */
function upd_fixEvntAsset(sParam){
	
	var sEvnt = document.getElementById('dtlSelEvnt').value;
	var sPayAsset = document.getElementById('dtlSelPayAsset').value;
	var sRcvAsset = document.getElementById('dtlSelrcvAsset').value;
	var nAmt = document.getElementById('dtlTxtAmt').value;
	var sEvntTycd = document.getElementById('dtlSelEvntTycd').value;
	var sFirm = document.getElementById('dtlTxtFirm').value;
	var sDtl = document.getElementById('dtlTxtDtl').value;
	var sEvntPer = document.getElementById('dtlTxtEvntPer').value;
	var sEvntDt = document.getElementById('dtlTxtEvntDt').value;
	var sFromDt = document.getElementById('dtlTxtFromDt').value;
	var sToDt = document.getElementById('dtlTxtToDt').value;
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", sParam, true);

	
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("&EVNT="+sEvnt
			+"&PAY_ASSET="+sPayAsset
			+"&RCV_ASSET="+sRcvAsset
			+"&AMT="+nAmt
			+"&EVNT_TYCD="+sEvntTycd
			+"&FIRM="+sFirm
			+"&DTL="+sDtl
			+"&EVNT_PER="+sEvntPer
			+"&EVNT_DT="+sEvntDt
			+"&FROM_DT="+sFromDt
			+"&TO_DT="+sToDt
			);
	
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