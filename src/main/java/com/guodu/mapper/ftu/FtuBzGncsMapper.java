package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzGncs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzGncsMapper extends BaseMapper<FtuBzGncs> {

    int insert2(FtuBzGncs record);

    List<FtuBzGncs> selectByAll(FtuBzGncs record);

}