package com.guodu.service.ftu;

import com.guodu.pojo.ftu.FtuJbxx;

/**
 * @ClassName: FtuTsjlService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/12 1:52 下午
 */
public interface FtuTsjlService {

    void insertFtuTsjl(FtuJbxx ftuJbxx);

    void deleteFtuTsjl(String tsid);
}
