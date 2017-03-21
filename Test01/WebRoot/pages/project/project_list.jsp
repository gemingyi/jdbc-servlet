<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>测试</title>
<meta charset="UTF-8">
<!-- 此时表示根据设备的大小调整页面的显示宽度 -->
<meta name="viewport" content="width=device-width,initial-scale=1">
<!-- Bootstrap需要jQuery的支持，所以一定要导入jQuery开发包 -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<!-- Bootstrap所需要的一些组件的*.js文件 -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript" src="js/project_list.js"></script>

<!-- Bootstrap所需要的一些基础样式 -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md12">
				<!-- 定义导航条 -->
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>工程管理</strong>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover table-striped"
							id="projectTable">
							<tr>
								<td class="text-center"><input type="checkbox" id="selall"
									name="selall"></td>
								<td class="text-center"><strong>工程名称</strong></td>
								<td class="text-center"><strong>法人名称</strong></td>
								<td class="text-center"><strong>法人电话</strong></td>
								<td class="text-center"><strong>工程地址</strong></td>
								<td class="text-center"><strong>操作</strong></td>
							</tr>
						</table>
						<button class="btn btn-danger" id="deleteBtn">
							<span class="glyphicon glyphicon-trash"></span>&nbsp;删除工程
						</button>
						<div id="pageDiv" class="text-right">
							<ul class="pagination pagination-sm" id="pagecontrol"></ul>
						</div>
						<div class="alert alert-success" id="alertDiv"
							style="display: none;">
							<button class="close">&times;</button>
							<span id="alertText"></span>
						</div>
					</div>
					<div class="panel-footer">
						<strong>小葛（www.xiaoge.com）</strong>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="projectInfo">
		<div class="modal-dialog" style="width : 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">编辑新闻数据</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="myform" method="post">
						<fieldset>
							<legend><lable><span class="glyphicon glyphicon-file"></span>修改数据</lable></legend>
							<div class="form-group" id="sidDiv">
								<lable class="col-md-3 control-lable" for="sid">项目编号:</lable>
								<div class="col-md-5">
									<span id="sid"></span>
								</div>
								<div class="col-md-4" id="sidSpan"></div>
							</div>
							<div class="form-group" id="nameDiv">
								<lable class="col-md-3 control-lable" for="name">项目名称:</lable>
								<div class="col-md-5">
									<input type="text" name="name" id="name" class="form-control" placeholder="请输入项目名称">
								</div>
								<div class="col-md-4" id="nameSpan"></div>
							</div>
							<div class="form-group" id="frNameDiv">
								<lable class="col-md-3 control-lable" for="frName">法人姓名:</lable>
								<div class="col-md-5">
									<input type="text" name="frName" id="frName" class="form-control" placeholder="请输入法人姓名">
								</div>
								<div class="col-md-4" id="frNameSpan"></div>
							</div>
							<div class="form-group" id="telDiv">
								<lable class="col-md-3 control-lable" for="tel">联系电话:</lable>
								<div class="col-md-5">
									<input type="text" name="tel" id="tel" class="form-control" placeholder="请输入联系电话">
								</div>
								<div class="col-md-4" id="telSpan"></div>
							</div>
							<div class="form-group" id="addressDiv">
								<lable class="col-md-3 control-lable" for="address">工程地址:</lable>
								<div class="col-md-5">
									<input type="text" name="address" id="address" class="form-control" placeholder="请输入工程地址">
								</div>
								<div class="col-md-4" id="addressSpan"></div>
							</div>
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<button type="button" class="btn btn-primary btn-sm" id="updateBtn">修改工程信息</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success btn-sm" data-dismiss="modal">关闭编辑界面</button>
				</div>
			</div>
		</div>
	</div>		
</body>
</html>
