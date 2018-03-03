package com.yph.service.log.impl;

import com.yph.common.annotation.RedisCache;
import com.yph.common.annotation.RedisEvict;
import com.yph.entity.log.SysLogger;
import com.yph.mapper.log.SysLoggerMapper;
import com.yph.service.log.ISysLoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yph.common.base.BaseMapper;
import com.yph.common.base.BaseServiceImpl;

/**
 * 业务逻辑实现
 * @author admin
 */
@Slf4j
@Service
@RedisEvict(type = SysLogger.class)
@RedisCache(type = SysLogger.class)
public class SysLoggerServiceImpl extends BaseServiceImpl<SysLogger> implements ISysLoggerService {

	@Autowired
	private SysLoggerMapper sysLoggerMapper;

	@Override
	public BaseMapper<SysLogger> getBaseMapper() {
		return sysLoggerMapper;
	}


}
