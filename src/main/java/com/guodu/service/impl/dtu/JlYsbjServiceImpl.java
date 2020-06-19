package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlYsbjMapper;
import com.guodu.pojo.dtu.JlYsbj;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlYsbjServiceImpl extends BaseServiceImpl<JlYsbj> {
    @Autowired
    private JlYsbjMapper jlYsbjMapper;

    public int deleteByPrimaryKey(String id) {
        return jlYsbjMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlYsbj record) {
        return jlYsbjMapper.insert2(record);
    }

    public int insertSelective(JlYsbj record) {
        return jlYsbjMapper.insertSelective(record);
    }

    public JlYsbj selectByPrimaryKey(String id) {
        return jlYsbjMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlYsbj record) {
        return jlYsbjMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlYsbj record) {
        return jlYsbjMapper.updateByPrimaryKey(record);
    }

    public List<JlYsbj> selectByAll(JlYsbj jlYsbj) {
        return jlYsbjMapper.selectByAll(jlYsbj);
    }

    public int updateBatch(List<JlYsbj> list) {
        return jlYsbjMapper.updateBatch(list);
    }

    public int batchInsert(List<JlYsbj> list) {
        return jlYsbjMapper.batchInsert(list);
    }

    public int deleteByTsid(String tsid) {
        return jlYsbjMapper.deleteByTsid(tsid);
    }
}
