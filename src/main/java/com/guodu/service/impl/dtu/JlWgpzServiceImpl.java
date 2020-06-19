package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlWgpzMapper;
import com.guodu.pojo.dtu.JlWgpz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlWgpzServiceImpl extends BaseServiceImpl<JlWgpz> {

    @Autowired
    private JlWgpzMapper jlWgpzMapper;

    public int deleteByPrimaryKey(String id) {
        return jlWgpzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlWgpz record) {
        return jlWgpzMapper.insert2(record);
    }

    public int insertSelective(JlWgpz record) {
        return jlWgpzMapper.insertSelective(record);
    }

    public JlWgpz selectByPrimaryKey(String id) {
        return jlWgpzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlWgpz record) {
        return jlWgpzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlWgpz record) {
        return jlWgpzMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlWgpz> list) {
        return jlWgpzMapper.updateBatch(list);
    }

    public int batchInsert(List<JlWgpz> list) {
        return jlWgpzMapper.batchInsert(list);
    }

    public List<JlWgpz> selectByAll(JlWgpz jlWgpz) {
        return jlWgpzMapper.selectByAll(jlWgpz);
    }

    public int deleteByTsid(String tsid) {
        return jlWgpzMapper.deleteByTsid(tsid);
    }
}
