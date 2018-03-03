<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.domain;

import lombok.Data;
import java.util.Date;

@Data
public class ${className} implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	<@generateFields/>

}