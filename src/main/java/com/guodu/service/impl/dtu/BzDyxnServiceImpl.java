package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzDyxnMapper;
import com.guodu.pojo.dtu.BzDyxn;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzDyxnServiceImpl extends BaseServiceImpl<BzDyxn> {

    @Autowired
    private BzDyxnMapper bzDyxnMapper;

    public int deleteByPrimaryKey(String id) {
        return bzDyxnMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzDyxn record) {
        return bzDyxnMapper.insert2(record);
    }

    public int insertSelective(BzDyxn record) {
        return bzDyxnMapper.insertSelective(record);
    }

    public BzDyxn selectByPrimaryKey(String id) {
        return bzDyxnMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzDyxn record) {
        return bzDyxnMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzDyxn record) {
        return bzDyxnMapper.updateByPrimaryKey(record);
    }

    public List<BzDyxn> selectByAll(BzDyxn bzDyxn) {
        return bzDyxnMapper.selectByAll(bzDyxn);
    }

    public int updateBatch(List<BzDyxn> list) {
        return bzDyxnMapper.updateBatch(list);
    }

    public int batchInsert(List<BzDyxn> list) {
        return bzDyxnMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzDyxn> bzDyxns = bzDyxnMapper.selectByPage((BzDyxn) map.get("bzDyxn"));
        PageInfo<BzDyxn> pageInfo = new PageInfo<>(bzDyxns);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
