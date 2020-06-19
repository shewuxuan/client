package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzTyjc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzTyjcMapper extends BaseMapper<FtuBzTyjc> {

    int insert2(FtuBzTyjc record);

    List<FtuBzTyjc> selectByAll(FtuBzTyjc record);

}