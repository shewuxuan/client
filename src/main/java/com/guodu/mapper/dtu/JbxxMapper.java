package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.Jbxx;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JbxxMapper extends BaseMapper<Jbxx> {

    /**
     * select by primary key
     *
     * @param tsid primary key
     * @return object by primary key
     */
    Jbxx selectByPrimaryKey(String tsid);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Jbxx record);

}
