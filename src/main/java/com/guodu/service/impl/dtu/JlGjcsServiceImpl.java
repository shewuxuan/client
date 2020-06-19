package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlGjcsMapper;
import com.guodu.pojo.dtu.JlGjcs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlGjcsServiceImpl extends BaseServiceImpl<JlGjcs> {

    @Autowired
    private JlGjcsMapper jlGjcsMapper;

    public int deleteByPrimaryKey(String id) {
        return jlGjcsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlGjcs record) {
        return jlGjcsMapper.insert2(record);
    }

    public int insertSelective(JlGjcs record) {
        return jlGjcsMapper.insertSelective(record);
    }

    public JlGjcs selectByPrimaryKey(String id) {
        return jlGjcsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlGjcs record) {
        return jlGjcsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlGjcs record) {
        return jlGjcsMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlGjcs> list) {
        return jlGjcsMapper.updateBatch(list);
    }

    public int batchInsert(List<JlGjcs> list) {
        return jlGjcsMapper.batchInsert(list);
    }

    public List<JlGjcs> selectByAll(JlGjcs jlGjcs) {
        return jlGjcsMapper.selectByAll(jlGjcs);
    }

    public int deleteByTsid(String tsid) {
        return jlGjcsMapper.deleteByTsid(tsid);
    }
}
