<#include "/java_copyright.include"> 
<#include "/macro.include">
<#assign className = table.className>
<#assign sqlName = table.sqlName>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.mapper;

import com.yph.common.base.BaseMapper;
import ${basepackage}.entity.${className};

/**
 * 持久化接口
 */
public interface ${className}Mapper  extends BaseMapper<${className}>{


}