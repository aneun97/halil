package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.EvntAssetStatVo;

@Repository
@Mapper
public interface EvntAssetStatDao {
	List<EvntAssetStatVo> lst(EvntAssetStatVo evntAssetStatVo);
}
