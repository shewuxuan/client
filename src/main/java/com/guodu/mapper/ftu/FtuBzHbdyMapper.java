package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzHbdy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzHbdyMapper extends BaseMapper<FtuBzHbdy> {

    int insert2(FtuBzHbdy record);

    List<FtuBzHbdy> selectByAll(FtuBzHbdy record);

}