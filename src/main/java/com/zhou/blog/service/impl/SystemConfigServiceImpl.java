package com.zhou.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.blog.dao.SystemConfigDao;
import com.zhou.blog.model.SystemConfigDo;
import com.zhou.blog.service.SystemConfigService;

@Service
public class SystemConfigServiceImpl implements SystemConfigService{
	
	@Autowired
	private SystemConfigDao systemConfigDao;

	public int add(SystemConfigDo systemConfigDo) {
		
		return systemConfigDao.insert(systemConfigDo);
	}

	public SystemConfigDo queryByParam(SystemConfigDo systemConfigDo) {
		// TODO Auto-generated method stub
		return systemConfigDao.queryByParam(systemConfigDo);
	}

	public int update(SystemConfigDo systemConfigDo) {
		// TODO Auto-generated method stub
		return systemConfigDao.updateByUuid(systemConfigDo);
	}

	public int delete(List<String> uuids) {
		// TODO Auto-generated method stub
		for(String uuid :uuids){
			systemConfigDao.deleteByUuids(uuid);
		}
		return 0;
	}

}
