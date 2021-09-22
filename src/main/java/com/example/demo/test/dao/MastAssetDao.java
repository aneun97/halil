package com.example.demo.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.InsEvntAssetVo;
import com.example.demo.test.vo.MastAssetVo;

@Repository
@Mapper
public interface MastAssetDao {
	InsEvntAssetVo viewEvnt(InsEvntAssetVo insEvntAssetVo);
	MastAssetVo viwMastAsset(MastAssetVo mastAssetVo);
	void insMastAsset(MastAssetVo mastAssetVo);
	void updMastAsset(MastAssetVo mastAssetVo);
	void updMastAssetAft(MastAssetVo mastAssetVo);
	void updDecAssetAft(MastAssetVo mastAssetVo);
	void updIncAssetAft(MastAssetVo mastAssetVo);
}
