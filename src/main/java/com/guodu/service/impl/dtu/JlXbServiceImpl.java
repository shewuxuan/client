package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlXbMapper;
import com.guodu.pojo.dtu.JlXb;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlXbServiceImpl extends BaseServiceImpl<JlXb> {

    @Autowired
    private JlXbMapper jlXbMapper;

    public int deleteByPrimaryKey(String id) {
        return jlXbMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlXb record) {
        return jlXbMapper.insert2(record);
    }

    public int insertSelective(JlXb record) {
        return jlXbMapper.insertSelective(record);
    }

    public JlXb selectByPrimaryKey(String id) {
        return jlXbMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlXb record) {
        return jlXbMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlXb record) {
        return jlXbMapper.updateByPrimaryKey(record);
    }

    public List<JlXb> selectByAll(JlXb jlXb) {
        return jlXbMapper.selectByAll(jlXb);
    }

    public int updateBatch(List<JlXb> list) {
        return jlXbMapper.updateBatch(list);
    }

    public int batchInsert(List<JlXb> list) {
        return jlXbMapper.batchInsert(list);
    }

    public int deleteByTsid(String tsid) {
        return jlXbMapper.deleteByTsid(tsid);
    }
}
