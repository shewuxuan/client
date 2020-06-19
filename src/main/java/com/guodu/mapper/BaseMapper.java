package com.guodu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseMapper<T> {
    T selectById(String id);
    List<T> selectList(Map<String, Object> map);
    List<Map<String, Object>> selectListAll();
    Integer insert(T t);
    Integer update(T t);
    Integer delete(@Param(value = "id") String id);
    Page<?> selectPage(Map<String, Object> map);
    List<Map<String,Object>> queryAllRoleInfo();
}
