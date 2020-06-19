package com.guodu.service.ftu;

import java.util.List;

import com.guodu.pojo.ftu.FtuJlTzyb;

/**
 * @ClassName: FtuJlTzybService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/12 2:07 下午
 */
public interface FtuJlTzybService {


    int deleteByPrimaryKey(String id);

    int deleteByTsid(String tsid);

    int insert(FtuJlTzyb record);

    int insertOrUpdate(FtuJlTzyb record);

    int insertOrUpdateSelective(FtuJlTzyb record);

    int insertSelective(FtuJlTzyb record);

    FtuJlTzyb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FtuJlTzyb record);

    int updateByPrimaryKey(FtuJlTzyb record);

    List<FtuJlTzyb> selectByAll(FtuJlTzyb ftuJlTzyb);

    int updateBatch(List<FtuJlTzyb> list);

    int updateBatchSelective(List<FtuJlTzyb> list);

    int batchInsert(List<FtuJlTzyb> list);

}
