<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="<@jspEl 'ctx'/>/pages/${modulename}/${className}.js"></script>
<script language="javascript" src="<@jspEl 'ctx'/>/common/js/Common.js"></script>
<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="<@jspEl 'operateType'/>"/>
	<form name="fm" id="fm" action="/${modulename}/${classNameLowerCase}" namespace="/${modulename}/${classNameLowerCase}" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<@generateEditTitle/>
					</h2></td>
			</tr>
			<tr>
				<#assign field_index=0 />
				<#list table.columns as column>
			      	    <#if (field_index==0)>
							<tr>
								<@generateEditForm column/>
								<#assign field_index=field_index+1 />
						<#elseif ((field_index%2)!=0)&&(column_has_next)>
							   <@generateEditForm column/>
							   <#assign field_index=field_index+1 />
						<#elseif ((field_index%2)==0)&&(column_has_next)>
							</tr>
							<tr>
								<@generateEditForm column/>
								<#assign field_index=field_index+1 />
						<#elseif ((field_index%2)==0)&&(!column_has_next)>
							</tr>
							<tr>
								<@generateEditForm column/>
								<#assign field_index=field_index+1 />
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr>	
						<#elseif ((field_index%2)!=0)&&(!column_has_next)&&((field_index%2)==1)>
								<@generateEditForm column/>
								<#assign field_index=field_index+1 />
							 </tr> 				 
						</#if>								
			      </#list>
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
