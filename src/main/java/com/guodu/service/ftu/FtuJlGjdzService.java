package com.guodu.service.ftu;

import java.util.List;
import com.guodu.pojo.ftu.FtuJlGjdz;
    /**
 * @ClassName: FtuJlGjdzService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/12 2:05 下午
 */
public interface FtuJlGjdzService{


    int deleteByPrimaryKey(String id);

    int deleteByTsid(String tsid);

    int insert(FtuJlGjdz record);

    int insertOrUpdate(FtuJlGjdz record);

    int insertOrUpdateSelective(FtuJlGjdz record);

    int insertSelective(FtuJlGjdz record);

    FtuJlGjdz selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FtuJlGjdz record);

    int updateByPrimaryKey(FtuJlGjdz record);

    List<FtuJlGjdz> selectByAll(FtuJlGjdz ftuJlGjdz);

    int updateBatch(List<FtuJlGjdz> list);

    int updateBatchSelective(List<FtuJlGjdz> list);

    int batchInsert(List<FtuJlGjdz> list);

}
