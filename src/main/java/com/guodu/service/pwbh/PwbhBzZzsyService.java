package com.guodu.service.pwbh;

import java.util.List;
import com.guodu.pojo.pwbh.PwbhBzZzsy;
    /**
 * @ClassName: PwbhBzZzsyService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/15 4:19 下午
 */
public interface PwbhBzZzsyService{


    int deleteByPrimaryKey(String id);

    int insert(PwbhBzZzsy record);

    int insertSelective(PwbhBzZzsy record);

    PwbhBzZzsy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PwbhBzZzsy record);

    int updateByPrimaryKey(PwbhBzZzsy record);

    List<PwbhBzZzsy> selectByAll(PwbhBzZzsy pwbhBzZzsy);

    int updateBatch(List<PwbhBzZzsy> list);

    int updateBatchSelective(List<PwbhBzZzsy> list);

    int batchInsert(List<PwbhBzZzsy> list);

}
