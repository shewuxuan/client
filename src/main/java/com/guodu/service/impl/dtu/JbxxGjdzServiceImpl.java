package com.guodu.service.impl.dtu;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.guodu.mapper.dtu.JbxxGjdzMapper;
import com.guodu.pojo.dtu.JbxxGjdz;
import com.guodu.service.dtu.JbxxGjdzService;

/**
 * @ClassName: JbxxGjdzServiceImpl
 * @Description: TODO
 * @Author: 2Uli
 * @Date: 2020/5/6 1:48 下午
 */
@Service
public class JbxxGjdzServiceImpl implements JbxxGjdzService {

    @Resource
    private JbxxGjdzMapper jbxxGjdzMapper;

    @Override
    public int deleteByPrimaryKey(String tsid) {
        return jbxxGjdzMapper.deleteByPrimaryKey(tsid);
    }

    @Override
    public int insert(JbxxGjdz record) {
        return jbxxGjdzMapper.insert(record);
    }

    @Override
    public int insertSelective(JbxxGjdz record) {
        return jbxxGjdzMapper.insertSelective(record);
    }

    @Override
    public JbxxGjdz selectByPrimaryKey(String tsid) {
        return jbxxGjdzMapper.selectByPrimaryKey(tsid);
    }

    @Override
    public int updateByPrimaryKeySelective(JbxxGjdz record) {
        return jbxxGjdzMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JbxxGjdz record) {
        return jbxxGjdzMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeyAndKgg(JbxxGjdz record) {
        return jbxxGjdzMapper.updateByPrimaryKeyAndKgg(record);
    }
}

