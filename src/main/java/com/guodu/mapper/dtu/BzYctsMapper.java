package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzYcts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzYctsMapper extends BaseMapper<BzYcts> {

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
    int insert2(BzYcts record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzYcts record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzYcts selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzYcts record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzYcts record);

    List<BzYcts> selectByAll(BzYcts bzYcts);

    int updateBatch(List<BzYcts> list);

    int batchInsert(@Param("list") List<BzYcts> list);

    List<BzYcts> selectByPage(BzYcts bzYcts);
}