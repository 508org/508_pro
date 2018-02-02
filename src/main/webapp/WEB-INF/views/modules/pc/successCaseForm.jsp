<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成功案例管理</title>
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
		
		function funComment(id){//评论
			
			var html = "<div style='padding:10px;'><input style='width: 350px' type='text'  name='comment' /></div>";
			var submit = function (v, h, f) {
			    if (f.comment == '') {
			    	top.$.jBox.tip("请输入评论信息!!!", 'error', { focusId: "comment" }); // 关闭设置 bchyy 为焦点
			        return false;
			    }
			   	
			    			    
			    $("#inputForm").attr("action","${ctx}/pc/successCase/saveRecord?likeFlag=3&comment="+f.comment);
			    console.info("${ctx}/pc/successCase/saveRecord?likeFlag=3&comment="+f.comment);
				$("#inputForm").submit();
				
			    return true;
			};
			top.$.jBox(html, { title: "评论信息",width: 400,bottomText: "注：如果之前已评论过，则会覆盖掉之前的评论信息！", submit: submit});
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pc/successCase/">成功案例列表</a></li>
		<li class="active"><a href="${ctx}/pc/successCase/form?id=${successCase.id}">成功案例<shiro:hasPermission name="pc:successCase:edit">${successCase.status eq '1' ? '查看' : not empty successCase.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission name="pc:successCase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="successCase" action="${ctx}/pc/successCase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">行业类别：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('first_industry_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200"  class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${successCase.status ne '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					
				<c:if test="${null == successCase.id || successCase.self}">
					<sys:ckfinder input="files" type="files" uploadPath="/pc/successCase" selectMultiple="true" />
				</c:if>
				<c:if test="${null != successCase.id && !successCase.self}">
					<sys:ckfinder input="files" type="files" uploadPath="/pc/successCase" selectMultiple="true" readonly="true" />
				</c:if>
					
				</div>
			</div>
			
			<!-- 
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> 发布后不能进行操作。</span>
				</div>
			</div>
			 -->
			
			<c:if test="${null != successCase.id }">
						
						<div class="control-group">
							<label class="control-label">上传人：</label>
							<div class="controls">
								${successCase.createByName}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上传单位：</label>
							<div class="controls">
								${successCase.createByOfficeName}
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">查看记录：</label>
							<div class="controls">
								<table id="contentTable" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>查看人</th>
											<th>部门</th>
										<!-- 	<th>阅读状态</th> -->
											<th>查看时间</th>
											<th>点赞信息</th>
											<th  width="40%">评论信息</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${successCase.successCaseRecordList}" var="successCaseRecord">
										<tr>
											<td>
												${successCaseRecord.user.name}
											</td>
											<td>
												${successCaseRecord.user.office.name}
											</td>
											<!-- 
											<td>
												${fns:getDictLabel(successCaseRecord.readFlag, 'oa_notify_read', '')}
											</td>
											-->
											<td>
												<fmt:formatDate value="${successCaseRecord.readDate}" pattern="yyyy-MM-dd"/>
											</td>
											<td>
												${successCaseRecord.likeFlag eq '1' ? '已点赞' : ''}
											</td>
											<td>
												${successCaseRecord.comment}
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								已查阅：${successCase.readNum}
							</div>
						</div>
			</c:if>
			
		</c:if>
		<c:if test="${successCase.status eq '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/pc/successCase" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>接受人</th>
								<th>接受部门</th>
								<!-- 	<th>阅读状态</th> -->
								<th>查看时间</th>
								<th>点赞信息</th>
								<th  width="40%">评论信息</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${successCase.successCaseRecordList}" var="successCaseRecord">
							<tr>
								<td>
									${successCaseRecord.user.name}
								</td>
								<td>
									${successCaseRecord.user.office.name}
								</td>
								<!-- 
								<td>
									${fns:getDictLabel(successCaseRecord.readFlag, 'oa_notify_read', '')}
								</td>
								-->
								<td>
									<fmt:formatDate value="${successCaseRecord.readDate}" pattern="yyyy-MM-dd"/>
								</td>
								
								<td>
									${successCaseRecord.likeFlag}
								</td>
								<td>
									${successCaseRecord.comment}
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					已查阅：${successCase.readNum} &nbsp; 未查阅：${successCase.unReadNum} &nbsp; 总共：${successCase.readNum + successCase.unReadNum}
				</div>
			</div>
		</c:if>
		<div class="form-actions" >
			
			<c:if test="${null == successCase.id || successCase.self}">
				<shiro:hasPermission name="pc:successCase:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<c:if test="${null != successCase.id && !successCase.self}">
				<c:if test="${successCase.likeFlag ne '1'}">
					<a class="btn btn-primary" href="${ctx}/pc/successCase/saveRecord?id=${successCase.id}&likeFlag=1" onclick="return confirmx('确认要为案例点赞吗？', this.href)">点赞</a>
				</c:if>
				<c:if test="${successCase.likeFlag eq '1'}">
					<a class="btn btn-primary" href="${ctx}/pc/successCase/saveRecord?id=${successCase.id}&likeFlag=2" onclick="return confirmx('确认要为案例取消赞吗？', this.href)">取消赞</a>
				</c:if>
				
				<input id="btnSubmit" class="btn btn-primary" type="button" value="评 论" onclick ="funComment('${successCase.id}')"/>&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>