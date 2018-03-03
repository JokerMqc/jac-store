<#include "/java_copyright.include"> 
<#include "/macro.include">
<#assign className = table.className>
<#assign sqlName = table.sqlName>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.impl;

import com.yph.common.annotation.RedisCache;
import com.yph.common.annotation.RedisEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yph.common.base.BaseMapper;
import com.yph.common.base.BaseServiceImpl;
import ${basepackage}.entity.${className};
import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.service.I${className}Service;

/**
 * ${table.remarks}业务逻辑实现
 * @author ${author}
 */
@Slf4j
@Service
@RedisEvict(type = ${className}.class)
@RedisCache(type = ${className}.class)
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements I${className}Service{

	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;

	@Override
	public BaseMapper<${className}> getBaseMapper() {
		return ${classNameLower}Mapper;
	}


}
