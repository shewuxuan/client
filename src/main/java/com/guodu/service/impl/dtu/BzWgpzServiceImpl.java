package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzWgpzMapper;
import com.guodu.pojo.dtu.BzWgpz;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzWgpzServiceImpl extends BaseServiceImpl<BzWgpz> {

    @Autowired
    private BzWgpzMapper bzWgpzMapper;

    public int deleteByPrimaryKey(String id) {
        return bzWgpzMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzWgpz record) {
        return bzWgpzMapper.insert2(record);
    }

    public int insertSelective(BzWgpz record) {
        return bzWgpzMapper.insertSelective(record);
    }

    public BzWgpz selectByPrimaryKey(String id) {
        return bzWgpzMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzWgpz record) {
        return bzWgpzMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzWgpz record) {
        return bzWgpzMapper.updateByPrimaryKey(record);
    }

    public List<BzWgpz> selectByAll(BzWgpz bzWgpz) {
        return bzWgpzMapper.selectByAll(bzWgpz);
    }

    public int updateBatch(List<BzWgpz> list) {
        return bzWgpzMapper.updateBatch(list);
    }

    public int batchInsert(List<BzWgpz> list) {
        return bzWgpzMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzWgpz> bzWgpzs = bzWgpzMapper.selectByPage((BzWgpz) map.get("bzWgpz"));

        PageInfo<BzWgpz> pageInfo = new PageInfo<>(bzWgpzs);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
