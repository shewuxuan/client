package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JlDlhlMapper;
import com.guodu.pojo.dtu.JlDlhl;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JlDlhlServiceImpl extends BaseServiceImpl<JlDlhl> {

    @Autowired
    private JlDlhlMapper jlDlhlMapper;

    public int deleteByPrimaryKey(String id) {
        return jlDlhlMapper.deleteByPrimaryKey(id);
    }

    public int insert2(JlDlhl record) {
        return jlDlhlMapper.insert2(record);
    }

    public int insertSelective(JlDlhl record) {
        return jlDlhlMapper.insertSelective(record);
    }

    public JlDlhl selectByPrimaryKey(String id) {
        return jlDlhlMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(JlDlhl record) {
        return jlDlhlMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(JlDlhl record) {
        return jlDlhlMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<JlDlhl> list) {
        return jlDlhlMapper.updateBatch(list);
    }

    public int batchInsert(List<JlDlhl> list) {
        return jlDlhlMapper.batchInsert(list);
    }

    public List<JlDlhl> selectByAll(JlDlhl jlDlhl) {
        return jlDlhlMapper.selectByAll(jlDlhl);
    }

    public int deleteByTsid(String tsid) {
        return jlDlhlMapper.deleteByTsid(tsid);
    }

}

