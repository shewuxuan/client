package com.guodu.mapper.dtu;

import com.guodu.pojo.dtu.JbxxGjdz;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: JbxxGjdzMapper
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/21 2:00 下午
 */
@Mapper
public interface JbxxGjdzMapper {
    /**
     * delete by primary key
     *
     * @param tsid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String tsid);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(JbxxGjdz record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(JbxxGjdz record);

    /**
     * select by primary key
     *
     * @param tsid primary key
     * @return object by primary key
     */
    JbxxGjdz selectByPrimaryKey(String tsid);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(JbxxGjdz record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(JbxxGjdz record);

    int updateByPrimaryKeyAndKgg(JbxxGjdz record);

}