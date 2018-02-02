<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>聚类市场信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
		
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function getGrid(id){
			form.action='${ctx}/pc/pcClusteringMarket/count?org='+id;
			form.submit();
		}
		function getGroup(id){
			form.action='${ctx}/pc/pcClusteringMarket/list?org='+id+'&flag=hide';
			form.submit();
		}
	</script>
</head>
<body>
     
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">聚类市场统计</a></li>
	</ul>
	<form:form name="form" id="searchForm" modelAttribute="pcClusteringMarket" action="${ctx}/pc/pcClusteringMarket/count" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label style="width:120px">聚类市场类型：</label>
				<form:select path="clusterMarketType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cluster_market_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>员工数量：</label>
				<form:input path="employeesNumber" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="employeesNumberTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>市场占有率：</label>
				<form:input path="marketShare" htmlEscape="false" maxlength="50" class="input-mini"/>%&nbsp;-&nbsp;
				<form:input path="marketShareTo" htmlEscape="false" maxlength="50" class="input-mini"/>%
			</li>
			
			<li><label style="width:120px">是否覆盖移动网路：</label>
				<form:select path="isOverlayMobileNetwork" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">是否使用宽带：</label>
				<form:select path="isUseBroadband" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label style="width:120px">是否使用固话：</label>
				<form:select path="isUseTelephone" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		    <tr> 
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="3">区县</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">集团数量</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">员工数量</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">市场占有率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">宽带渗透率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="2">固话渗透率</th>
				<th style="vertical-align: middle !important;text-align: center;" colspan="4">协议到期预警</th>
		    </tr>
		        
		    <tr>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">他网</th>
		        
		       <!--  <th style="vertical-align: middle !important;text-align: center;" colspan="2">客户</th> -->
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">宽带</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="2">固话</th>
		       
		    </tr>
		    
			<tr>
								
				<th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th>
		        
		       <!--  <th style="vertical-align: middle !important;text-align: center;">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;">他网</th> -->
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="pcClusteringMarket">
			<tr>
				<td><c:if test="${pcClusteringMarket.organization.id!='1'}"><a href="#"  onclick="javaScript:getGrid('${pcClusteringMarket.organization.id}');">
				<%-- <a href="${ctx}/pc/pcClusteringMarket/form?id=${pcClusteringMarket.id}"> --%>
					${pcClusteringMarket.organization.name}
				</a></c:if>
				<c:if test="${pcClusteringMarket.organization.id=='1'}">${pcClusteringMarket.organization.name}</c:if>
				</td>				
				<td><a href="#"  onclick="javaScript:getGroup('${pcClusteringMarket.organization.id}');">
					${pcClusteringMarket.groupCount}
				</a></td>
				<td>
					${pcClusteringMarket.employeesNumberCount}
				</td>
				<td>
					${pcClusteringMarket.marketShareCountOur}
				</td>
				<td>
					${pcClusteringMarket.marketShareCountOther}
				</td>
				
				<td>
					${pcClusteringMarket.broadbandPermeabilityOur}
				</td>
				<td>
					${pcClusteringMarket.broadbandPermeabilityOther}
				</td>
				<td>
					${pcClusteringMarket.telPermeabilityOur}
				</td>
				
				<td>
					${pcClusteringMarket.telPermeabilityOther}
				</td>
				<td>
					${pcClusteringMarket.broadbandWarningOur}
				</td>
				<td>
					${pcClusteringMarket.broadbandWarningOther}
				</td>
				<td>
					${pcClusteringMarket.telWarningOur}
				</td>
				<td>
					${pcClusteringMarket.telWarningOther}
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>