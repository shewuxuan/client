package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzDlhlMapper;
import com.guodu.pojo.dtu.BzDlhl;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzDlhlServiceImpl extends BaseServiceImpl<BzDlhl> {

    @Autowired
    private BzDlhlMapper bzDlhlMapper;

    public int deleteByPrimaryKey(String id) {
        return bzDlhlMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzDlhl record) {
        return bzDlhlMapper.insert2(record);
    }

    public int insertSelective(BzDlhl record) {
        return bzDlhlMapper.insertSelective(record);
    }

    public BzDlhl selectByPrimaryKey(String id) {
        return bzDlhlMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzDlhl record) {
        return bzDlhlMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzDlhl record) {
        return bzDlhlMapper.updateByPrimaryKey(record);
    }

    public List<BzDlhl> selectByAll(BzDlhl bzDlhl) {
        return bzDlhlMapper.selectByAll(bzDlhl);
    }

    public int updateBatch(List<BzDlhl> list) {
        return bzDlhlMapper.updateBatch(list);
    }

    public int batchInsert(List<BzDlhl> list) {
        return bzDlhlMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzDlhl> bzDlhls = bzDlhlMapper.selectByPage((BzDlhl) map.get("bzDlhl"));
        PageInfo<BzDlhl> pageInfo = new PageInfo<>(bzDlhls);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}

