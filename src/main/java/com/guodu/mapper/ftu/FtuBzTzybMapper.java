package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzTzyb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzTzybMapper extends BaseMapper<FtuBzTzyb> {

    int insert2(FtuBzTzyb record);

    List<FtuBzTzyb> selectByAll(FtuBzTzyb record);

}