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
		<h1 class="mui-title">聚类市场信息</h1>
	</header>
		<form id="inputForm" modelAttribute="pcClusteringMarket"
		
		method="post" class="form-horizontal">
		<input  name="id" value="<%=id %>" type="text"/>
	<div class="mui-content mui-btn-block">
		<div id="maincontent" class="mui-control-content mui-active ">
			<div class="mui-content-padded" style="margin: -20px 5px;">
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">区县:</label><span>${pcClusteringMarket.organization.name}</span><br> 
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">归属网格:</label><span>${pcClusteringMarket.attributedGrid.name}</span><br> 
				</div>
				<div class="mui-table-view-cell" >
				<label class="weui-form-preview__label">聚类市场类型:</label><span>${fns:getDictLabel(pcClusteringMarket.clusterMarketType, 'cluster_market_type', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">聚类市场名称:</label><span>${pcClusteringMarket.clusterMarketName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">集团编号:</label><span>${pcClusteringMarket.groupNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">店铺名称:</label><span>${pcClusteringMarket.shopName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">地址（街道、门牌号）:</label><span>${pcClusteringMarket.address}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联系人姓名:</label><span>${pcClusteringMarket.contactName}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">联系人手机号码:</label><span>${pcClusteringMarket.contactPhone}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">员工数量:</label><span>${pcClusteringMarket.employeesNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">移动数量:</label><span>${pcClusteringMarket.mobileNumber}</span><br>
				</div>				
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">他网数量:</label><span>${pcClusteringMarket.otherNumber}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">是否覆盖移动网络:</label><span>${fns:getDictLabel(pcClusteringMarket.isOverlayMobileNetwork, 'is_or_no', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">是否使用宽带:</label><span>${fns:getDictLabel(pcClusteringMarket.isUseBroadband, 'is_or_no', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">宽带运营商:</label><span>${fns:getDictLabel(pcClusteringMarket.broadbandOperators, 'broadband_operators', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">宽带到期时间:</label><span><fmt:formatDate value="${pcClusteringMarket.broadbandExpirationDate}" pattern="yyyy-MM-dd"/></span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">是否使用固话:</label><span>${fns:getDictLabel(pcClusteringMarket.isUseTelephone, 'is_or_no', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">固话运营商:</label><span>${fns:getDictLabel(pcClusteringMarket.telephoneOperators, 'telephone_operators', '')}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">固话到期时间:</label><span><fmt:formatDate value="${pcClusteringMarket.telephoneExpirationDate}" pattern="yyyy-MM-dd"/></span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">其他产品使用情况:</label><span>${pcClusteringMarket.otherProductUsage}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">客户需求:</label><span>${pcClusteringMarket.customerDemand}</span><br>
				</div>
				<div class="mui-table-view-cell">
				<label class="weui-form-preview__label">客户经理:</label><span>${pcClusteringMarket.customerManagerNumber.name}</span><br>
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