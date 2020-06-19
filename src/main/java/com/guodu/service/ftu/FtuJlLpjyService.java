package com.guodu.service.ftu;

import java.util.List;

import com.guodu.pojo.ftu.FtuJlLpjy;

/**
 * @ClassName: FtuJlLpjyService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/12 2:07 下午
 */
public interface FtuJlLpjyService {


    int deleteByPrimaryKey(String id);

    int deleteByTsid(String tsid);

    int insert(FtuJlLpjy record);

    int insertOrUpdate(FtuJlLpjy record);

    int insertOrUpdateSelective(FtuJlLpjy record);

    int insertSelective(FtuJlLpjy record);

    FtuJlLpjy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FtuJlLpjy record);

    int updateByPrimaryKey(FtuJlLpjy record);

    List<FtuJlLpjy> selectByAll(FtuJlLpjy ftuJlLpjy);

    int updateBatch(List<FtuJlLpjy> list);

    int updateBatchSelective(List<FtuJlLpjy> list);

    int batchInsert(List<FtuJlLpjy> list);

}
