<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	 
	 .input-xxxlarge {
		 width: 650px;
	 }
	
	</style>
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
		function updateAtt(flag)
		{
			if(flag == 1)
			{
				
				
				//当当前人有单位负责人权限并且 上传的list没有他自己时，则要弹出不参会原因和处理意见
				if(${dwfzr=='true'})
				{
					var tmepFlag = true;
					var data = ${fns:toJson(meetingData.meetingDataAttendeeList)};
				
					for (var i=0; i<data.length; i++){
						
						var userId  = $('#meetingDataAttendeeList'+i+'_userId');
						var delFlag = $('#meetingDataAttendeeList'+i+'_delFlag');
						
						if(delFlag.val() != "9"&&userId.val()=='${fns:getUser().id}')
						{
							
							tmepFlag = false;
							break;
						}	
					}
					
					if(tmepFlag)
					{
						var html = "<div style='padding:10px;'>不参会原因：<input type='text' id='bchyy' name='bchyy' /><br>处理意见：&nbsp;&nbsp;&nbsp;<input type='text' id='clyj' name='clyj' /></div>";
						var submit = function (v, h, f) {
						    if (f.bchyy == '') {
						    	top.$.jBox.tip("请输入不参会原因!!!", 'error', { focusId: "bchyy" }); // 关闭设置 bchyy 为焦点
						        return false;
						    }
						   	
						    $("#bchyy").val(f.bchyy);
						    $("#clyj").val(f.clyj);
						    
						    $("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=1");
							$("#inputForm").submit();
						    return true;
						};
						top.$.jBox(html, { title: "不参会原因", submit: submit});
					}
					else
					{
						$("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=1");
						$("#inputForm").submit();
					}	
					
				}
				else
				{
					
					$("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=1");
					$("#inputForm").submit();
				}	
				
				
				//top.$.jBox.confirm("上报后无法在操作参会人信息，确认要上报参会人吗？","系统提示",function(v,h,f){
				//	if(v=="ok"){
				//		$("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=1");
				//		$("#inputForm").submit();
				//	}
				//},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
				
			}
			else if(flag == 2)
			{
				
				top.$.jBox.confirm("确认要呈报参会人吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=2");
						$("#inputForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			}
			else
			{
				$("#inputForm").attr("action","${ctx}/meeting/meetingData/attendeeAll?flag=0");
				$("#inputForm").submit();
			}	
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		
		<li class="active"><a href="${ctx}/meeting/meetingData/form?id=${meetingData.id}">会议<shiro:hasPermission name="meeting:meetingData:edit">${meetingData.status eq '1' ? '查看' : not empty meetingData.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission name="meeting:meetingData:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="meetingData" action="${ctx}/meeting/meetingData/attendeeAll" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xxxlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<div class="controls">
				<form:input path="addr" htmlEscape="false" maxlength="200" class="input-xxxlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议时间：</label>
			<div class="controls">
				
				<input  style="width: 160px" id="startDate" name="startDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${meetingData.startDate}" pattern="yyyy-MM-dd  HH:mm"/>" />
				<span class="help-inline"><font color="red">*</font> </span>
			
			<label >上报截止时间：</label>
			
				<input  style="width: 160px" id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${meetingData.endDate}" pattern="yyyy-MM-dd  HH:mm"/>" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea readonly="true" path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<c:if test="${meetingData.status eq '1' or meetingData.status eq '4'or meetingData.status eq '8'}">
			
					
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/meeting/meetingData" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">接收人：</label>
				<div class="controls">
					<div id="p" style="display: none;">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>接收人</th>
								<th>接受部门</th>
								<th>阅读状态</th>
								<th>阅读时间</th>
								<th>上报状态</th>
								<th>上报时间</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${meetingData.meetingDataReceiverList}" var="meetingDataReceiver">
							<tr>
								<td>
									${meetingDataReceiver.user.name}
								</td>
								<td>
									${meetingDataReceiver.user.office.name}
								</td>
								<td>
									${fns:getDictLabel(meetingDataReceiver.readFlag, 'oa_notify_read', '')}
								</td>
								<td>
									<fmt:formatDate value="${meetingDataReceiver.readDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<c:if test="${meetingDataReceiver.flag ne '1'}">
										未上报
									</c:if>
									<c:if test="${meetingDataReceiver.flag eq '1'}">
										已上报
									</c:if>
								</td>
								<td>
									<fmt:formatDate value="${meetingDataReceiver.doneDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					</div>
					已查阅：${meetingData.readNum} &nbsp; 未查阅：${meetingData.unReadNum} &nbsp; 已上报：${meetingData.sbNum} &nbsp; 未上报：${meetingData.unsbNum} &nbsp; 总共：${meetingData.readNum + meetingData.unReadNum}&nbsp;&nbsp;<button type="button" onclick="$('#p').hide(500);">折叠</button>&nbsp;<button type="button" onclick="$('#p').show(500);">展开</button>
				</div>
			</div>
			<input type='hidden' id='bchyy' name='bchyy' /><input type='hidden' id='clyj' name='clyj' />
			<sys:meetingtreeselect id="meetingDataReceiver" name="meetingDataAttendeeIds" value="${meetingData.meetingDataAttendeeIds}" labelName="oaNotifyRecordNames" labelValue="${meetingData.oaNotifyRecordNames}"
						title="参会人" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
			<div class="control-group" style="display: none">
				<label class="control-label">是否发送消息(微信/短信)：</label>
				<div class="controls">
					<input name="sendFlag" type="radio" value="0" checked="checked"/>是 
         			<input name="sendFlag" type="radio" value="1" />否
				</div>
			</div>
			
		</c:if>
		<div class="form-actions">
			
			<c:if test="${meetingData.self && meetingData.flag!='1'}">
				<shiro:hasPermission name="meeting:meetingData:attendee">
					<input  class="btn btn-primary" type="button" value="保 存"   onclick ="updateAtt(0)"/>&nbsp;
					<input  class="btn btn-primary" type="button" value="呈 报"   onclick ="updateAtt(2)"/>&nbsp;
					<input  class="btn btn-primary" type="button" value="上 报"   onclick ="updateAtt(1)"/>&nbsp;
				</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>