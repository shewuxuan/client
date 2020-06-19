package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.JlDlhl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JlDlhlMapper extends BaseMapper<JlDlhl> {
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
    int insert2(JlDlhl record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JlDlhl record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    JlDlhl selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JlDlhl record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JlDlhl record);

    List<JlDlhl> selectByAll(JlDlhl jlDlhl);

    int updateBatch(List<JlDlhl> list);

    int batchInsert(@Param("list") List<JlDlhl> list);

    int deleteByTsid(@Param("tsid") String tsid);
}