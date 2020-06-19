package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlXb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlXbMapper extends BaseMapper<JlXb> {
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
    int insert2(JlXb record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlXb record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlXb selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlXb record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlXb record);

    List<JlXb> selectByAll(JlXb jlXb);

    int updateBatch(List<JlXb> list);

    int batchInsert(@Param("list") List<JlXb> list);

    int deleteByTsid(@Param("tsid") String tsid);
}