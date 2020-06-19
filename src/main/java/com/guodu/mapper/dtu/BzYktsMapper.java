package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzYkts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzYktsMapper extends BaseMapper<BzYkts> {
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
    int insert2(BzYkts record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzYkts record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzYkts selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzYkts record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzYkts record);

    List<BzYkts> selectByAll(BzYkts bzYkts);

    int updateBatch(List<BzYkts> list);

    int batchInsert(@Param("list") List<BzYkts> list);

    List<BzYkts> selectByPage(BzYkts bzYkts);
}