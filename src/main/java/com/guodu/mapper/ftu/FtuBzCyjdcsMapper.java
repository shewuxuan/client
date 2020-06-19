package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzCyjdcs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzCyjdcsMapper extends BaseMapper<FtuBzCyjdcs> {

    int insert2(FtuBzCyjdcs record);

    List<FtuBzCyjdcs> selectByAll(FtuBzCyjdcs record);
}