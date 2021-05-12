package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.PayRcvSumDao;
import com.example.demo.test.vo.PayRcvSumVo;


@Service
public class PayRcvSumSvc {

	@Autowired
	public PayRcvSumDao payRcvSumDao;
	
	public List<PayRcvSumVo> lst(PayRcvSumVo payRcvSumVo){
		return payRcvSumDao.lst(payRcvSumVo);
	}

}
