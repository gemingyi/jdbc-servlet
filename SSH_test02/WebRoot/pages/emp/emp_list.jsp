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
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<!-- 此时表示根据设备的大小调整页面的显示宽度 -->
<meta name="viewport" content="width=device-width,initial-scale=1">
<!-- Bootstrap需要jQuery的支持，所以一定要导入jQuery开发包 -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<!-- Bootstrap所需要的一些组件的*.js文件 -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!-- 自定义的一些*.js文件 -->
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript" src="js/emp_list.js"></script>
<!-- Bootstrap所需要的一些基础样式 -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md12">
				<!-- 定义导航条 -->
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>雇员管理</strong>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-striped"
							id="empTable">
							<tr>
								<td class="text-center"><input type="checkbox" id="selall"
									name="selall"></td>
								<td class="text-center"><strong>雇员编号</strong></td>
								<td class="text-center"><strong>姓名</strong></td>
								<td class="text-center"><strong>年龄</strong></td>
								<td class="text-center"><strong>雇佣日期</strong></td>
								<td class="text-center"><strong>佣金</strong></td>
								<td class="text-center"><strong>操作</strong></td>
							</tr>
						</table>
						<button class="btn btn-danger" id="deleteBtn">
							<span class="glyphicon glyphicon-trash"></span>&nbsp;解聘雇员
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
	
	<div class="modal" id="empInfo">
		<div class="modal-dialog" style="width : 800 px;">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">编辑新闻数据</h4>
				</div>
				
				<div class="modal-body">
					<form class="form-horizontal" id="myform" method="post">
						<fieldset>
							<legend><label><span class="glyphicon glyphicon-file"></span>修改数据</label> </legend>
							<div class="form-group" id="sidDiv">
								<label class="col-md-3 control-lable" for="sid">雇员编号:</label>
								<div class="col-md-5">
									<input type="text" name="sid" id="sid" class="form-control" placeholder="请输入雇员编号">
								</div>
								<div class="col-md-4" id="sidSpan"></div>
							</div>
							<div class="form-group" id="nameDiv">
								<label class="col-md-3 control-lable" for="name">姓名:</label>
								<div class="col-md-5">
									<input type="text" name="name" id="name" class="form-control" placeholder="请输入雇佣姓名">
								</div>
								<div class="col-md-4" id="nameSpan"></div>
							</div>
							<div class="form-group" id="ageDiv">
								<label class="col-md-3 control-lable" for="age">年龄:</label>
								<div class="col-md-5">
									<input type="text" name="age" id="age" class="form-control" placeholder="请输入雇员年龄">
								</div>
								<div class="col-md-4" id="ageSpan"></div>
							</div>
							<div class="form-group" id="birthdayDiv">
								<label class="col-md-3 control-lable" for="birthday">雇用日期:</label>
								<div class="col-md-5">
									<input type="text" name="birthday" id="birthday" class="form-control" placeholder="请输入雇佣日期">
								</div>
								<div class="col-md-4" id="birthdaySpan"></div>
							</div>
							<div class="form-group" id="salDiv">
								<label class="col-md-3 control-lable" for="sal">佣金:</label>
								<div class="col-md-5">
									<input type="text" name="sal" id="sal" class="form-control" placeholder="请输入雇员佣金">
								</div>
								<div class="col-md-4" id="salSpan"></div>
							</div>
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<button tupe="button" class="btn btn-primary btn-sm" id="updateBtn">修改雇员信息</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success btn-sm" data-dismiss="modal">关闭编辑窗口</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
