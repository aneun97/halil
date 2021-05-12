package com.example.demo.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.test.dao.TestDao;
import com.example.demo.test.vo.TestVo;

@Service
public class TestSvc {

	@Autowired
	public TestDao testDao;
	
	public List<TestVo> selectTest(){
		return testDao.selectTest();
	}
}
