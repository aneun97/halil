package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.EvntAssetSelVo;

@Repository
@Mapper
public interface EvntAssetSelDao {
	List<EvntAssetSelVo> lst(EvntAssetSelVo evntAssetSelVo);
	List<EvntAssetSelVo> lstUnChk(EvntAssetSelVo evntAssetSelVo);
	EvntAssetSelVo viw(EvntAssetSelVo evntAssetSelVo);
	void ins(EvntAssetSelVo evntAssetSelVo);
	void upd(EvntAssetSelVo evntAssetSelVo);
	void del(EvntAssetSelVo evntAssetSelVo);
}
