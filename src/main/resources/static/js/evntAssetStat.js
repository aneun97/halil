/*---------------------------
- 자산 관리 메인
---------------------------*/

var IOmode;

var dstStatLst;

var dstAsset;
var dstAssetKind;

assetAmtSel_init();


function assetAmtSel_init(){

}



/*
 * 조회버튼 클릭
 */
function onClick_btnSer(){
	

var date = new Date();
var viewYear = date.getFullYear();
var viewMonth = date.getMonth();
var befStDt = new Date(viewYear, viewMonth - 1, 1);
var befEdDt = new Date(viewYear, viewMonth, 0);
var thsStDt = new Date(viewYear, viewMonth, 1);
var thsEdDt = new Date(viewYear, viewMonth + 1, 0);
befStDt = fnGetDate(befStDt);
befEdDt = fnGetDate(befEdDt);
thsStDt = fnGetDate(thsStDt);
thsEdDt = fnGetDate(thsEdDt);


	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "lst", true);


	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(		
		"BEF_ST_DT="+befStDt
		+"&BEF_ED_DT="+befEdDt
		+"&THS_ST_DT="+thsStDt
		+"&THS_ED_DT="+thsEdDt
	);
	
	xhr.onload = function () {
	  // xhr 객체의 status 값을 검사한다.
	  if (xhr.status === 200) {
	    // 서버로 부터 받은 데이터를 처리할 코드
		var resText = xhr.responseText;
		dstStatLst = JSON.parse(resText);
	
		set_grdStatList();
	  }
	}
}

/*
 * 조회버튼 클릭
 */
function set_grdStatList()
{
			
		var nThsSumAmt = 0;
		var nBefSumAmt = 0;	
		
		dstStatLst.forEach(function(e, i) 
		{ 
			
			nThsSumAmt += e.ths_SUM;			
			nBefSumAmt += e.bef_SUM;
			
			if(!e.ths_KR_NM)
			{
				dstStatLst[i] = `<div class="item" style="border: 2px solid #eee;">
									<div class="cell">${e.bef_KR_NM}</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.ths_SUM)} 원</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.bef_SUM)} 원</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.ths_SUM - e.bef_SUM)} 원</div>
							   </div>`;
			}
			else
			{
				dstStatLst[i] = `<div class="item" style="border: 2px solid #eee;">
									<div class="cell">${e.ths_KR_NM}</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.ths_SUM)} 원</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.bef_SUM)} 원</div>
									<div class="cell style="text-align: right;">${gfnSetComma(e.ths_SUM - e.bef_SUM)} 원</div>
							   </div>`;
			}
		}
		)
		document.querySelector('.grdStatList').innerHTML = dstStatLst.join('');
		
  		document.getElementById('txtThsSumAmt').innerText = gfnSetComma(nThsSumAmt);
  		document.getElementById('txtBefSumAmt').innerText = gfnSetComma(nBefSumAmt);
}




function fnGetDate(pDate)
{
 var sYear = pDate.getFullYear().toString();
 var sMonth = gfnLpad((pDate.getMonth()+1).toString(), 2, '0');
 var sDate = gfnLpad(pDate.getDate().toString(), 2, '0');
 return sYear+sMonth+sDate;
};
