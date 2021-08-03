package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.test.vo.AssetAmtSelVo;

@Repository
@Mapper
public interface AssetAmtSelDao {
	List<AssetAmtSelVo> viwTotal(AssetAmtSelVo assetAmtSelVo);
	void ins(AssetAmtSelVo AssetAmtSelVo);
}
