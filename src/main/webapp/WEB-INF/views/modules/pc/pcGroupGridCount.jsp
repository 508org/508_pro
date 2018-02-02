<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>集团信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function getGroup(id){
			
			form.action='${ctx}/pc/pcGroup/list?grid='+id+'&flag=hide';
			form.submit();
		}
		function getManager(id){
			form.action='${ctx}/pc/pcGroup/count?grid='+id;
			form.submit();
		}
		/* function getOrgCount(id){
			form.action='${ctx}/pc/pcGroup/count?flag=${flag}&org=${org}&grid=${grid}';
			form.submit();
		} */
	</script>
<body>
    
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">ABC类集团统计</a></li>
	</ul>
	<form:form name="form" id="searchForm" modelAttribute="pcGroup" action="${ctx}/pc/pcGroup/count" method="post" class="breadcrumb form-search">
		<ul class="ul-form" style="display:none">
			<li><label>客户级别：</label>
				<form:select path="customerLevel" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('customer_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">一级行业类别：</label>
				<form:select path="firstIndustryCategory" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('first_industry_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">二级行业类别：</label>
				<form:input path="secondIndustryCategory" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>企业类型：</label>
				<form:select path="enterpriseType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('enterprise_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>注册资金：</label>
				<form:input path="registeredCapital" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="registeredCapitalTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>税收：</label>
				<form:input path="taxRevenue" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;-&nbsp;
				<form:input path="taxRevenueTo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>员工数量：</label>
				<form:input path="phoneUsageEmployeesNumber" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="phoneUsageEmployeesNumberTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>市场占有率：</label>
				<form:input path="marketShare" htmlEscape="false" maxlength="50" class="input-mini"/>%&nbsp;-&nbsp;
				<form:input path="marketShareTo" htmlEscape="false" maxlength="50" class="input-mini"/>%
			</li>
		</ul>	
		<ul class="ul-form" style="display:none">	
			<li><label  style="width:140px"><font color="red">月收入情况:</font>&nbsp;&nbsp;&nbsp;&nbsp;类型：</label>
				<form:select path="incomeType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('income_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>运营商：</label>
				<form:select path="operators" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('broadband_operators')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>月收入：</label>
				<form:input path="allMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="allMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			</ul>
		<ul class="ul-form">			
			<li class="btns"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-2);"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" >
		<thead>
		    <tr> 
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="3">归属网格</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">集团数量</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">员工数量</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">市场占有率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">专线渗透率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">IMS渗透率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="8">协议到期预警</th>
		    </tr>
		        
		    <tr>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">客户</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">专线</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">IMS</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">其他产品</th>
		       
		    </tr>
		    
			<tr>
								
				<th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="pcGroup">
			<tr>
				<td><a href="#"  onclick="javaScript:getManager('${pcGroup.attributedGrid.id}');">
					${pcGroup.attributedGrid.name}
				</a></td>
				<td><a href="#"  onclick="javaScript:getGroup('${pcGroup.attributedGrid.id}');">
					${pcGroup.groupCount}
				</a></td>
				<td>
					${pcGroup.employeesNumberCount}
				</td>
				<td>
					${pcGroup.marketShareCountOur}
				</td>
				<td>
					${pcGroup.marketShareCountOther}
				</td>
				<td>
					${pcGroup.linePermeabilityOur}
				</td>
				<td>
					${pcGroup.linePermeabilityOther}
				</td>
				<td>
					${pcGroup.imsPermeabilityOur}
				</td>
				<td>
					${pcGroup.imsPermeabilityOther}
				</td>
				<td>
					${pcGroup.customerWarningOur}
				</td>
				<td>
					${pcGroup.customerWarningOther}
				</td>
				<td>
					${pcGroup.lineWarningOur}
				</td>
				<td>
					${pcGroup.lineWarningOther}
				</td>
				<td>
					${pcGroup.imsWarningOur}
				</td>
				<td>
					${pcGroup.imsWarningOther}
				</td>
				<td>
					${pcGroup.otherWarningOur}
				</td>
				<td>
					${pcGroup.otherWarningOther}
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>