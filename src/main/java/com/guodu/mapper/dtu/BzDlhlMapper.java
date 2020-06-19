package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzDlhl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzDlhlMapper extends BaseMapper<BzDlhl> {

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
    int insert2(BzDlhl record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzDlhl record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzDlhl selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzDlhl record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzDlhl record);

    List<BzDlhl> selectByAll(BzDlhl bzDlhl);

    int updateBatch(List<BzDlhl> list);

    int batchInsert(@Param("list") List<BzDlhl> list);

    List<BzDlhl> selectByPage(BzDlhl bzDlhl);
}