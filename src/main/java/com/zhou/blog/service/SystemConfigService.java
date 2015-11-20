package com.zhou.blog.service;

import java.util.List;

import com.zhou.blog.model.SystemConfigDo;

public interface SystemConfigService {
	public int add(SystemConfigDo systemConfigDo);
	public SystemConfigDo queryByParam(SystemConfigDo systemConfigDo);
	public int update(SystemConfigDo systemConfigDo);
	public int delete(List<String> uuids);
}
