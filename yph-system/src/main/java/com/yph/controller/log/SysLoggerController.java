/*
 * create by yph
 * Since 2018 - 2018
 */

package com.yph.controller.log;

import com.github.pagehelper.PageInfo;
import com.yph.common.base.BaseController ;
import com.yph.common.base.IBaseService;
import com.yph.common.result.CommonResult;
import com.yph.entity.log.SysLogger;
import com.yph.service.log.ISysLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Administrator Hzhan
 * @create ：2018/1/29
 **/
@RestController
@RequestMapping("sys/log")
public class SysLoggerController  extends BaseController<SysLogger> {

	@Autowired
	private ISysLoggerService sysLoggerService;

	@Override
	public IBaseService getBaseService() {
		return sysLoggerService;
	}

}

