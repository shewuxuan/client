package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlHldz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JlHldzMapper extends BaseMapper<JlHldz> {
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
    int insert2(JlHldz record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlHldz record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlHldz selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlHldz record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlHldz record);

    List<JlHldz> selectByAll(JlHldz jlHldz);

    int updateBatch(List<JlHldz> list);

    int batchInsert(@Param("list") List<JlHldz> list);

    int deleteByTsid(@Param("tsid") String tsid);

    List<Map<String,String>> selectByJgGroup(@Param("tsid")String tsid);
}