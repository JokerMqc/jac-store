package com.yph.utils;

import com.yph.common.utils.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 */
public class ScheduleRunnable implements Runnable {

	/**
	 *  目标类
	 */
	private Object target;

	/**
	 *  方法
	 */
	private Method method;

	/**
	 * 参数
	 */
	private String params;

	/**
	 *  调度方法
	 * @param beanName
	 * @param methodName
	 * @param params
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	/**
	 *  线程中运行的方法
	 */
	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params);
			}else{
				method.invoke(target);
			}
		}catch (Exception e) {
			throw new RuntimeException("执行定时任务失败", e);
		}
	}

}
