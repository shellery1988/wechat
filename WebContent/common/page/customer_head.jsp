<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<%=request.getContextPath() %>/common/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/common/css/bootstrap-theme.min.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-2.0.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/bootstrap.min.js"></script>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button data-target=".navbar-collapse" data-toggle="collapse"
				class="navbar-toggle collapsed" type="button">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand">维修服务管理系统</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#about">我的申请</a></li>
				<li><a href="#about">添加申请</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
