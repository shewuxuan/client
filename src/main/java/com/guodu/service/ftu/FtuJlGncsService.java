package com.guodu.service.ftu;

import java.util.List;
import com.guodu.pojo.ftu.FtuJlGncs;
    /**
 * @ClassName: FtuJlGncsService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/12 2:06 下午
 */
public interface FtuJlGncsService{


    int deleteByPrimaryKey(String id);

    int deleteByTsid(String tsid);

    int insert(FtuJlGncs record);

    int insertOrUpdate(FtuJlGncs record);

    int insertOrUpdateSelective(FtuJlGncs record);

    int insertSelective(FtuJlGncs record);

    FtuJlGncs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FtuJlGncs record);

    int updateByPrimaryKey(FtuJlGncs record);

    List<FtuJlGncs> selectByAll(FtuJlGncs ftuJlGncs);

    int updateBatch(List<FtuJlGncs> list);

    int updateBatchSelective(List<FtuJlGncs> list);

    int batchInsert(List<FtuJlGncs> list);

}
