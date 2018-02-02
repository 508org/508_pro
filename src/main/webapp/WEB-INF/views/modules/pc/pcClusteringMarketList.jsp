<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>聚类市场信息管理</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/json2.js"></script>
	<meta name="decorator" content="default" />
	<script type="text/javascript" >
	var strShopName;
	var strOperators;
		$(document).ready(function() {			
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
					$(this).children().eq(0).css({"position":"relative","top":"0px","left":left,"background-color":"#E0EEEE"});
					$(this).children().eq(1).css({"position":"relative","top":"0px","left":left,"background-color":"#E0EEEE"});
					$(this).children().eq(2).css({"position":"relative","top":"0px","left":left,"background-color":"#E0EEEE"});
				}
				
				});
				}); */

			
			var sort = $("#sort").val();
				var id=sort.replace("ASC,","").replace("DESC,","").replace(" ","").replace("a.","").replace("▲","").replace("▼","");
            if(sort.indexOf("ASC") > 0 ){
            	$("#"+id).html($("#"+id).html().replace("▼","")); 
           	}
            if(sort.indexOf("DESC") > 0 ){
            	$("#"+id).html($("#"+id).html().replace("▲","")); 
           	}
            
			if(${display=='hide'})
			{
				hideCheck();
			}
			$(".ul-form").show();
			if(${flag=='hide'})
			{
				$(".ul-form li").hide();
			}
			/* $("#contentTable").toSuperTable({
                width: "600px",
                height: "200px",
                fixedCols: 2
            }); */
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
			window.location.href='${ctx}/pc/pcClusteringMarket/form?id='+id+'&display='+display+'&flag=${flag}';			
		}
		function deleteInfo(id){
			var display=$("#display").val();
			window.location.href='${ctx}/pc/pcClusteringMarket/delete?id='+id+'&display='+display;			
		}
		function getMobileNumber(shopName,operators){//IE传参数出现乱码，因此使用加密解密的方式传参数
			strShopName=shopName;
			strOperators=operators;
			$.getJSON("${ctx}/pc/pcClusteringMarketMobileNumber/getMobileNumber/" + encodeURI(encodeURI(shopName))+ "/" +encodeURI(encodeURI(operators))+"?a="+new Date(), function(data){
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
			    
		        $.post("${ctx}/pc/pcClusteringMarketMobileNumber/addMobileNumber/" + encodeURI(encodeURI(strShopName))+ "/" +encodeURI(encodeURI(strOperators))+"/" +encodeURI(encodeURI(f.mobileNumber)),function(result){
		        	if(result=="success"){
		        		top.$.jBox.tip("手机号码添加成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strShopName,strOperators);			    
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
			    
		        $.post("${ctx}/pc/pcClusteringMarketMobileNumber/editMobileNumber/" + encodeURI(encodeURI(strShopName))+ "/" +encodeURI(encodeURI(mobileNumber))+"/" +encodeURI(encodeURI(f.mobileNumber)),function(result){
		        	if(result=="success"){
		        		top.$.jBox.tip("手机号码修改成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strShopName,strOperators);			    
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
					$.post("${ctx}/pc/pcClusteringMarketMobileNumber/deleteMobileNumber/" + encodeURI(encodeURI(strShopName))+ "/" +encodeURI(encodeURI(strOperators))+"/" +encodeURI(encodeURI(mobileNumber)),function(result){			        	
		        		top.$.jBox.tip("手机号码删除成功！");
		        		$.jBox.close(true);
					    getMobileNumber(strShopName,strOperators);			    
					    return true; 			        		
			       });
				}
			});
		}
		/* function sort(content){
			var sort = $("#sort").val();	
            if(sort.indexOf("ASC") > 0 ){
            	$("#sort").val(" a."+content+"  DESC,");
           	}else{
           		$("#sort").val(" a."+content+"  ASC,");	
           	} 
			$("#searchForm").submit();	
		} */
	</script>
</head>
<body>
    <script type="text/template" id="mobileBox">
		<table class="table table-striped ">
           <tr>
               <td width="100px;">{{shopName}}<input class="btn btn-primary" type="button" value="添加" onclick="addMobileNumber();"/></td>
           </tr>
           {{#key}}
           <tr>
               <td width="100px;">{{mobileNumber}}</td>
           </tr>
		   {{/key}}
		</table>
	</script>
     <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pc/pcClusteringMarket/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>			
			<a href="${pageContext.request.contextPath}/static/excel/聚类集团信息导入模板.xlsx">下载模板</a><br>
			<font color="red">注意：导入模板根据需求随时变化，导入前请下载最新模板！</font>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pc/pcClusteringMarket/">聚类市场信息列表</a></li>
		<shiro:hasPermission name="pc:pcClusteringMarket:edit"><li><a href="${ctx}/pc/pcClusteringMarket/form">聚类市场信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form name="form" id="searchForm" modelAttribute="pcClusteringMarket" action="${ctx}/pc/pcClusteringMarket/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="display" name="display" type="hidden" value=""/>
		<input id="flag" name="flag" type="hidden" value="${flag}"/>
		<form:input path="sort" htmlEscape="false" id="sort" type="hidden" maxlength="255" class="input-medium"/>
		<ul class="ul-form" style="display:none">
			<li><label>区县：</label>
				<sys:treeselect id="organization" name="organization.id" value="${pcClusteringMarket.organization.id}" labelName="organization.name" labelValue="${pcClusteringMarket.organization.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>归属网格：</label>
				<%-- <form:input path="attributedGrid" htmlEscape="false" maxlength="50" class="input-medium"/> --%>
				<sys:treeselect id="attributedGrid" name="attributedGrid.id" value="${pcClusteringMarket.attributedGrid.id}" labelName="attributedGrid.name" labelValue="${pcClusteringMarket.attributedGrid.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label style="width:120px">聚类市场类型：</label>
				<form:select path="clusterMarketType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cluster_market_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:120px">聚类市场名称：</label>
				<form:input path="clusterMarketName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>集团编号：</label>
				<form:input path="groupNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>店铺名称：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>员工数量：</label>
				<form:input path="employeesNumber" htmlEscape="false" maxlength="50" class="input-mini"/>&nbsp;-&nbsp;
				<form:input path="employeesNumberTo" htmlEscape="false" maxlength="50" class="input-mini"/>
			</li>
			<li><label>市场占有率：</label>
				<form:input path="marketShare" htmlEscape="false" maxlength="50" class="input-mini"/>%&nbsp;-&nbsp;
				<form:input path="marketShareTo" htmlEscape="false" maxlength="50" class="input-mini"/>%
			</li>
			 <li><label>客户经理：</label>
				<sys:treeselect id="customerManagerNumber" name="customerManagerNumber.id" value="${pcClusteringMarket.customerManagerNumber.id}" labelName="customerManagerNumber.name" labelValue="${pcClusteringMarket.customerManagerNumber.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
			<li><label>宽带运营商：</label>
				<form:select path="broadbandOperators" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('broadband_operators')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label style="width:120px">宽带到期时间：</label>
				<input name="broadbandExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${pcClusteringMarket.broadbandExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
					<input name="broadbandExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${pcClusteringMarket.broadbandExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li> --%>
			<li><label style="width:120px">是否使用固话：</label>
				<form:select path="isUseTelephone" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_or_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>固话运营商：</label>
				<form:select path="telephoneOperators" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('telephone_operators')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label style="width:120px">固话到期时间：</label>
				<input name="telephoneExpirationDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${pcClusteringMarket.telephoneExpirationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;-&nbsp;
					<input name="telephoneExpirationDateTo" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${pcClusteringMarket.telephoneExpirationDateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="pc:pcClusteringMarket:import"><li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li></shiro:hasPermission>
			<!-- <li class="btns"><input  class="btn btn-primary" type="button" value="隐藏查询" onclick="javascript:hideCheck();"/></li>
            <li id="showBtn" class="btns" style="display:none"><input type="button" class="btn btn-primary" value="显示查询" onclick="javascript:showCheck();"/></li> -->
			
			<li class="clearfix"></li>
		</ul>
		<c:if test="${flag=='hide'}">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-2);"/>
			</c:if>
	</form:form>
	<sys:message content="${message}"/>
	<!-- <div id="tableDiv" style="width:100%;overflow-x:auto;"> -->
	
	<table id="contentTable"  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr style="background-color:expression((this.sectionRowIndex%2==0)?'black:red)">
				<th style="vertical-align: middle !important;text-align: center;">区县</th>
				<th style="vertical-align: middle !important;text-align: center;">归属网格</th>
				<th style="vertical-align: middle !important;text-align: center;">店铺名称</th>
				<th style="vertical-align: middle !important;text-align: center;">聚类市场类型</th>
				<th style="vertical-align: middle !important;text-align: center;">聚类市场名称</th>
				<th style="vertical-align: middle !important;text-align: center;">集团编号</th>				
				<th style="vertical-align: middle !important;text-align: center;">地址（街道、门牌号）</th>
				<th style="vertical-align: middle !important;text-align: center;">联系人姓名</th>
				<th style="vertical-align: middle !important;text-align: center;">联系人手机号码</th>
				<th style="vertical-align: middle !important;text-align: center;">员工数量</th>
				<th style="vertical-align: middle !important;text-align: center;">移动数量</th>
				<th style="vertical-align: middle !important;text-align: center;">他网数量</th>
				<th style="vertical-align: middle !important;text-align: center;">市场占有率</th>
				<th style="vertical-align: middle !important;text-align: center;">是否覆盖移动网路</th>
				<th style="vertical-align: middle !important;text-align: center;">是否使用宽带</th>
				<th style="vertical-align: middle !important;text-align: center;">宽带运营商</th>
				<th id="broadband_expiration_date" style="vertical-align: middle !important;text-align: center;" onclick="sort('broadband_expiration_date');">宽带到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">是否使用固话</th>
				<th style="vertical-align: middle !important;text-align: center;">固话运营商</th>
				<th id="telephone_expiration_date" style="vertical-align: middle !important;text-align: center;" onclick="sort('telephone_expiration_date');">固话到期时间</th>
				<th style="vertical-align: middle !important;text-align: center;">其他产品使用情况</th>
				<th style="vertical-align: middle !important;text-align: center;">客户需求</th>
				<th  style="vertical-align: middle !important;text-align: center;">客户经理</th>
				<shiro:hasPermission name="pc:pcClusteringMarket:edit"><th style="vertical-align: middle !important;text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pcClusteringMarket">
			<tr>
				<td><a href="javascript:getInfo('${pcClusteringMarket.id}');">
				<%-- <a href="${ctx}/pc/pcClusteringMarket/form?id=${pcClusteringMarket.id}"> --%>
					${pcClusteringMarket.organization.name}
				</a></td>
				<td>
					${pcClusteringMarket.attributedGrid.name}
				</td>
				<td>
					${pcClusteringMarket.shopName}
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.clusterMarketType, 'cluster_market_type', '')}
				</td>
				<td>
					${pcClusteringMarket.clusterMarketName}
				</td>
				<td>
					${pcClusteringMarket.groupNumber}
				</td>				
				<td>
					${pcClusteringMarket.address}
				</td>
				<td>
					${pcClusteringMarket.contactName}
				</td>
				<td>
					${pcClusteringMarket.contactPhone}
				</td>
				<td>
					${pcClusteringMarket.employeesNumber}
				</td>
				<td><a href="javascript:getMobileNumber('${pcClusteringMarket.shopName}','移动');">
					${pcClusteringMarket.mobileNumber}
				</a></td>
				<td><a href="javascript:getMobileNumber('${pcClusteringMarket.shopName}','他网');">
					${pcClusteringMarket.otherNumber}
				</a></td>
				<td>
					${pcClusteringMarket.marketShare}
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.isOverlayMobileNetwork, 'is_or_no', '')}
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.isUseBroadband, 'is_or_no', '')}
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.broadbandOperators, 'broadband_operators', '')}
				</td>
				<td>
					<fmt:formatDate value="${pcClusteringMarket.broadbandExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.isUseTelephone, 'is_or_no', '')}
				</td>
				<td>
					${fns:getDictLabel(pcClusteringMarket.telephoneOperators, 'telephone_operators', '')}
				</td>
				<td>
					<fmt:formatDate value="${pcClusteringMarket.telephoneExpirationDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pcClusteringMarket.otherProductUsage}
				</td>
				<td>
					${pcClusteringMarket.customerDemand}
				</td>
				<td>
					${pcClusteringMarket.customerManagerNumber.name}
				</td>
				<shiro:hasPermission name="pc:pcClusteringMarket:edit"><td>
    				<a href="javascript:getInfo('${pcClusteringMarket.id}');">修改</a>
					<a href="javascript:deleteInfo('${pcClusteringMarket.id}');" onclick="return confirmx('确认要删除该聚类市场信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- </div> -->
	<div class="pagination">${page}</div>
</body>
</html>