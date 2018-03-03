<#include "/java_copyright.include"> 
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.controller;

import com.github.pagehelper.PageInfo;
import com.yph.common.base.BaseController ;
import com.yph.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Administrator Hzhan
 * @create ：2018/1/29
 **/
@RestController
@RequestMapping("${classNameLower}")
public class ${className}Controller  extends BaseController {

	@Autowired
	private I${className}Service ${classNameLower}Service;

	@Override
	public IBaseService getBaseService() {
		return ${classNameLower}Service;
	}




}

