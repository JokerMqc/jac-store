/*
 * create by yph
 * Since 2018 - 2018
 */

package com.yph.entity.log;

import lombok.Data;
import java.util.Date;

@Data
public class SysLogger implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/**属性*/
	/**
	 *
	 */
	private Long id;
	/**
	 *  日志名称
	 */
	private String name;
	/**
	 *  ip地址
	 */
	private String ip;
	/**
	 *  方法名称
	 */
	private String method;
	/**
	 *  参数
	 */
	private String params;
	/**
	 *  删除标记
	 */
	private Integer flag;
	/**
	 *  创建时间
	 */
	private Date createTime;

}