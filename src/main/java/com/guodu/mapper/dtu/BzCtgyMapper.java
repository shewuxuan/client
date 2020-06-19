package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzCtgy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzCtgyMapper extends BaseMapper<BzCtgy> {

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
    int insert2(BzCtgy record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzCtgy record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzCtgy selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzCtgy record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzCtgy record);

    List<BzCtgy> selectByAll(BzCtgy bzCtgy);

    int updateBatch(List<BzCtgy> list);

    int batchInsert(@Param("list") List<BzCtgy> list);

    List<BzCtgy> selectByPage(BzCtgy bzCtgy);
}