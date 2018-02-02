<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>集团信息管理</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/json2.js"></script>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var strGroupNumber;
	var strOperators;
		$(document).ready(function() {
			//$("th").setAttribute('style', 'vertical-align: middle !important;text-align: center');
			$("th").css("vertical-align: middle;text-align: center;");
			$("#btnImport").click(function(){
				alert("导入注意事项： 1、导入前请下载最新的模板，根据用户需求模板会相应更改！2、导入时查看表中区县和归属网格是否和统计中一一对应，没有请联系管理员添加！");
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		$(function(){ 
			/* $("#tableDiv").scroll(function(){//给table外面的div滚动事件绑定一个函数
				var left=$("#tableDiv").scrollLeft();//获取滚动的距离
				var trs=$("#tableDiv table tr");//获取表格的所有tr
				   trs.each(function(i){//对每一个tr（每一行）进行处理
				//获得每一行下面的所有的td，然后选中下标为0的，即第一列，设置position为相对定位
				//相对于父div左边的距离为滑动的距离，然后设置个背景颜色，覆盖住后面几列数据滑动到第一列下面的情况
				//如果有必要也可以设置一个z-index属性
				if(i%2==0){
					$(this).children().eq(0).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
					$(this).children().eq(1).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
					$(this).children().eq(2).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
				}else
				{
					$(this).children().eq(0).css({"position":"relative","top":"0px","left":left});
					$(this).children().eq(1).css({"position":"relative","top":"0px","left":left});
					$(this).children().eq(2).css({"position":"relative","top":"0px","left":left});
				}
				
				});
				}); */
			
			if(${display=='hide'})
			{
				hideCheck();
			}
			$(".ul-form").show();
			if(${flag=='hide'})
			{
				$(".ul-form li").hide();
				$("#flag").hide();
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function hideCheck(){
			$(".ul-form li").hide();
			$("#showBtn").show();
			$("#display").val("hide");
			
		}
		function showCheck(){
			$(".ul-form li").show();
			$("#showBtn").hide();
			$("#display").val("show");
			
		}
		function getInfo(id){
			var display=$("#display").val();
			window.location.href='${ctx}/pc/pcGroup/form?id='+id+'&display='+display+'&flag=${flag}';			
		}
		function deleteInfo(id){
			var display=$("#display").val();
			window.location.href='${ctx}/pc/pcGroup/delete?id='+id+'&display='+display;			
		}
		function getMobileNumber(groupNumber,operators){//IE传参数出现乱码，因此使用加密解密的方式传参数
			strGroupNumber=groupNumber;
			strOperators=operators;
			$.getJSON("${ctx}/pc/pcGroupMobileNumber/getMobileNumber/" + encodeURI(encodeURI(groupNumber))+ "/" +encodeURI(encodeURI(operators)) , function(data){
				var html= "<table style='padding:10px;' class='table table-striped table-bordered table-condensed'>"
				           +"<thead><tr><th colspan='2'><input class='btn btn-primary' type='button' value='添加' onclick='addMobileNumber();'/></th></tr></thead>"
				           +"<thead><tr><th>手机号码</th><th>操作</th></tr></thead><tbody>";
				  for(var i=0; i<data.length; i++)  
				  {  
				     html+="<tr><td>"+data[i].mobileNumber+"</td><td><a href='javascript:editMobileNumber("+data[i].mobileNumber+");'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteMobileNumber("+data[i].mobileNumber+");'>删除</a></td></tr>"; 
				  }
				 html+="</tbody></table>";
				 $.jBox(html, {title:"手机号码信息",width:600,height:400, buttons:{"关闭":true}});				
				});		
		}
		function addMobileNumber(){
			var html = "<div style='padding:10px;'>手机号码：<input type='number' id='mobileNumber' name='mobileNumber' /></div>";
			var submit = function (v, h, f) {
			    if (f.mobileNumber == '') {
			    	top.$.jBox.tip("请输入手机号码!", 'error', { focusId: "mobileNumber" }); // 关闭设置 mobileNumber 为焦点
			    	//$.jBox.error('请输入手机号码!', '错误提示:');
			        return false;
			    }
			    
		        $.post("${ctx}/pc/pcGroupMobileNumber/addMobileNumber/" + encodeURI(encodeURI(strGroupNumber))+ "/" +encodeURI(encodeURI(strOperators))+"/" +encodeURI(encodeURI(f.mobileNumber)),function(result){
		        	if(result=="success"){
		        		top.$.jBox.tip("手机号码添加成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strGroupNumber,strOperators);			    
					    return true; 
		        	}else{
		        		top.$.jBox.tip("手机号码已存在！");
		        	}	
		       });	
			    
			};
			top.$.jBox(html, { title: "手机号码添加", submit: submit});
		}
		function editMobileNumber(mobileNumber){
			var html = "<div style='padding:10px;'>手机号码：<input type='number' id='mobileNumber' name='mobileNumber' value='"+mobileNumber+"' /></div>";
			var submit = function (v, h, f) {
			    if (f.mobileNumber == '') {
			    	top.$.jBox.tip("手机号码不能为空!", 'error', { focusId: "mobileNumber" }); // 关闭设置 mobileNumber 为焦点
			    	//$.jBox.error('请输入手机号码!', '错误提示:');
			        return false;
			    }
			    
		        $.post("${ctx}/pc/pcGroupMobileNumber/editMobileNumber/" + encodeURI(encodeURI(strGroupNumber))+ "/" +encodeURI(encodeURI(mobileNumber))+"/" +encodeURI(encodeURI(f.mobileNumber)),function(result){
		        	if(result=="success"){
		        		top.$.jBox.tip("手机号码修改成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strGroupNumber,strOperators);			    
					    return true; 
		        	}else{
		        		top.$.jBox.tip("手机号码已存在！");
		        	}	
		       });	
			    
			};
			top.$.jBox(html, { title: "手机号码添加", submit: submit});
		}
		function deleteMobileNumber(mobileNumber){
			top.$.jBox.confirm("确认要删除手机号码吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$.post("${ctx}/pc/pcGroupMobileNumber/deleteMobileNumber/" + encodeURI(encodeURI(strGroupNumber))+ "/" +encodeURI(encodeURI(strOperators))+"/" +encodeURI(encodeURI(mobileNumber)),function(result){			        	
		        		top.$.jBox.tip("手机号码删除成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strGroupNumber,strOperators);			    
					    return true; 			        		
			       });
				}
			});
		}
		/* function sort(content){
			var sort = $("#sort").val();	
            if(sort.indexOf("ASC") > 0 ){
            	$("#sort").val("a."+content+"  DESC,");
            	$("#"+content).html("▼"+$("#"+content).html().replace("▲","").replace("▼","")); 
           	}else{
           		$("#sort").val("a."+content+"  ASC,");	
           		$("#"+content).html("▲"+$("#"+content).html().replace("▲","").replace("▼","")); 
           	} 
			$("#searchForm").submit();	
		} */
		
		
	</script>
	
</head>
<style type="text/css">
	   th{
	       vertical-align: middle;text-align: center;
	    }
</style>
<body>
    <script type="text/template" id="mobileBox">
		<table class="table table-striped ">         
           {{#key}}
           <tr>
               <td width="100px;">{{mobileNumber}}</td>
           </tr>
		   {{/key}}
		</table>
	</script>
<%-- <div id="mobileBox" class="hide">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		
		<tbody>
		<c:forEach items="${list}" var="pcGroupMobileNumber">
			<tr>
				<td>
					${pcGroupMobileNumber.mobileNumber}
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div> --%>
    <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pc/pcGroup/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit"  value="   导    入   "/>
			<a href="${pageContext.request.contextPath}/static/excel/ABC类集团信息导入模板.xlsx">下载模板</a>
			<br>
			<font color="red">注意：导入模板根据需求随时变化，导入前请下载最新模板！</font>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pc/pcGroup/">集团信息列表</a></li>
		<shiro:hasPermission name="pc:pcGroup:edit"><li><a href="${ctx}/pc/pcGroup/form">集团信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form name="form" id="searchForm" modelAttribute="pcGroup" action="${ctx}/pc/pcGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="display" name="display" type="hidden" value=""/>
		<input id="flag" name="flag" type="hidden" value="${flag}"/>
		<form:input path="sort" htmlEscape="false"  type="hidden" maxlength="255" class="input-medium"/>
		<ul class="ul-form" id="ul-form" style="display:none">
		 
			<li><label>区县：</label>
				<sys:treeselect id="organization" name="organization.id" value="${pcGroup.organization.id}" labelName="organization.name" labelValue="${pcGroup.organization.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>集团编号：</label>
				<form:input path="groupNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>集团名称：</label>
				<form:input path="groupName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>营业状态：</label>
				<form:select path="groupState" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户级别：</label>
				<form:select path="customerLevel" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('customer_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">行业类别：</label>
				<form:select path="firstIndustryCategory" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('first_industry_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">上级单位名称：</label>
				<form:select path="orgParentName" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_parent_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>归属网格：</label>
				<%-- <form:input path="attributedGrid" htmlEscape="false" maxlength="50" class="input-medium"/> --%>
				<sys:treeselect id="attributedGrid" name="attributedGrid.id" value="${pcGroup.attributedGrid.id}" labelName="attributedGrid.name" labelValue="${pcGroup.attributedGrid.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<form:input path="taxRevenue" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="taxRevenueTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>员工数量：</label>
				<form:input path="phoneUsageEmployeesNumber" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="phoneUsageEmployeesNumberTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>市场占有率：</label>
				<form:input path="marketShare" htmlEscape="false" maxlength="50" class="input-mini"/>%&nbsp;-&nbsp;
				<form:input path="marketShareTo" htmlEscape="false" maxlength="50" class="input-mini"/>%
			</li>
		   <%--  <li><label>客户经理：</label>
				<sys:treeselect id="customerManagerNumber" name="customerManagerNumber.id" value="${pcGroup.customerManagerNumber.id}" labelName="customerManagerNumber.name" labelValue="${pcGroup.customerManagerNumber.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label style="width:120px">客户经理工号：</label>
				<form:input path="customerManagerNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			
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
			
			 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="pc:pcGroup:import"><li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li></shiro:hasPermission>
			<!-- <li class="btns"><input  class="btn btn-primary" type="button" value="隐藏查询" onclick="javascript:hideCheck();"/></li>			
			<li id="showBtn" class="btns" style="display:none"><input type="button" class="btn btn-primary" value="显示查询" onclick="javascript:showCheck();"/></li>	 -->		
			<li class="clearfix"></li>
			
		</ul>
		<c:if test="${flag=='hide'}">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-2);"/>
			</c:if>	
				
		<ul class="ul-form1" id="sysjqk" style="display:none">	
			<li><label style="width:180px"><font color="red">使用手机情况-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="phoneUsageMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="phoneUsageMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>		
			<li><label style="width:140px">我网协议到期时间：</label>
				<input name="phoneUsageMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="phoneUsageMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="sysjqk" style="display:none">	
			<li><label style="width:180px"><font color="red">使用手机情况-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="phoneUsageUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="phoneUsageUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label style="width:140px">联通协议到期时间：</label>
				<input name="phoneUsageUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="phoneUsageUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="sysjqk" style="display:none">
			<li><label style="width:180px"><font color="red">使用手机情况-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="phoneUsageTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="phoneUsageTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label style="width:140px">电信协议到期时间：</label>
				<input name="phoneUsageTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="phoneUsageTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.phoneUsageTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="sjzx" style="display:none">	
			<li><label style="width:180px"><font color="red">数据专线-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="dataLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="dataLineMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="dataLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="dataLineMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="sjzx" style="display:none">	
			<li><label style="width:180px"><font color="red">数据专线-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="dataLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="dataLineUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="dataLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="dataLineUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="sjzx" style="display:none">	
			<li><label style="width:180px"><font color="red">数据专线-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="dataLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="dataLineTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="dataLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="dataLineTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.dataLineTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="hlwzx" style="display:none">	
			<li><label style="width:180px"><font color="red">互联网专线-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="internetLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="internetLineMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="internetLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="internetLineMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="hlwzx" style="display:none">	
			<li><label style="width:180px"><font color="red">互联网专线-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="internetLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="internetLineUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="internetLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="internetLineUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="hlwzx" style="display:none">	
			<li><label style="width:180px"><font color="red">互联网专线-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="internetLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="internetLineTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="internetLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="internetLineTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.internetLineTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="yyzx" style="display:none">	
			<li><label style="width:180px"><font color="red">语音专线-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="voiceLineMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="voiceLineMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="voiceLineMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="voiceLineMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="yyzx" style="display:none">	
			<li><label style="width:180px"><font color="red">语音专线-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="voiceLineUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="voiceLineUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="voiceLineUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="voiceLineUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="yyzx" style="display:none">	
			<li><label style="width:180px"><font color="red">语音专线-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="voiceLineTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="voiceLineTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="voiceLineTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="voiceLineTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.voiceLineTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="syimsqk" style="display:none">	
			<li><label style="width:180px"><font color="red">使用IMS情况-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="imsMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="imsMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="imsMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="imsMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="syimsqk" style="display:none">	
			<li><label style="width:180px"><font color="red">使用IMS情况-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="imsUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="imsUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="imsUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="imsUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="syimsqk" style="display:none">	
			<li><label style="width:180px"><font color="red">使用IMS情况-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="imsTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="imsTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="imsTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="imsTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.imsTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="qtxxhcpsyqk" style="display:none">	
			<li><label style="width:250px"><font color="red">其他信息化产品使用情况-移动:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="otherProductsMobileMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="otherProductsMobileMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="otherProductsMobileExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsMobileExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="otherProductsMobileExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsMobileExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
		<ul class="ul-form1" id="qtxxhcpsyqk" style="display:none">	
			<li><label style="width:250px"><font color="red">其他信息化产品使用情况-联通:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="otherProductsUnicomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="otherProductsUnicomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="otherProductsUnicomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="otherProductsUnicomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsUnicomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<ul class="ul-form1" id="qtxxhcpsyqk" style="display:none">	
			<li><label style="width:250px"><font color="red">其他信息化产品使用情况-电信:</font>&nbsp;&nbsp;&nbsp;&nbsp;月收入：</label>
				<form:input path="otherProductsTelecomMonthlyIncome" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="otherProductsTelecomMonthlyIncomeTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>到期时间：</label>
				<input name="otherProductsTelecomExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
				<input name="otherProductsTelecomExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pcGroup.otherProductsTelecomExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>	
			</li>
		</ul>
		<%-- <ul class="ul-form"  id="ul-form" style="display:none">	
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="pc:pcGroup:import"><li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li></shiro:hasPermission>
			<li class="btns"><input  class="btn btn-primary" type="button" value="隐藏查询" onclick="javascript:hideCheck();"/></li>			
			<li id="showBtn" class="btns" style="display:none"><input type="button" class="btn btn-primary" value="显示查询" onclick="javascript:showCheck();"/></li>			
			<li class="clearfix"></li>
			</ul>
			<c:if test="${flag=='hide'}">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-2);"/>
			</c:if> --%>
		
	</form:form>
	<sys:message content="${message}"/>
	<!-- <div id="tableDiv" style="width:100%;overflow-x:auto;"> -->
	<table id="contentTable"   class="table table-striped table-bordered table-condensed" >
		<thead>
		    <tr> 
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="3">区县</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">集团编号</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">集团名称</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">营业状态</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">客户级别</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">行业类别</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">上级单位名称</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">归属网格</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">企业类型</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">单位实际地址</th>
				<!-- 
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">证件号码</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">注册资金</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">税收</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">一把手姓名</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">一把手电话</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">分管信息关键人姓名</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">分管信息关键人手机号码</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">信息科关键人姓名</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">信息科关键人手机号码</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">其他关键人姓名</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">其他关键人手机号码</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">联系人姓名</th>
				<th style="vertical-align: middle !important;text-align: center;" rowspan="3">联系人手机号码</th>
				 -->
				<!-- 
		        <th style="vertical-align: middle !important;text-align: center;" colspan="13">使用手机情况</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="9">数据专线</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="9">互联网专线</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="9">语音专线</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="9">使用IMS情况</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="9">其他信息化产品使用情况</th>
		        
		        <th  style="vertical-align: middle !important;text-align: center;" rowspan="3">正在跟进或将来要跟进的业务名称</th>
				<th  style="vertical-align: middle !important;text-align: center;" rowspan="3">具体跟进情况</th>
				 -->
				<th  style="vertical-align: middle !important;text-align: center;" rowspan="3">客户经理工号</th>
				<th  style="vertical-align: middle !important;text-align: center;" rowspan="3">客户经理姓名</th>
				<shiro:hasPermission name="pc:pcGroup:edit"><th id="flag" style="vertical-align: middle !important;text-align: center;" rowspan="3">操作</th></shiro:hasPermission>
		    </tr>
		    <!--    
		    <tr>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">员工数量</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="5">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		        <th style="vertical-align: middle !important;text-align: center;" rowspan="2">市场占有率</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		        
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">移动</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">联通</th>
		        <th style="vertical-align: middle !important;text-align: center;" colspan="3">电信</th>
		    </tr>
		      
		    
			<tr>
								
				<th style="vertical-align: middle !important;text-align: center;">号码数量</th>
				<th id="phoneUsageMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageMobileMonthlyIncome');">月收入</th>
				<th id="phoneUsageMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageMobileExpirationDate');">我网协议到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">统一付费成员数量</th>
				<th style="vertical-align: middle !important;text-align: center;">统一付费收入</th>
				<th style="vertical-align: middle !important;text-align: center;">号码数量</th>
				<th id="phoneUsageUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageUnicomMonthlyIncome');">月收入</th>
				<th id="phoneUsageUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageUnicomExpirationDate');">联通协议到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">号码数量</th>
				<th id="phoneUsageTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageTelecomMonthlyIncome');">月收入</th>
				<th id="phoneUsageTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('phoneUsageTelecomExpirationDate');">电信协议到期时间</th>	
				
					
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="dataLineMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineMobileMonthlyIncome');">月收入</th>
				<th id="dataLineMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineMobileExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="dataLineUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineUnicomMonthlyIncome');">月收入</th>
				<th id="dataLineUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineUnicomExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="dataLineTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineTelecomMonthlyIncome');">月收入</th>
				<th id="dataLineTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('dataLineTelecomExpirationDate');">到期时间</th>
				
				
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="internetLineMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineMobileMonthlyIncome');">月收入</th>
				<th id="internetLineMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineMobileExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="internetLineUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineUnicomMonthlyIncome');">月收入</th>
				<th id="internetLineUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineUnicomExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="internetLineTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineTelecomMonthlyIncome');">月收入</th>
				<th id="internetLineTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('internetLineTelecomExpirationDate');">到期时间</th>
				
				
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="voiceLineMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineMobileMonthlyIncome');">月收入</th>
				<th id="voiceLineMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineMobileExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="voiceLineUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineUnicomMonthlyIncome');">月收入</th>
				<th id="voiceLineUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineUnicomExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">条数</th>
				<th id="voiceLineTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineTelecomMonthlyIncome');">月收入</th>
				<th id="voiceLineTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('voiceLineTelecomExpirationDate');">到期时间</th>
				
				
				<th style="vertical-align: middle !important;text-align: center;">门数</th>
				<th id="imsMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsMobileMonthlyIncome');">月收入</th>
				<th id="imsMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsMobileExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">门数</th>
				<th id="imsUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsUnicomMonthlyIncome');">月收入</th>
				<th id="imsUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsUnicomExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">门数</th>
				<th id="imsTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsTelecomMonthlyIncome');">月收入</th>
				<th id="imsTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('imsTelecomExpirationDate');">到期时间</th>
				
				
				<th style="vertical-align: middle !important;text-align: center;">业务名称</th>
				<th id="otherProductsMobileMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsMobileMonthlyIncome');">月收入</th>
				<th id="otherProductsMobileExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsMobileExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">业务名称</th>
				<th id="otherProductsUnicomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsUnicomMonthlyIncome');">月收入</th>
				<th id="otherProductsUnicomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsUnicomExpirationDate');">到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">业务名称</th>
				<th id="otherProductsTelecomMonthlyIncome" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsTelecomMonthlyIncome');">月收入</th>
				<th id="otherProductsTelecomExpirationDate" style="vertical-align: middle !important;text-align: center;" onclick="sort('otherProductsTelecomExpirationDate');">到期时间</th>
				
				
				
				
			</tr>
			-->
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pcGroup">
			<tr>
				<td><a href="javascript:getInfo('${pcGroup.id}');">
				<%-- <a href="${ctx}/pc/pcGroup/form?id=${pcGroup.id}"> --%>
					${pcGroup.organization.name == null?'无':pcGroup.organization.name}
				</a></td>
				<td>
					${pcGroup.groupNumber}
				</td>
				<td>
					${pcGroup.groupName}
				</td>
				<td>
					${fns:getDictLabel(pcGroup.groupState, 'group_state', '无') }
				</td>
				<td>
					${fns:getDictLabel(pcGroup.customerLevel, 'customer_level', '无')}
				</td>
				<td>
					${fns:getDictLabel(pcGroup.firstIndustryCategory, 'first_industry_category', '无')}
				</td>
				<td>
					${fns:getDictLabel(pcGroup.orgParentName, 'org_parent_name', '无')} 
				</td>
				<td>
					${pcGroup.attributedGrid.name == null || pcGroup.attributedGrid.name == '' ?'无':pcGroup.attributedGrid.name}
				</td>
				<td>
					${fns:getDictLabel(pcGroup.enterpriseType, 'enterprise_type','无')}
				</td>
				<td>
					${pcGroup.unitActualAddress == null || pcGroup.unitActualAddress == '' ?'无':pcGroup.unitActualAddress}
				</td>
				
				<!-- 
				<td>
					${pcGroup.identificationNumber == null || pcGroup.identificationNumber == '' ?'无':pcGroup.identificationNumber}
				</td>
				
				
				<td>
					${pcGroup.registeredCapital == null || pcGroup.registeredCapital == '' ?'无':pcGroup.registeredCapital}
				</td>
				<td>
					${pcGroup.taxRevenue == null || pcGroup.taxRevenue == '' ?'无':pcGroup.taxRevenue}
				</td>
				<td>
					${pcGroup.numberOneName == null || pcGroup.numberOneName == '' ?'无':pcGroup.numberOneName}
				</td>
				<td>
					${pcGroup.numberOnePhone == null || pcGroup.numberOnePhone == '' ?'无':pcGroup.numberOnePhone}
				</td>
				<td>
					${pcGroup.chargeKeyPersonName == null || pcGroup.chargeKeyPersonName == '' ?'无':pcGroup.chargeKeyPersonName}
				</td>
				<td>
					${pcGroup.chargeKeyPersonPhone == null || pcGroup.chargeKeyPersonPhone == '' ?'无':pcGroup.chargeKeyPersonPhone}
				</td>
				<td>
					${pcGroup.informationKeyPersonName == null || pcGroup.informationKeyPersonName == '' ?'无':pcGroup.informationKeyPersonName}
				</td>
				<td>
					${pcGroup.informationKeyPersonPhone == null || pcGroup.informationKeyPersonPhone == '' ?'无':pcGroup.informationKeyPersonPhone}
				</td>
				<td>
					${pcGroup.otherKeyPersonName == null || pcGroup.otherKeyPersonName == '' ?'无':pcGroup.otherKeyPersonName}
				</td>
				<td>
					${pcGroup.otherKeyPersonPhone == null || pcGroup.otherKeyPersonPhone == '' ?'无':pcGroup.otherKeyPersonPhone}
				</td>
				<td>
					${pcGroup.contactsName == null || pcGroup.contactsName == '' ?'无':pcGroup.contactsName}
				</td>
				<td>
					${pcGroup.contactsPhone == null || pcGroup.contactsPhone == '' ?'无':pcGroup.contactsPhone}
				</td>
				 -->
				<!-- 
				<td>
					${pcGroup.phoneUsageEmployeesNumber == null || pcGroup.phoneUsageEmployeesNumber == '' ?'无':pcGroup.phoneUsageEmployeesNumber}
				</td>
				<td><a href="javascript:getMobileNumber('${pcGroup.groupNumber}','移动');">
					${pcGroup.phoneUsageMobileNumber == null || pcGroup.phoneUsageMobileNumber == '' ?'无':pcGroup.phoneUsageMobileNumber}
				</a></td>
				<td>
					${pcGroup.phoneUsageMobileMonthlyIncome == null || pcGroup.phoneUsageMobileMonthlyIncome == '' ?'无':pcGroup.phoneUsageMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.phoneUsageMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.unifiedPaymentNumber == null || pcGroup.unifiedPaymentNumber == '' ?'无':pcGroup.unifiedPaymentNumber}
				</td>
				<td>
					${pcGroup.unifiedPaymentIncome == null || pcGroup.unifiedPaymentIncome == '' ?'无':pcGroup.unifiedPaymentIncome}
				</td>
				<td><a href="javascript:getMobileNumber('${pcGroup.groupNumber}','联通');">
					${pcGroup.phoneUsageUnicomNumber == null || pcGroup.phoneUsageUnicomNumber == '' ?'无':pcGroup.phoneUsageUnicomNumber}
				</a></td>
				<td>
					${pcGroup.phoneUsageUnicomMonthlyIncome == null || pcGroup.phoneUsageUnicomMonthlyIncome == '' ?'无':pcGroup.phoneUsageUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.phoneUsageUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td><a href="javascript:getMobileNumber('${pcGroup.groupNumber}','电信');">
					${pcGroup.phoneUsageTelecomNumber == null || pcGroup.phoneUsageTelecomNumber == '' ?'无':pcGroup.phoneUsageTelecomNumber}
				</a></td>
				<td>
					${pcGroup.phoneUsageTelecomMonthlyIncome == null || pcGroup.phoneUsageTelecomMonthlyIncome == '' ?'无':pcGroup.phoneUsageTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.phoneUsageTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.marketShare == null || pcGroup.marketShare == '' ?'无':pcGroup.marketShare}
				</td>
				<td>
					${pcGroup.dataLineMobileNumber == null || pcGroup.dataLineMobileNumber == '' ?'无':pcGroup.dataLineMobileNumber}
				</td>
				<td>
					${pcGroup.dataLineMobileMonthlyIncome == null || pcGroup.dataLineMobileMonthlyIncome == '' ?'无':pcGroup.dataLineMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.dataLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.dataLineUnicomNumber == null || pcGroup.dataLineUnicomNumber == '' ?'无':pcGroup.dataLineUnicomNumber}
				</td>
				<td>
					${pcGroup.dataLineUnicomMonthlyIncome == null || pcGroup.dataLineUnicomMonthlyIncome == '' ?'无':pcGroup.dataLineUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.dataLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.dataLineTelecomNumber == null || pcGroup.dataLineTelecomNumber == '' ?'无':pcGroup.dataLineTelecomNumber}
				</td>
				<td>
					${pcGroup.dataLineTelecomMonthlyIncome == null || pcGroup.dataLineTelecomMonthlyIncome == '' ?'无':pcGroup.dataLineTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.dataLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.internetLineMobileNumber == null || pcGroup.internetLineMobileNumber == '' ?'无':pcGroup.internetLineMobileNumber}
				</td>
				<td>
					${pcGroup.internetLineMobileMonthlyIncome == null || pcGroup.internetLineMobileMonthlyIncome == '' ?'无':pcGroup.internetLineMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.internetLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.internetLineUnicomNumber == null || pcGroup.internetLineUnicomNumber == '' ?'无':pcGroup.internetLineUnicomNumber}
				</td>
				<td>
					${pcGroup.internetLineUnicomMonthlyIncome == null || pcGroup.internetLineUnicomMonthlyIncome == '' ?'无':pcGroup.internetLineUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.internetLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.internetLineTelecomNumber == null || pcGroup.internetLineTelecomNumber == '' ?'无':pcGroup.internetLineTelecomNumber}
				</td>
				<td>
					${pcGroup.internetLineTelecomMonthlyIncome == null || pcGroup.internetLineTelecomMonthlyIncome == '' ?'无':pcGroup.internetLineTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.internetLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.voiceLineMobileNumber == null || pcGroup.voiceLineMobileNumber == '' ?'无':pcGroup.voiceLineMobileNumber}
				</td>
				<td>
					${pcGroup.voiceLineMobileMonthlyIncome == null || pcGroup.voiceLineMobileMonthlyIncome == '' ?'无':pcGroup.voiceLineMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.voiceLineMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.voiceLineUnicomNumber == null || pcGroup.voiceLineUnicomNumber == '' ?'无':pcGroup.voiceLineUnicomNumber}
				</td>
				<td>
					${pcGroup.voiceLineUnicomMonthlyIncome == null || pcGroup.voiceLineUnicomMonthlyIncome == '' ?'无':pcGroup.voiceLineUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.voiceLineUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.voiceLineTelecomNumber == null || pcGroup.voiceLineTelecomNumber == '' ?'无':pcGroup.voiceLineTelecomNumber}
				</td>
				<td>
					${pcGroup.voiceLineTelecomMonthlyIncome == null || pcGroup.voiceLineTelecomMonthlyIncome == '' ?'无':pcGroup.voiceLineTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.voiceLineTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.imsMobileNumber == null || pcGroup.imsMobileNumber == '' ?'无':pcGroup.imsMobileNumber}
				</td>
				<td>
					${pcGroup.imsMobileMonthlyIncome == null || pcGroup.imsMobileMonthlyIncome == '' ?'无':pcGroup.imsMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.imsMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.imsUnicomNumber == null || pcGroup.imsUnicomNumber == '' ?'无':pcGroup.imsUnicomNumber}
				</td>
				<td>
					${pcGroup.imsUnicomMonthlyIncome == null || pcGroup.imsUnicomMonthlyIncome == '' ?'无':pcGroup.imsUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.imsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.imsTelecomNumber == null || pcGroup.imsTelecomNumber == '' ?'无':pcGroup.imsTelecomNumber}
				</td>
				<td>
					${pcGroup.imsTelecomMonthlyIncome == null || pcGroup.imsTelecomMonthlyIncome == '' ?'无':pcGroup.imsTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.imsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.otherProductsMobileBusinessName == null || pcGroup.otherProductsMobileBusinessName == '' ?'无':pcGroup.otherProductsMobileBusinessName}
				</td>
				<td>
					${pcGroup.otherProductsMobileMonthlyIncome == null || pcGroup.otherProductsMobileMonthlyIncome == '' ?'无':pcGroup.otherProductsMobileMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.otherProductsMobileExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.otherProductsUnicomBusinessName == null || pcGroup.otherProductsUnicomBusinessName == '' ?'无':pcGroup.otherProductsUnicomBusinessName}
				</td>
				<td>
					${pcGroup.otherProductsUnicomMonthlyIncome == null || pcGroup.otherProductsUnicomMonthlyIncome == '' ?'无':pcGroup.otherProductsUnicomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.otherProductsUnicomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.otherProductsTelecomBusinessName == null || pcGroup.otherProductsTelecomBusinessName == '' ?'无':pcGroup.otherProductsTelecomBusinessName}
				</td>
				<td>
					${pcGroup.otherProductsTelecomMonthlyIncome == null || pcGroup.otherProductsTelecomMonthlyIncome == '' ?'无':pcGroup.otherProductsTelecomMonthlyIncome}
				</td>
				<td>
					<fmt:formatDate value="${pcGroup.otherProductsTelecomExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcGroup.followUpBusinessName == null || pcGroup.followUpBusinessName == '' ?'无':pcGroup.followUpBusinessName}
				</td>
				<td>
					${pcGroup.followUpSituation == null || pcGroup.followUpSituation == '' ?'无':pcGroup.followUpSituation}
				</td>
				 -->
				<td>
					${pcGroup.customerManagerNumber == null || pcGroup.customerManagerNumber == '' ?'无':pcGroup.customerManagerNumber}
				</td>
				<td>
					${pcGroup.customerManagerName == null || pcGroup.customerManagerName == '' ?'无':pcGroup.customerManagerName}
				</td>
				<shiro:hasPermission name="pc:pcGroup:edit"><td id="flag">
    				<a href="javascript:getInfo('${pcGroup.id}');">修改</a>
					<a href="javascript:deleteInfo('${pcGroup.id}');" onclick="return confirmx('确认要删除该集团信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- </div> -->
	<div class="pagination">${page}</div>
</body>
</html>