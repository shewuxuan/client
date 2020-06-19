package com.guodu.service.impl.sys;

import com.guodu.mapper.sys.SysDbMapper;
import com.guodu.pojo.sys.SysDb;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDbServiceImpl extends BaseServiceImpl<SysDb> {
    @Autowired
    private SysDbMapper sysDbMapper;
}

