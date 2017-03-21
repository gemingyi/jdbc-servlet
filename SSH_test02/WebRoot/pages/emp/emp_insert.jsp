 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "/EmpAction/insert.action";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>emp增加页面</title>
	<!-- 此时表示根据设备的大小调整页面的显示宽度 -->
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<!-- Bootstrap需要jQuery的支持，所以一定要导入jQuery开发包 -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!-- 导入jquery validate.js -->
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<!-- 导入自己的js文件 -->
	<script type="text/javascript" src="js/emp_insert.js"></script>
	<!-- Bootstrap所需要的一些组件的*.js文件 -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<!-- Bootstrap所需要的一些基础样式 -->
	<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
  </head>
  
  <body>
    <div class="container">
    	<div class="row">
    		<div class="col-md-12">
    			<div class="panel panel-success">
    				<div class="panel-heading">
    					<strong>雇员入职</strong>
    				</div>
    				
    				<div class="panel-body">
    					<form class="form-horizontal" action="<%=insertUrl%>" id="myform" method="post">
    						<fieldset>
    							<legend>
									<label><span class="glyphicon glyphicon-file"></span>增加工程</label>
								</legend>
								<div class="form-group" id="idDiv">
									<label class="col-md-3 control-lable" for="id">雇员编号:</label>
									<div class="col-md-5">
										<input type="text" id="id" name="id" class="form-control" 
											placeholder="请输入雇员编号"/>
									</div>
									<div class="col-md-4" id="idSpan"></div>
								</div>
								<div class="form-group" id="nameDiv">
									<label class="col-md-3 control-lable" for="name">姓名:</label>
									<div class="col-md-5">
										<input type="text" id="name" name="name" class="form-control" 
											placeholder="请输入雇员姓名"/>
									</div>
									<div class="col-md-4" id="nameSpan"></div>
								</div>
								<div class="form-group" id="ageDiv">
									<label class="col-md-3 control-lable" for="age">年龄:</label>
									<div class="col-md-5">
										<input type="text" id="age" name="age" class="form-control" 
											placeholder="请输入雇员年龄"/>
									</div>
									<div class="col-md-4" id="ageSpan"></div>
								</div>
								<div class="form-group" id="birthdayDiv">
									<label class="col-md-3 control-lable" for="birthday">雇用日期:</label>
									<div class="col-md-5">
										<input type="text" id="birthday" name="birthday" class="form-control" 
											placeholder="请输入入职时间"/>
									</div>
									<div class="col-md-4" id="birthdaySpan"></div>
								</div>
								<div class="form-group" id="salDiv">
									<label class="col-md-3 control-lable" for="sal">雇员薪资:</label>
									<div class="col-md-5">
										<input type="text" id="sal" name="sal" class="form-control" 
											placeholder="请输入雇员佣金"/>
									</div>
									<div class="col-md-4" id="salSpan"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">增加</button>
										<button type="reset" class="btn btn-warning">重置</button>
										<a href="<%=basePath %>/pages/emp/emp_list.jsp" class="btn btn-info">
											<span class="glyphicon glyphicon-list">&nbsp;查看全部数据</span></a>
									</div>
								</div>
    						</fieldset>
    					</form>
    				</div>
    				
    				<div class="panel-footer">
    					<strong>小葛（www.xiaoge.com）</strong>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
</html>
