package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlYkts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlYktsMapper extends BaseMapper<JlYkts> {
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
    int insert2(JlYkts record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlYkts record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlYkts selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlYkts record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlYkts record);

    List<JlYkts> selectByAll(JlYkts jlYkts);

    int updateBatch(List<JlYkts> list);

    int batchInsert(@Param("list") List<JlYkts> list);

    int deleteByTsid(@Param("tsid") String tsid);
}