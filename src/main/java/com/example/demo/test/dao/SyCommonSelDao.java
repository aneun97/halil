package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.SyCommonSelVo;

@Repository
@Mapper
public interface SyCommonSelDao {
	List<SyCommonSelVo> lstEvnt();
	List<SyCommonSelVo> lstAsset(SyCommonSelVo syCommonSelVo);
	List<SyCommonSelVo> lstAssetKind();
	List<SyCommonSelVo> lstEvntHcls();
	List<SyCommonSelVo> lstEvntLcls(SyCommonSelVo syCommonSelVo);
}
