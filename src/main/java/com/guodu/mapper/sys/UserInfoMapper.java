package com.guodu.mapper.sys;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.sys.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<Map<String, Object>> getComboTreeData(String roleId);
}
