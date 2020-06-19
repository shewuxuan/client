package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlCyjdMapper;
import com.guodu.pojo.dtu.JlCyjd;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlCyjdServiceImpl extends BaseServiceImpl<JlCyjd> {

    @Autowired
    private JlCyjdMapper jlCyjdMapper;

    public int deleteByPrimaryKey(String id) {
        return jlCyjdMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlCyjd record) {
        return jlCyjdMapper.insert2(record);
    }

    public int insertSelective(JlCyjd record) {
        return jlCyjdMapper.insertSelective(record);
    }

    public JlCyjd selectByPrimaryKey(String id) {
        return jlCyjdMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlCyjd record) {
        return jlCyjdMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlCyjd record) {
        return jlCyjdMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlCyjd> list) {
        return jlCyjdMapper.updateBatch(list);
    }

    public int batchInsert(List<JlCyjd> list) {
        return jlCyjdMapper.batchInsert(list);
    }

    public List<JlCyjd> selectByAll(JlCyjd jlCyjd) {
        return jlCyjdMapper.selectByAll(jlCyjd);
    }

    public int deleteByTsid(String tsid) {
        return jlCyjdMapper.deleteByTsid(tsid);
    }

}

