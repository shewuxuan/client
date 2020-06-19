package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlDtu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlDtuMapper extends BaseMapper<JlDtu> {
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
    int insert2(JlDtu record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlDtu record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlDtu selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlDtu record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlDtu record);

    List<JlDtu> selectByAll(JlDtu jlDtu);

    int updateBatch(List<JlDtu> list);

    int batchInsert(@Param("list") List<JlDtu> list);

    int deleteByTsid(@Param("tsid") String tsid);
}