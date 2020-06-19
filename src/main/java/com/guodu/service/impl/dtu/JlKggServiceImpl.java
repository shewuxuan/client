package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlKggMapper;
import com.guodu.pojo.dtu.JlKgg;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlKggServiceImpl extends BaseServiceImpl<JlKgg> {

    @Autowired
    private JlKggMapper jlKggMapper;

    public int deleteByPrimaryKey(String id) {
        return jlKggMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlKgg record) {
        return jlKggMapper.insert2(record);
    }

    public int insertSelective(JlKgg record) {
        return jlKggMapper.insertSelective(record);
    }

    public JlKgg selectByPrimaryKey(String id) {
        return jlKggMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlKgg record) {
        return jlKggMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlKgg record) {
        return jlKggMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlKgg> list) {
        return jlKggMapper.updateBatch(list);
    }

    public int batchInsert(List<JlKgg> list) {
        return jlKggMapper.batchInsert(list);
    }

    public List<JlKgg> selectByAll(JlKgg jlKgg) {
        return jlKggMapper.selectByAll(jlKgg);
    }

    public int deleteByTsid(String tsid) {
        return jlKggMapper.deleteByTsid(tsid);
    }
}
