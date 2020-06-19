package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzCyjd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzCyjdMapper extends BaseMapper<BzCyjd> {
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
    int insert2(BzCyjd record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzCyjd record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzCyjd selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzCyjd record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzCyjd record);

    List<BzCyjd> selectByAll(BzCyjd bzCyjd);

    int updateBatch(List<BzCyjd> list);

    int updateBatchSelective(List<BzCyjd> list);

    int batchInsert(@Param("list") List<BzCyjd> list);

    List<BzCyjd> selectByPage(BzCyjd bzCyjd);
}