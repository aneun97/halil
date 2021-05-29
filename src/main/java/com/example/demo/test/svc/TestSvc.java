package com.example.demo.test.svc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.TestDao;
import com.example.demo.test.vo.TestVo;

@Service
public class TestSvc {

	@Autowired
	public TestDao testDao;
	
	public List<TestVo> lstMonthAsset(TestVo testVo){
		testVo.setDATE_LIST(setDateList(testVo.getST_DT(), testVo.getED_DT(), "yyyyMMdd"));
		return testDao.lstMonthAsset(testVo);
	}
	
	
	
	private List<String> setDateList(String startDate, String endDate, String format) {

	    List<String> dateList = new ArrayList<String>();



	    SimpleDateFormat formatter = new SimpleDateFormat(format);

	    try {

	        Calendar beginDate = Calendar.getInstance();

	        Calendar stopDate = Calendar.getInstance();



	        beginDate.setTime(formatter.parse(startDate));

	        stopDate.setTime(formatter.parse(endDate));



	        while (beginDate.compareTo(stopDate) != 1) {

	            dateList.add(formatter.format(beginDate.getTime()));



	            beginDate.add(Calendar.DATE, 1);

	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return dateList;

	}
}
