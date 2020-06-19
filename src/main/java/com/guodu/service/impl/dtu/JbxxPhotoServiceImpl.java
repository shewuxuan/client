package com.guodu.service.impl.dtu;

import com.guodu.mapper.dtu.JbxxPhotoMapper;
import com.guodu.pojo.dtu.JbxxPhoto;
import com.guodu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JbxxPhotoServiceImpl extends BaseServiceImpl<JbxxPhoto> {
	@Autowired
	JbxxPhotoMapper jbxxPhotoMapper;

	public List<Map<String,Object>> selectAllByTsid(Map<String, Object> vo){
		return jbxxPhotoMapper.selectAllByTsid(vo);
	}
}
