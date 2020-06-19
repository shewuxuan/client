package com.guodu.mapper.sys;

import com.guodu.mapper.BaseMapper;
import com.guodu.pojo.sys.RoleInfo;

import java.util.List;
import java.util.Map;

public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
    List<Map<String, Object>> getRoleByFunc(String roleId);

    List<Map<String, Object>> getAllFunc();

    void addRoleFuncByRoleId(Map<String, Object> map);

    void updateRoleFuncByRoleId(Map<String, Object> map);

    void deleteFuncInfoByRoleId(String roleId);
}
