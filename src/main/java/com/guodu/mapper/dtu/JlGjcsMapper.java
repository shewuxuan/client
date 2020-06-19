package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlGjcs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlGjcsMapper extends BaseMapper<JlGjcs> {
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
    int insert2(JlGjcs record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlGjcs record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlGjcs selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlGjcs record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlGjcs record);

    List<JlGjcs> selectByAll(JlGjcs jlGjcs);

    int updateBatch(List<JlGjcs> list);

    int batchInsert(@Param("list") List<JlGjcs> list);

    int deleteByTsid(@Param("tsid") String tsid);
}