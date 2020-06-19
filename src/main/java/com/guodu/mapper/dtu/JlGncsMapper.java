package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlGncs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlGncsMapper extends BaseMapper<JlGncs> {
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
    int insert2(JlGncs record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlGncs record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlGncs selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlGncs record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlGncs record);

    List<JlGncs> selectByAll(JlGncs jlGncs);

    int updateBatch(List<JlGncs> list);

    int batchInsert(@Param("list") List<JlGncs> list);

    int deleteByTsid(@Param("tsid") String tsid);
}