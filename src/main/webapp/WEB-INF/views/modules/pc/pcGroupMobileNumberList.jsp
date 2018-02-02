<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ABC类集团手机号码管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pc/pcGroupMobileNumber/">ABC类集团手机号码列表</a></li>
		<shiro:hasPermission name="pc:pcGroupMobileNumber:edit"><li><a href="${ctx}/pc/pcGroupMobileNumber/form">ABC类集团手机号码添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pcGroupMobileNumber" action="${ctx}/pc/pcGroupMobileNumber/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号码：</label>
				<form:input path="mobileNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>运营商：</label>
				<form:input path="operators" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>集团编号：</label>
				<form:input path="groupNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>手机号码</th>
				<th>运营商</th>
				<th>集团编号</th>
				<shiro:hasPermission name="pc:pcGroupMobileNumber:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pcGroupMobileNumber">
			<tr>
				<td><a href="${ctx}/pc/pcGroupMobileNumber/form?id=${pcGroupMobileNumber.id}">
					${pcGroupMobileNumber.mobileNumber}
				</a></td>
				<td>
					${pcGroupMobileNumber.operators}
				</td>
				<td>
					${pcGroupMobileNumber.groupNumber}
				</td>
				<shiro:hasPermission name="pc:pcGroupMobileNumber:edit"><td>
    				<a href="${ctx}/pc/pcGroupMobileNumber/form?id=${pcGroupMobileNumber.id}">修改</a>
					<a href="${ctx}/pc/pcGroupMobileNumber/delete?id=${pcGroupMobileNumber.id}" onclick="return confirmx('确认要删除该ABC类集团手机号码吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>