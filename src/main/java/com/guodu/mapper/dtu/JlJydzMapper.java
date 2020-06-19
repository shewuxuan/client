package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlJydz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlJydzMapper extends BaseMapper<JlJydz> {
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
    int insert2(JlJydz record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlJydz record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlJydz selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlJydz record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlJydz record);

    List<JlJydz> selectByAll(JlJydz jlJydz);

    int updateBatch(List<JlJydz> list);

    int batchInsert(@Param("list") List<JlJydz> list);

    int deleteByTsid(@Param("tsid") String tsid);
}