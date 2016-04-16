<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <html>
 <head>
   	<meta charset="UTF-8">
   	<link type="text/css" href="<%=request.getContextPath() %>/common/css/bootstrap.min.css" rel="stylesheet" />
	<link type="text/css" href="<%=request.getContextPath() %>/common/css/common.css" rel="stylesheet" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/default.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/component.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/common.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/modernizr.custom.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/opr.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
		
	</script>
</head>
<body style="min-width: 1040px;">
<div class="content">
	<form id="listFrm" action="" method="post">
	
	<table class="table table-bordered table-hover">
		<tr>
			<th width="30px" style="text-align: center;"><input type="checkbox" id="checkall"/></th>
			<th>昵称</th>
			<th>头像</th>
			<th>性别</th>
			<th>地区</th>
		</tr>
		<c:choose>
			<c:when test="${queryBean.pageDataMap != null && fn:length(queryBean.pageDataMap) > 0}">
				<c:forEach items="${queryBean.pageDataMap}" var="user" >
					<tr>
						<td class="td_center"><input type="checkbox" class="userid" value="${user.value.openid}"/></td>
						<td class="td_center">${user.value.nickname}</td>
						<td class="td_center"><img src="${user.value.headimgurl}" width="36px" height="36px"/></td>
						<td class="td_center">
							<c:if test="${user.value.sex==1}">男</c:if>
							<c:if test="${user.value.sex==2}">女</c:if>
						</td>
						<td class="td_center">
							${user.value.country }${user.value.province }${user.value.city }
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td align="center" colspan="13">暂无相关记录</td></tr>
			</c:otherwise>
		</c:choose>
	</table>
	<jsp:include page="/common/page/page_index.jsp">
		<jsp:param name="currentPage" value="${queryBean.currentPage}" />
		<jsp:param name="pageCount" value="${queryBean.pageCount }"/>
		<jsp:param name="totalRecords" value="${queryBean.totalRecords }"/>
		<jsp:param name="pageSize" value="${queryBean.pageSize }"/>
		<jsp:param name="startIndex" value="${queryBean.startIndex}"/>
		<jsp:param name="stopIndex" value="${queryBean.stopIndex}"/>
	</jsp:include>
	</form>
</div>
</body>
</html>
  