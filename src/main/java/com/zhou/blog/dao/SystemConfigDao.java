package com.zhou.blog.dao;

import com.zhou.blog.model.SystemConfigDo;

public interface SystemConfigDao {
	public int insert(SystemConfigDo systemConfigDo);
	public SystemConfigDo queryByParam(SystemConfigDo systemConfigDo);
	public int updateByUuid(SystemConfigDo systemConfigDo);
	public int deleteByUuids(String uuid);
}
