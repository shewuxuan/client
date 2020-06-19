package com.guodu.mapper.ftu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.ftu.FtuJbxx;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FtuJbxxMapper extends BaseMapper<FtuJbxx> {

    FtuJbxx selectByPrimaryKey(String id);
}