package com.guodu.mapper.pwbh;

import com.guodu.pojo.pwbh.PwbhBzJdjy;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PwbhBzJdjyMapper
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/11 3:59 下午
 */
@Mapper
public interface PwbhBzJdjyMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param(value = "id") String id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(PwbhBzJdjy record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PwbhBzJdjy record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    PwbhBzJdjy selectByPrimaryKey(String id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PwbhBzJdjy record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PwbhBzJdjy record);

    List<PwbhBzJdjy> selectByAll(PwbhBzJdjy pwbhBzJdjy);

    int updateBatch(List<PwbhBzJdjy> list);

    int updateBatchSelective(List<PwbhBzJdjy> list);

    int batchInsert(@Param("list") List<PwbhBzJdjy> list);
}