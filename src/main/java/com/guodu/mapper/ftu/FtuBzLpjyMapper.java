package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzLpjy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzLpjyMapper extends BaseMapper<FtuBzLpjy> {

    int insert2(FtuBzLpjy record);

    List<FtuBzLpjy> selectByAll(FtuBzLpjy record);
}