package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlCtgyMapper;
import com.guodu.pojo.dtu.JlCtgy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlCtgyServiceImpl extends BaseServiceImpl<JlCtgy> {
    @Autowired
    private JlCtgyMapper jlCtgyMapper;

    public int deleteByPrimaryKey(String id) {
        return jlCtgyMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlCtgy record) {
        return jlCtgyMapper.insert2(record);
    }

    public int insertSelective(JlCtgy record) {
        return jlCtgyMapper.insertSelective(record);
    }

    public JlCtgy selectByPrimaryKey(String id) {
        return jlCtgyMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlCtgy record) {
        return jlCtgyMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlCtgy record) {
        return jlCtgyMapper.updateByPrimaryKey(record);
    }

    public List<JlCtgy> selectByAll(JlCtgy jlCtgy) {
        return jlCtgyMapper.selectByAll(jlCtgy);
    }

    public int updateBatch(List<JlCtgy> list) {
        return jlCtgyMapper.updateBatch(list);
    }

    public int batchInsert(List<JlCtgy> list) {
        return jlCtgyMapper.batchInsert(list);
    }

    public int deleteByTsid(String tsid) {
        return jlCtgyMapper.deleteByTsid(tsid);
    }


}

