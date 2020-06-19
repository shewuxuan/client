package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlGncsMapper;
import com.guodu.pojo.dtu.JlGncs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlGncsServiceImpl extends BaseServiceImpl<JlGncs> {

    @Autowired
    private JlGncsMapper jlGncsMapper;

    public int deleteByPrimaryKey(String id) {
        return jlGncsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlGncs record) {
        return jlGncsMapper.insert2(record);
    }

    public int insertSelective(JlGncs record) {
        return jlGncsMapper.insertSelective(record);
    }

    public JlGncs selectByPrimaryKey(String id) {
        return jlGncsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlGncs record) {
        return jlGncsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlGncs record) {
        return jlGncsMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlGncs> list) {
        return jlGncsMapper.updateBatch(list);
    }

    public int batchInsert(List<JlGncs> list) {
        return jlGncsMapper.batchInsert(list);
    }

    public List<JlGncs> selectByAll(JlGncs jlGncs) {
        return jlGncsMapper.selectByAll(jlGncs);
    }

    public int deleteByTsid(String tsid) {
        return jlGncsMapper.deleteByTsid(tsid);
    }
}
