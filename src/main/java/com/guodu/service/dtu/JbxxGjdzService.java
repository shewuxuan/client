package com.guodu.service.dtu;

import com.guodu.pojo.dtu.JbxxGjdz;

/**
 * @ClassName: JbxxGjdzService
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/6 1:48 下午
 */
public interface JbxxGjdzService {


    int deleteByPrimaryKey(String tsid);

    int insert(JbxxGjdz record);

    int insertSelective(JbxxGjdz record);

    JbxxGjdz selectByPrimaryKey(String tsid);

    int updateByPrimaryKeySelective(JbxxGjdz record);

    int updateByPrimaryKey(JbxxGjdz record);

    int updateByPrimaryKeyAndKgg(JbxxGjdz record);

}

