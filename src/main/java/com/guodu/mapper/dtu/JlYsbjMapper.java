package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlYsbj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlYsbjMapper extends BaseMapper<JlYsbj> {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert2(JlYsbj record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlYsbj record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlYsbj selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlYsbj record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlYsbj record);

    List<JlYsbj> selectByAll(JlYsbj jlYsbj);

    int updateBatch(List<JlYsbj> list);

    int batchInsert(@Param("list") List<JlYsbj> list);

    int deleteByTsid(@Param("tsid") String tsid);
}