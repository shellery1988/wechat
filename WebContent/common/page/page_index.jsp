<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/css/common.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/css/main.css">
<script type="text/javascript">
	$(function(){
		$("#pagesizeSel").change(function(){
			document.getElementById("currentPage").value = 1;
			document.getElementById("pageSize").value = $(this).find("option:selected").val();
			doSubmit();
		});
	});
	function locatePage(index){
		document.getElementById("currentPage").value = index;
     	doSubmit();
	}
	
	function Previous(){
		var currentPage = ${param.currentPage};
		if(currentPage>1){
			return locatePage(currentPage-1);
		}
	}
	
	function Next(){
		var currentPage = ${param.currentPage};
		var pageCount = ${param.pageCount};
		if(currentPage>0 && currentPage<pageCount){
			return locatePage(currentPage+1);
		}
	}
	
</script>
<fmt:parseNumber value="${param.currentPage}" type="number" var="currentPage"></fmt:parseNumber>
<fmt:parseNumber value="${param.pageCount}" type="number" var="pageCount"></fmt:parseNumber>
<fmt:parseNumber value="${param.pageSize}" type="number" var="pageSize"></fmt:parseNumber>
<fmt:parseNumber value="${param.totalRecords}" type="number" var="totalRecords"></fmt:parseNumber>
<fmt:parseNumber value="${param.startIndex}" type="number" var="startIndex"></fmt:parseNumber>
<fmt:parseNumber value="${param.stopIndex}" type="number" var="stopIndex"></fmt:parseNumber>
<input type="hidden" id="currentPage" name="queryBean.currentPage" value="${currentPage }" />
<input type="hidden" id="pageSize" name="queryBean.pageSize" value="${pageSize }" />
<div align="center">
  <div class="pageindex">
  	
    <ul>
    	<li class="first-child"><span>共${totalRecords }条</span></li>
        <li <c:if test="${currentPage==1}">class="disabled"</c:if> ><a href="javascript:void(0);" onclick="return locatePage(1)" aria-label="Previous">
	        	首页
	      </a></li>
        <li <c:if test="${currentPage==1}">class="disabled"</c:if> ><a href="javascript:void(0);" onclick="Previous()">上一页</a></li>
   		<c:if test="${currentPage>5}">
			<li><a href="javascript:void(0)" onclick="return locatePage('${startIndex-5}')">...</a></li>
		</c:if>
		
   		<c:forEach begin="${startIndex}" end="${stopIndex}" var="i">
   			<c:if test="${i<=pageCount}">
   				<li <c:if test="${currentPage==i }">class="active"</c:if>>
					<a href="javascript:void(0)" onclick="return locatePage(${i})">${i}</a>
				</li>&nbsp;
   			</c:if>
		</c:forEach>
		<c:if test="${stopIndex < pageCount}">
			<li><a href="javascript:void(0)" onclick="return locatePage('${stopIndex+1}')">...</a></li>
		</c:if>
        <li <c:if test="${currentPage==pageCount}">class="disabled"</c:if> ><a href="javascript:void(0);" onclick="Next()">下一页</a></li>
        <li <c:if test="${currentPage==pageCount}">class="disabled"</c:if> ><a href="javascript:void(0);" onclick="return locatePage(${pageCount})" aria-label="Next">
	        末页
	     </a></li>
        <li class="last-child">
        	<span>
        		每页&nbsp;<select id="pagesizeSel" style="margin-top: -5px;width:40px">
					<option <c:if test="${pageSize==5 }">selected='selected'</c:if> >5</option>
					<option <c:if test="${pageSize==10 }">selected='selected'</c:if>>10</option>
					<option <c:if test="${pageSize==20 }">selected='selected'</c:if>>20</option>
				</select>&nbsp;条
        	</span>
        </li>
    </ul>
  </div>
</div>
<br/>