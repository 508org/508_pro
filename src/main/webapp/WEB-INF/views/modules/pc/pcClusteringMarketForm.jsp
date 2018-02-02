<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>聚类市场信息管理</title>
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
		<li><a href="${ctx}/pc/pcClusteringMarket/">聚类市场信息列表</a></li>
		<li class="active"><a href="${ctx}/pc/pcClusteringMarket/form?id=${pcClusteringMarket.id}">聚类市场信息<shiro:hasPermission name="pc:pcClusteringMarket:edit">${not empty pcClusteringMarket.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:pcClusteringMarket:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pcClusteringMarket" action="${ctx}/pc/pcClusteringMarket/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<input id="display" name="display" type="hidden" value="${display}"/>	
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<sys:treeselect id="organization" name="organization.id" value="${pcClusteringMarket.organization.id}" labelName="organization.name" labelValue="${pcClusteringMarket.organization.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属网格：</label>
			<div class="controls">
				<%-- <form:input path="attributedGrid" htmlEscape="false" maxlength="50" class="input-xlarge "/> --%>
				<sys:treeselect id="attributedGrid" name="attributedGrid.id" value="${pcClusteringMarket.attributedGrid.id}" labelName="attributedGrid.name" labelValue="${pcClusteringMarket.attributedGrid.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">聚类市场类型：</label>
			<div class="controls">
				<form:select path="clusterMarketType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cluster_market_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">聚类市场名称：</label>
			<div class="controls">
				<form:input path="clusterMarketName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集团编号：</label>
			<div class="controls">
				<form:input path="groupNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址（街道、门牌号）：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人手机号码：</label>
			<div class="controls">
				<form:input path="contactPhone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工数量：</label>
			<div class="controls">
				<form:input path="employeesNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">移动数量：</label>
			<div class="controls">
				<form:input path="mobileNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">他网数量：</label>
			<div class="controls">
				<form:input path="otherNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否覆盖移动网路：</label>
			<div class="controls">
				<form:select path="isOverlayMobileNetwork" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否使用宽带：</label>
			<div class="controls">
				<form:select path="isUseBroadband" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽带运营商：</label>
			<div class="controls">
				<form:select path="broadbandOperators" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('broadband_operators')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽带到期时间：</label>
			<div class="controls">
				<input name="broadbandExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcClusteringMarket.broadbandExpirationDate}" pattern=" yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:' yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否使用固话：</label>
			<div class="controls">
				<form:select path="isUseTelephone" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">固话运营商：</label>
			<div class="controls">
				<form:select path="telephoneOperators" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('telephone_operators')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">固话到期时间：</label>
			<div class="controls">
				<input name="telephoneExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pcClusteringMarket.telephoneExpirationDate}" pattern=" yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:' yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他产品使用情况：</label>
			<div class="controls">
				<form:textarea path="otherProductUsage" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户需求：</label>
			<div class="controls">
				<form:textarea path="customerDemand" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户经理：</label>
			<div class="controls">
				<sys:treeselect id="customerManagerNumber" name="customerManagerNumber.id" value="${pcClusteringMarket.customerManagerNumber.id}" labelName="customerManagerNumber.name" labelValue="${pcClusteringMarket.customerManagerNumber.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<c:if test="${flag!='hide'}"><shiro:hasPermission name="pc:pcClusteringMarket:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission></c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>