<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>集团信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pc/pcGroup/">集团信息列表</a></li>
		<li class="active"><a href="${ctx}/pc/pcGroup/form?id=${pcGroup.id}">集团信息<shiro:hasPermission name="pc:pcGroup:edit">${not empty pcGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pcGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pcGroup" action="${ctx}/pc/pcGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<input id="display" name="display" type="hidden" value="${display}"/>
		
			
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<sys:treeselect id="organization" name="organization.id" value="${pcGroup.organization.id}" labelName="organization.name" labelValue="${pcGroup.organization.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集团编号：</label>
			<div class="controls">
				<form:input path="groupNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集团名称：</label>
			<div class="controls">
				<form:input path="groupName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		
		<div style="background: #FFF5EE;">
		
		<fieldset >
		<legend>基本信息：</legend>
		
		<div class="control-group">
			<label class="control-label">营业状态:</label>
			<div class="controls">
				<form:select path="groupState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">客户级别：</label>
			<div class="controls">
				<form:select path="customerLevel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('customer_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行业类别：</label>
			<div class="controls">
				<form:select path="firstIndustryCategory" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('first_industry_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级单位名称:</label>
			<div class="controls">
				<form:select path="orgParentName" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_parent_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属网格：</label>
			<div class="controls">
				<%-- <form:input path="attributedGrid" htmlEscape="false" maxlength="50" class="input-xlarge "/> --%>
				<sys:treeselect id="attributedGrid" name="attributedGrid.id" value="${pcGroup.attributedGrid.id}" labelName="attributedGrid.name" labelValue="${pcGroup.attributedGrid.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业类型：</label>
			<div class="controls">
				<form:select path="enterpriseType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('enterprise_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位实际地址：</label>
			<div class="controls">
				<form:input path="unitActualAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证件号码：</label>
			<div class="controls">
				<form:input path="identificationNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册资金：</label>
			<div class="controls">
				<form:input path="registeredCapital" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税收：</label>
			<div class="controls">
				<form:input path="taxRevenue" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一把手姓名：</label>
			<div class="controls">
				<form:input path="numberOneName" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一把手电话：</label>
			<div class="controls">
				<form:input path="numberOnePhone" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分管信息关键人姓名：</label>
			<div class="controls">
				<form:input path="chargeKeyPersonName" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分管信息关键人手机号码：</label>
			<div class="controls">
				<form:input path="chargeKeyPersonPhone" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信息科关键人姓名：</label>
			<div class="controls">
				<form:input path="informationKeyPersonName" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信息科关键人手机号码：</label>
			<div class="controls">
				<form:input path="informationKeyPersonPhone" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="contactsName" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人手机号码：</label>
			<div class="controls">
				<form:input path="contactsPhone" htmlEscape="false" maxlength="50" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">其他关键人姓名：</label>
			<div class="controls">
				<form:input path="otherKeyPersonName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他关键人手机号码：</label>
			<div class="controls">
				<form:input path="otherKeyPersonPhone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		</div>
		
		<div style="background: #F0FFF0;">
		<fieldset style="border-width: 1px; border-color: #008000; width:95%; height:220">
		<legend>使用手机情况：</legend>
		<div class="control-group">
			<label class="control-label">员工数量：</label>
			<div class="controls">
				<form:input path="phoneUsageEmployeesNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">号码数量：</label>
			<div class="controls">
				<form:input path="phoneUsageMobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="phoneUsageMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">我网协议到期时间：</label>
			<div class="controls">
				<input name="phoneUsageMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.phoneUsageMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一付费成员数量 ：</label>
			<div class="controls">
				<form:input path="unifiedPaymentNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一付费收入（元）：</label>
			<div class="controls">
				<form:input path="unifiedPaymentIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">号码数量：</label>
			<div class="controls">
				<form:input path="phoneUsageUnicomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="phoneUsageUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联通协议到期时间：</label>
			<div class="controls">
				<input name="phoneUsageUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.phoneUsageUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">号码数量：</label>
			<div class="controls">
				<form:input path="phoneUsageTelecomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="phoneUsageTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电信协议到期时间：</label>
			<div class="controls">
				<input name="phoneUsageTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.phoneUsageTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		
		<div style="background: #FFF5EE;">
		<fieldset>
		<legend>数据专线：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="dataLineMobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="dataLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="dataLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.dataLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="dataLineUnicomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="dataLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="dataLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.dataLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="dataLineTelecomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="dataLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="dataLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.dataLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		
		<div style="background: #F0FFF0;">
		<fieldset>
		<legend>互联网专线：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="internetLineMobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="internetLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="internetLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.internetLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="internetLineUnicomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="internetLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="internetLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.internetLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="internetLineTelecomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="internetLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="internetLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.internetLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		
		<div style="background: #FFF5EE;">
		<fieldset>
		<legend>语音专线：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="voiceLineMobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="voiceLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="voiceLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.voiceLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="voiceLineUnicomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="voiceLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="voiceLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.voiceLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">条数：</label>
			<div class="controls">
				<form:input path="voiceLineTelecomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="voiceLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="voiceLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.voiceLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
	    </div>
		
		<div style="background: #F0FFF0;">
		<fieldset>
		<legend>使用IMS情况：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">门数：</label>
			<div class="controls">
				<form:input path="imsMobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="imsMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="imsMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.imsMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">门数：</label>
			<div class="controls">
				<form:input path="imsUnicomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="imsUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="imsUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.imsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">门数：</label>
			<div class="controls">
				<form:input path="imsTelecomNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="imsTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="imsTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.imsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		
		
		<div style="background: #FFF5EE;">
		<fieldset>
		<legend>物联网卡：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="wlwkYdBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="wlwkYdIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="wlwkYdDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.wlwkYdDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="wlwkLtBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="wlwkLtIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="wlwkLtDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.wlwkLtDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="wlwkDxBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="wlwkDxIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="wlwkDxDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.wlwkDxDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		<div style="background: #F0FFF0;">
		<fieldset>
		<legend>IDC业务使用情况：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="iDCYdBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资费：</label>
			<div class="controls">
				<form:input path="iDCYdZf" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="iDCYdDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.iDCYdDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="iDCLtBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资费：</label>
			<div class="controls">
				<form:input path="iDCLtZf" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="iDCLtDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.iDCLtDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>其他单位（如电信、广电、华为、英特力等）：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="iDCQtBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IDC提供单位：</label>
			<div class="controls">
				<form:input path="iDCQtAddr" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资费：</label>
			<div class="controls">
				<form:input path="iDCQtZf" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="iDCQtDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.iDCQtDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		 
		
		<div style="background: #FFF5EE;">
		<fieldset>
		<legend>其他信息话产品使用情况：</legend>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="otherProductsMobileBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="otherProductsMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="otherProductsMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.otherProductsMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联通：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="otherProductsUnicomBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="otherProductsUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="otherProductsUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.otherProductsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信：</legend>
		<div class="control-group">
			<label class="control-label">业务名称：</label>
			<div class="controls">
				<form:input path="otherProductsTelecomBusinessName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月收入：</label>
			<div class="controls">
				<form:input path="otherProductsTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">到期时间：</label>
			<div class="controls">
				<input name="otherProductsTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcGroup.otherProductsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		</div>
		
		<div class="control-group"></div>
		<div class="control-group">
			<label class="control-label">正在跟进或将来要跟进的业务名称：</label>
			<div class="controls">
				<form:textarea path="followUpBusinessName" htmlEscape="false" rows="4" maxlength="50" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体跟进情况：</label>
			<div class="controls">
				<form:textarea path="followUpSituation" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">客户经理：</label>
			<div class="controls">
				<sys:treeselect id="customerManagerNumber" name="customerManagerNumber.id" value="${pcGroup.customerManagerNumber.id}" labelName="customerManagerNumber.name" labelValue="${pcGroup.customerManagerNumber.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">客户经理工号：</label>
			<div class="controls">
				<form:input path="customerManagerNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户经理姓名：</label>
			<div class="controls">
				<form:input path="customerManagerName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<c:if test="${flag!='hide'}"><shiro:hasPermission name="pc:pcGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission></c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>