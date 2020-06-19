package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzJydz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzJydzMapper extends BaseMapper<BzJydz> {
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
    int insert2(BzJydz record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzJydz record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzJydz selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzJydz record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzJydz record);

    List<BzJydz> selectByAll(BzJydz bzJydz);

    int updateBatch(List<BzJydz> list);

    int batchInsert(@Param("list") List<BzJydz> list);

    List<BzJydz> selectByPage(BzJydz bzJydz);
}