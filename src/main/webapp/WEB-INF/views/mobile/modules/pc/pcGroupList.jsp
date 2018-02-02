<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<!DOCTYPE >

<html>
<head>
<meta charset="utf-8">
<title>客户资源管理系统</title>
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="${ctxStatic }/jingle/css/Jingle.css">
<link rel="stylesheet" href="${ctxStatic }/mui-master/dist/css/mui.css">
<link rel="stylesheet" href="${ctxStatic }/mui-master/dist/css/mui.min.css">
</head>
<body>
	<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">ABC类集团</h1>
	</header>
	<div class="mui-content" id="content">
	
		<div id="listdiv">
				<div id="item1" class="mui-control-content mui-active">
					<ul class="mui-table-view" id="list">
							
					</ul>
				</div>
		<button type="button" id="more" class="mui-btn  mui-btn-block"  onclick="getlist();">加载更多</button>
		</div>
	</div>	
		<script type="text/javascript"
			src="${ctxStatic }/jingle/js/lib/zepto.js"></script>
		<script src="${ctxStatic }/mui-master/dist/js/mui.js"></script>
		<script type="text/javascript">
		//判断是开始进入页面还是返回当前页面，如返回则存好当前页面信息 
		$(function() {
			if(sessionStorage.mydutyListHtml) {
					var str = sessionStorage.mydutyListHtml;
					var obj = JSON.parse(str);
					document.getElementById('listdiv').innerHTML = obj.content;
					document.body.scrollTop = obj.scrolltop;
					sessionStorage.removeItem('mydutyListHtml');
					$("#list .mui-active").removeClass("mui-active");
					setLiClick();			
				} else {
					getlist();
				}
		});
		function pulldownRefresh() {
		    setTimeout(function() {
		        count = 1;//刷新并显示第一页
		        data={
		            //...,
		            page:count
		        };
		        type=1;//代表下拉刷新
		        toList(data,type);//具体取数据的方法
		    }, 100);
		}
		function pullupRefresh() {
		    setTimeout(function() {
		        count++;//翻下一页
		        data={
		            //...,
		            page:count
		        };
		        type=2;//代表上拉加载
		        toList(data,type);//具体取数据的方法
		    }, 100);
		}
			function getlist() {
				//载入
				var rows = 6;//分页大小
				var len = (($("#list li").length)/rows)+1;//当前页码
				$.ajax({
						type: "post",
						url : "${ctx}/pc/pcGroup/listApp",
						data : {
								"pageSize": rows,
								"pageNo": len
								},
						dataType : "json",
						success : function(op) {
									
								if(op) {   
					       	 	var rows = op.listentity;
					       	 	var total = op.count;
								var html = '';
								for(var i=0;i<rows.length;i++) {
									html += '<li class="mui-table-view-cell" id="'+rows[i].id+'">';
									/* html += '<div class="mui-table"><div class="mui-table-cell mui-col-xs-10"><h4 class="mui-ellipsis">'+rows[i].activityTheme+'</h4>';
									html +='<h5>活动时间：'+rows[i].activityTime+'</h5>';
									html +=' <p class="mui-h6 mui-ellipsis">'+rows[i].activityPlace+'</p> </div>'
									html +=' <div class="mui-table-cell mui-col-xs-2 mui-text-right"><span class="mui-h5">'+rows[i].statusName+'</span> </div></div>' */
									html +='<h5>集团编号：'+rows[i].groupNumber+'</h5>';
									html +='<h5>集团名称：'+rows[i].groupName+'</h5>';
									html += '</li>';
											
								}
					            $("#list").append(html); 
					            if ($("#list li").length == total) {
					   	            $("#more").css('display', 'none');
					   	        }
					   	         else {
					   	                  $("#more").css('display', 'block');
					   	              } 
					       		} else {
						       		// alert(op.msg);
						       	 } 
								},
								error : function() {
									throw justep.Error.create("加载数据失败");
								}
						});
				}
				
				//点击进入详情页面
				function setLiClick() {
					$('#list').on('click', 'li', function() {
						//存储当前页面的信息，返回时显示此时的数据信息
						var parObj = new Object();
						parObj.content = document.getElementById('listdiv').innerHTML;
						parObj.scrolltop = document.body.scrollTop;
						var str = JSON.stringify(parObj); 
						sessionStorage.mydutyListHtml = str;
						var id = this.id;
						 mui.openWindow({
							   url:"${ctx}/pc/pcGroup/formApp?id="+id
							  }); 
						 return false;
						});
					}
				 mui.init({
					swipeBack:true //启用右滑关闭功能
				});
				(function($) {
					$('#scroll').scroll({
						indicators: true //是否显示滚动条
					});	

					setLiClick();
				})(mui); 

				
			
		
			
		</script>
</body>
</html>