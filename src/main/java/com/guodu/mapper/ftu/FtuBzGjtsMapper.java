package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuBzGjts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FtuBzGjtsMapper extends BaseMapper<FtuBzGjts> {

    int insert2(FtuBzGjts record);

    List<FtuBzGjts> selectByAll(FtuBzGjts record);

}