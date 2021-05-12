package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.InsEvntAssetVo;

@Repository
@Mapper
public interface InsEvntAssetDao {
	List<InsEvntAssetVo> lstEvnt();
	List<InsEvntAssetVo> lstAsset();
	List<InsEvntAssetVo> lstMastAsset(InsEvntAssetVo insEvntAssetVo);
	InsEvntAssetVo viewEvnt(InsEvntAssetVo insEvntAssetVo);
	InsEvntAssetVo viewMastAsset(InsEvntAssetVo insEvntAssetVo);
	void isrt(InsEvntAssetVo insEvntAssetVo);
	void isrtMastAsset(InsEvntAssetVo insEvntAssetVo);
	void updtMastAsset(InsEvntAssetVo insEvntAssetVo);
	void updtMastAssetAft(InsEvntAssetVo insEvntAssetVo);
	void updtDecAssetAft(InsEvntAssetVo insEvntAssetVo);
	void updtIncAssetAft(InsEvntAssetVo insEvntAssetVo);
}
