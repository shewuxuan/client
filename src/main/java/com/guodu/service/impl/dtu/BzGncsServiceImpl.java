package com.guodu.service.impl.dtu;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guodu.mapper.dtu.BzGncsMapper;
import com.guodu.pojo.dtu.BzGncs;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BzGncsServiceImpl extends BaseServiceImpl<BzGncs> {

    @Autowired
    private BzGncsMapper bzGncsMapper;

    public int deleteByPrimaryKey(String id) {
        return bzGncsMapper.deleteByPrimaryKey(id);
    }

    public int insert2(BzGncs record) {
        return bzGncsMapper.insert2(record);
    }

    public int insertSelective(BzGncs record) {
        return bzGncsMapper.insertSelective(record);
    }

    public BzGncs selectByPrimaryKey(String id) {
        return bzGncsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BzGncs record) {
        return bzGncsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BzGncs record) {
        return bzGncsMapper.updateByPrimaryKey(record);
    }

    public List<BzGncs> selectByAll(BzGncs bzGncs) {
        return bzGncsMapper.selectByAll(bzGncs);
    }

    public int updateBatch(List<BzGncs> list) {
        return bzGncsMapper.updateBatch(list);
    }

    public int batchInsert(List<BzGncs> list) {
        return bzGncsMapper.batchInsert(list);
    }

    public String selectByPage(Map<String, Object> map) {
        // pagehelper分页
        PageHelper.startPage((Integer) map.get("page"), (Integer) map.get("rows"));
        List<BzGncs> bzGncss = bzGncsMapper.selectByPage((BzGncs) map.get("bzGncs"));
        PageInfo<BzGncs> pageInfo = new PageInfo<>(bzGncss);

        Map<String, Object> respMap = new HashMap<>(16);
        respMap.put("rows", pageInfo.getList());
        respMap.put("total", pageInfo.getTotal());
        return JSONUtil.toJsonStr(respMap);
    }
}
