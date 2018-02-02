<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<%
	String id = request.getParameter("id");	
		
%>
<html>
<head>
<meta charset="utf-8">
<title>客户资源管理系统</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!--标准mui.css-->
<link rel="stylesheet" href="${ctxStatic }/mui-master/dist/css/mui.css">

<script src="${ctxStatic}/mui-master/dist/js/mui.min.js"></script>
<link rel="stylesheet" href="${ctxStatic }/mui-master/dist/css/mui.min.css" />
	<script type="text/javascript"
			src="${ctxStatic}/jingle/js/lib/zepto.js"></script>
		<script src="${ctxStatic}/mui-master/dist/js/mui.js"></script>
		<style type="text/css">
		 p{border:1px solid gray;
		} 
		 span{color: #6d6d72;
		font-size:17px;
		margin-left:20px
		} 
		
		</style>
<script type="text/javascript">
mui.init({
	swipeBack:true //启用右滑关闭功能
});
$(document).ready(
		function() {
		
		});
		


</script>
</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<a class="mui-icon mui-icon-left-nav mui-pull-left"
			onclick="javascript:history.go(-1);"></a>
		<h1 class="mui-title">集团详细信息</h1>
	</header>
		<form id="inputForm" modelAttribute="pcGroup"
		
		method="post" class="form-horizontal">
		<input  name="id" value="<%=id %>" type="text"/>
	<div class="mui-content mui-btn-block">
		<div id="maincontent" class="mui-control-content mui-active ">
			<div class="mui-content-padded" style="margin: -20px 5px;">
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">区县:</label><span>${pcGroup.organization.name}</span><br> 
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">集团编号:</label><span>${pcGroup.groupNumber}</span><br> 
				</div>
				<div class="mui-table-view-cell" >
				<label class="weui-form-preview__label">集团名称:</label><span>${pcGroup.groupName}</span><br>
				</div>
				<div class="mui-table-view-cell" >
				<label class="weui-form-preview__label">营业状态:</label><span>${pcGroup.groupState}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">客户级别:</label><span>${fns:getDictLabel(pcGroup.customerLevel, 'customer_level', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">行业类别:</label><span>${fns:getDictLabel(pcGroup.firstIndustryCategory, 'first_industry_category', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">上级单位名称:</label><span>${pcGroup.orgParentName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">归属网格:</label><span>${pcGroup.attributedGrid.name}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">企业类型:</label><span>${fns:getDictLabel(pcGroup.enterpriseType, 'enterprise_type','')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">单位实际地址:</label><span>${pcGroup.unitActualAddress}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">证件号码:</label><span>${pcGroup.identificationNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">注册资金:</label><span>${pcGroup.registeredCapital}</span><br>
				</div>				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">税收:</label><span>${pcGroup.taxRevenue}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">一把手姓名:</label><span>${pcGroup.numberOneName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">一把手电话:</label><span>${pcGroup.numberOnePhone}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">分管信息关键人姓名:</label><span>${pcGroup.chargeKeyPersonName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">分管信息关键人手机号码:</label><span>${pcGroup.chargeKeyPersonPhone}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">信息科关键人姓名:</label><span>${pcGroup.informationKeyPersonName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">信息科关键人手机号码:</label><span>${pcGroup.informationKeyPersonPhone}</span><br>
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联系人姓名:</label><span>${pcGroup.contactsName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联系人手机号码:</label><span>${pcGroup.contactsPhone}</span><br>
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">其他关键人姓名:</label><span>${pcGroup.otherKeyPersonName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">其他关键人手机号码:</label><span>${pcGroup.otherKeyPersonPhone}</span><br>
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">使用手机情况:</label><br>
				</div>			
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">员工数量:</label><span>${pcGroup.phoneUsageEmployeesNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">号码数量:</label><span>${pcGroup.phoneUsageMobileNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.phoneUsageMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">我网协议到期时间:</label><span><fmt:formatDate value="${pcGroup.phoneUsageMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">统一付费成员数量 :</label><span>${pcGroup.unifiedPaymentNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">统一付费收入（元） :</label><span>${pcGroup.unifiedPaymentIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">号码数量:</label><span>${pcGroup.phoneUsageUnicomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.phoneUsageUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通协议到期时间:</label><span><fmt:formatDate value="${pcGroup.phoneUsageUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">号码数量:</label><span>${pcGroup.phoneUsageTelecomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.phoneUsageTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信协议到期时间:</label><span><fmt:formatDate value="${pcGroup.phoneUsageTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">数据专线:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.dataLineMobileNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.dataLineMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.dataLineMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.dataLineUnicomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.dataLineUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.dataLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.dataLineTelecomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.dataLineTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.dataLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">互联网专线:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.internetLineMobileNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.internetLineMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.internetLineMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.internetLineUnicomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.internetLineUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.internetLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.internetLineTelecomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.internetLineTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.internetLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">语音专线:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.voiceLineMobileNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.voiceLineMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.voiceLineMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.voiceLineUnicomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.voiceLineUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.voiceLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">条数:</label><span>${pcGroup.voiceLineTelecomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.voiceLineTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.voiceLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">使用IMS情况:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">门数:</label><span>${pcGroup.imsMobileNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.imsMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.imsMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">门数:</label><span>${pcGroup.imsUnicomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.imsUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.imsUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">门数:</label><span>${pcGroup.imsTelecomNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.imsTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.imsTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">其他信息化产品使用情况:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">业务名称:</label><span>${pcGroup.otherProductsMobileBusinessName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.otherProductsMobileMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.otherProductsMobileExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联通:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">业务名称:</label><span>${pcGroup.otherProductsUnicomBusinessName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.otherProductsUnicomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.otherProductsUnicomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">电信:</label><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">业务名称:</label><span>${pcGroup.otherProductsTelecomBusinessName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">月收入:</label><span>${pcGroup.otherProductsTelecomMonthlyIncome}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">到期时间:</label><span><fmt:formatDate value="${pcGroup.otherProductsTelecomExpirationDate}" pattern="yyyy-MM-dd"/></span><br>				
				</div>
				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">正在跟进或将来要跟进的业务名称:</label><span>${pcGroup.followUpBusinessName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">具体跟进情况:</label><span>${pcGroup.followUpSituation}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">客户经理工号:</label><span>${pcGroup.customerManagerNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">客户经理姓名:</label><span>${pcGroup.customerManagerName}</span><br>
				</div>
				
				
		</div>
	</div>
		
		<div class="mui-button-row">
		<button id='confirmBtn' type="button" class="mui-btn mui-btn-blue mui-btn-outlined" onclick="history.back()">返回</button>
			</div>
		
	</div>
</form>
</body>

<script>
	
	
</script>
</html>