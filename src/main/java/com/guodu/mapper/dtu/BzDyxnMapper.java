package com.guodu.mapper.dtu;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.dtu.BzDyxn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BzDyxnMapper extends BaseMapper<BzDyxn> {
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
    int insert2(BzDyxn record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(BzDyxn record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    BzDyxn selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BzDyxn record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BzDyxn record);

    List<BzDyxn> selectByAll(BzDyxn bzDyxn);

    int updateBatch(List<BzDyxn> list);

    int batchInsert(@Param("list") List<BzDyxn> list);

    List<BzDyxn> selectByPage(BzDyxn bzDyxn);
}