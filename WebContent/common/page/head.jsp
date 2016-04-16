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
			<a href="#" class="navbar-brand">我的主页</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="#">首页</a></li>
				<li class="active"><a href="#about">用户管理</a></li>
				<li><a href="#contact">联系我</a></li>
				<li class="dropdown"><a aria-expanded="false" role="button"
					data-toggle="dropdown" class="dropdown-toggle" href="#">Dropdown
						<span class="caret"></span>
				</a>
					<ul role="menu" class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
