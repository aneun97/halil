package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.FixEvntAssetMgrVo;

@Repository
@Mapper
public interface FixEvntAssetMgrDao {
	List<FixEvntAssetMgrVo> lst(FixEvntAssetMgrVo fixEvntAssetMgrVo);
	void ins(FixEvntAssetMgrVo fixEvntAssetMgrVo);
	void insAssetEvnt(FixEvntAssetMgrVo fixEvntAssetMgrVo);
	void updRecDt(FixEvntAssetMgrVo fixEvntAssetMgrVo);
}
