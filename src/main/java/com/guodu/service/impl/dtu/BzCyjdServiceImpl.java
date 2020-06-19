package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzCyjdMapper;
import com.guodu.pojo.dtu.BzCyjd;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzCyjdServiceImpl extends BaseServiceImpl<BzCyjd> {

    @Autowired
    private BzCyjdMapper bzCyjdMapper;

    public int deleteByPrimaryKey(String id) {
        return bzCyjdMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzCyjd record) {
        return bzCyjdMapper.insert2(record);
    }

    public int insertSelective(BzCyjd record) {
        return bzCyjdMapper.insertSelective(record);
    }

    public BzCyjd selectByPrimaryKey(String id) {
        return bzCyjdMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzCyjd record) {
        return bzCyjdMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzCyjd record) {
        return bzCyjdMapper.updateByPrimaryKey(record);
    }

    public List<BzCyjd> selectByAll(BzCyjd bzCyjd) {
        return bzCyjdMapper.selectByAll(bzCyjd);
    }

    public int updateBatch(List<BzCyjd> list) {
        return bzCyjdMapper.updateBatch(list);
    }

    public int batchInsert(List<BzCyjd> list) {
        return bzCyjdMapper.batchInsert(list);
    }

    public int updateBatchSelective(List<BzCyjd> list) {
        return bzCyjdMapper.updateBatchSelective(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzCyjd> bzCyjds = bzCyjdMapper.selectByPage((BzCyjd) map.get("bzCyjd"));
        PageInfo<BzCyjd> pageInfo = new PageInfo<>(bzCyjds);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}

