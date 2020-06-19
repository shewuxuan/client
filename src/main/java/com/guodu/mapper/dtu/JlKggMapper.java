package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlKgg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface JlKggMapper extends BaseMapper<JlKgg> {
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
    int insert2(JlKgg record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlKgg record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlKgg selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlKgg record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlKgg record);

    List<JlKgg> selectByAll(JlKgg jlKgg);

    int updateBatch(List<JlKgg> list);

    int batchInsert(@Param("list") List<JlKgg> list);

    int deleteByTsid(@Param("tsid") String tsid);
}