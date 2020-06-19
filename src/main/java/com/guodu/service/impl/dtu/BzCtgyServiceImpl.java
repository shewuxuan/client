package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzCtgyMapper;
import com.guodu.pojo.dtu.BzCtgy;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzCtgyServiceImpl extends BaseServiceImpl<BzCtgy> {
    @Autowired
    private BzCtgyMapper bzCtgyMapper;

    public int deleteByPrimaryKey(String id) {
        return bzCtgyMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzCtgy record) {
        return bzCtgyMapper.insert2(record);
    }

    public int insertSelective(BzCtgy record) {
        return bzCtgyMapper.insertSelective(record);
    }

    public BzCtgy selectByPrimaryKey(String id) {
        return bzCtgyMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzCtgy record) {
        return bzCtgyMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzCtgy record) {
        return bzCtgyMapper.updateByPrimaryKey(record);
    }

    public List<BzCtgy> selectByAll(BzCtgy bzCtgy) {
        return bzCtgyMapper.selectByAll(bzCtgy);
    }

    public int updateBatch(List<BzCtgy> list) {
        return bzCtgyMapper.updateBatch(list);
    }

    public int batchInsert(List<BzCtgy> list) {
        return bzCtgyMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzCtgy> bzCtgys = bzCtgyMapper.selectByPage((BzCtgy) map.get("bzCtgy"));
        PageInfo<BzCtgy> pageInfo = new PageInfo<>(bzCtgys);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}

