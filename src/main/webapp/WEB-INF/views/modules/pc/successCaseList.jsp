<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成功案例管理</title>
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
		function funComment(id){//评论
		
			var html = "<div style='padding:10px;'>评论信息：<input type='text'  name='comment' /></div>";
			var submit = function (v, h, f) {
			    if (f.comment == '') {
			    	top.$.jBox.tip("请输入评论信息!!!", 'error', { focusId: "comment" }); // 关闭设置 bchyy 为焦点
			        return false;
			    }
			   	
			    			    
			    $("#searchForm").attr("action","${ctx}/pc/successCase/saveRecord?id="+id+"&likeFlag=3&comment="+f.comment);
				$("#searchForm").submit();
				
			    return true;
			};
			top.$.jBox(html, { title: "评论信息", submit: submit});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pc/successCase/${successCase.self?'self':''}">成功案例列表</a></li>
		<c:if test="${!successCase.self}"><shiro:hasPermission name="pc:successCase:edit"><li><a href="${ctx}/pc/successCase/form">成功案例添加</a></li></shiro:hasPermission></c:if>
	</ul>
	<form:form  id="searchForm" modelAttribute="successCase" action="${ctx}/pc/successCase/${successCase.self?'self':''}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>行业类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('first_industry_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!--<c:if test="${!requestScope.successCase.self}"><li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li></c:if>  -->
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>行业类别</th>
				<!-- <th>状态</th> -->
				<th>查阅记录(条)</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="successCase">
			<tr>
				<td><a href="${ctx}/pc/successCase/${requestScope.successCase.self?'view':'form'}?id=${successCase.id}">
					${fns:abbr(successCase.title,50)}
				</a></td>
				<td>
					${fns:getDictLabel(successCase.type, 'first_industry_category', '')}
				</td>
				<!-- 
				<td>
					${fns:getDictLabel(successCase.status, 'oa_notify_status', '')}
				</td>
				 -->
				<td>
					${successCase.readNum}
				</td>
				<td>
					<fmt:formatDate value="${successCase.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					
					<c:if test="${successCase.createBy.id == fns:getUser().id}">
						<shiro:hasPermission name="pc:successCase:edit">
							
		    				<a href="${ctx}/pc/successCase/form?id=${successCase.id}">修改</a>
							<a href="${ctx}/pc/successCase/delete?id=${successCase.id}" onclick="return confirmx('确认要删除该成功案例吗？', this.href)">删除</a>
							<!-- 
							<a href="${ctx}/pc/successCase/saveRecord?id=${successCase.id}&likeFlag=1" onclick="return confirmx('确认要为案例点赞吗？', this.href)">点赞${successCase.likeFlag}</a>
							<a href="javascript:;" onclick ="funComment('${successCase.id}')">评论</a>
							 -->
						</shiro:hasPermission>
					</c:if>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>