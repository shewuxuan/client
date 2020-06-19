package com.guodu.service.pwbh;

import com.guodu.pojo.pwbh.PwbhJbxx;

/**
 * @ClassName: PwbhTsjlService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/6/15 2:32 下午
 */
public interface PwbhTsjlService {

    void insertPwbhTsjlByPwbhJbxx(PwbhJbxx record);

    void deletePwbhTsjlByPwbhJbxx(String tsid);
}
