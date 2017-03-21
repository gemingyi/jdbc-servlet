<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String insertUrl = basePath + "/pages/project/ProjectServlet/insert";
%>

<html>
<head>
	<base href="<%=basePath%>">
	<title>工程监管系统</title>
	<meta charset="UTF-8">
    <!-- 此时表示根据设备的大小调整页面的显示宽度 -->
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!-- Bootstrap需要jQuery的支持，所以一定要导入jQuery开发包 -->
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap所需要的一些组件的*.js文件 -->
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <!-- Bootstrap所需要的一些基础样式 -->
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-mdl2">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>添加工程</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="<%=insertUrl%>" id="myform" method="post">
						<fieldset>
							<legend>
								<label><span class="glyphicon glyphicon-file"></span>增加工程</label>
							</legend>
							<div class="form-group" id="nameDiv">
								<label class="col-md-3 control-lable" for="name">工程名称:</label>
								<div class="col-md-5">
									<input type="text" id="name" name="name" class="form-control"
										placeholder="请输工程名称">
								</div>
								<div class="col-md-4" id="nameSpan"></div>
							</div>
							<div class="form-group" id="frNameDiv">
								<label class="col-md-3 control-lable" for="frName">法人姓名:</label>
								<div class="col-md-5">
									<input type="text" id="frName" name="frName"
										class="form-control" placeholder="请输法人姓名">
								</div>
								<div class="col-md-4" id="frNameSpan"></div>
							</div>
							<div class="form-group" id="telDiv">
								<label class="col-md-3 control-lable" for="tel">联系电话:</label>
								<div class="col-md-5">
									<input type="text" id="tel" name="tel" class="form-control"
										placeholder="请输联系电话">
								</div>
								<div class="col-md-4" id="telSpan"></div>
							</div>
							<div class="form-group" id="addressDiv">
								<label class="col-md-3 control-lable" for="address">工程地址:</label>
								<div class="col-md-5">
									<input type="text" id="address" name="address"
										class="form-control" placeholder="请输工程地址">
								</div>
								<div class="col-md-4" id="addressSpan"></div>
							</div>
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<button type="submit" class="btn btn-primary">发布</button>
									<button type="reset" class="btn btn-warning">重置</button>
									<a href="<%=basePath%>/pages/project/project_list.jsp" class="btn btn-info">
                                            <span class="glyphicon glyphicon-list"></span>&nbsp;查看全部数据</a>
								</div>
							</div>
						</fieldset>
						</form>
					</div>
				</div>
				<div class="panel-footer">
					<strong>小葛（www.xiaoge.com）</strong>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
