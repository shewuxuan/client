package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlDyxnMapper;
import com.guodu.pojo.dtu.JlDyxn;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlDyxnServiceImpl extends BaseServiceImpl<JlDyxn> {

    @Autowired
    private JlDyxnMapper jlDyxnMapper;

    public int deleteByPrimaryKey(String id) {
        return jlDyxnMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlDyxn record) {
        return jlDyxnMapper.insert2(record);
    }

    public int insertSelective(JlDyxn record) {
        return jlDyxnMapper.insertSelective(record);
    }

    public JlDyxn selectByPrimaryKey(String id) {
        return jlDyxnMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlDyxn record) {
        return jlDyxnMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlDyxn record) {
        return jlDyxnMapper.updateByPrimaryKey(record);
    }

    public List<JlDyxn> selectByAll(JlDyxn jlDyxn) {
        return jlDyxnMapper.selectByAll(jlDyxn);
    }

    public int updateBatch(List<JlDyxn> list) {
        return jlDyxnMapper.updateBatch(list);
    }

    public int batchInsert(List<JlDyxn> list) {
        return jlDyxnMapper.batchInsert(list);
    }

    public int deleteByTsid(String tsid) {
        return jlDyxnMapper.deleteByTsid(tsid);
    }

}
