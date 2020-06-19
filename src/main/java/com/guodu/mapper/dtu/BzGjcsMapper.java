package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzGjcs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzGjcsMapper extends BaseMapper<BzGjcs> {
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
    int insert2(BzGjcs record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzGjcs record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzGjcs selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzGjcs record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzGjcs record);

    List<BzGjcs> selectByAll(BzGjcs bzGjcs);

    int updateBatch(List<BzGjcs> list);

    int batchInsert(@Param("list") List<BzGjcs> list);

    List<BzGjcs> selectByPage(BzGjcs bzGjcs);
}