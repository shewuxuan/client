package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzCyjd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzCyjdMapper extends BaseMapper<FtuBzCyjd> {

    int insert2(FtuBzCyjd record);

    List<FtuBzCyjd> selectByAll(FtuBzCyjd record);

}