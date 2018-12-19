<%@page import="com.atsjp.webDemo.servlet.GetPageCustServiceServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/getAllC.css" />
</head>
<body>
    <a href="<%=request.getContextPath()%>/manager/queryCustService.jsp"
       target="mainFrame"><button class="but">查询服务信息</button></a>&nbsp;&nbsp;
    <a href="<%=request.getContextPath()%>/manager/addCustService.jsp"
       target="mainFrame"><button class="but">增加服务信息</button></a>&nbsp;&nbsp;
    <c:if test="${sessionScope.user.name=='admin'}" >
        <a href="<%=request.getContextPath()%>/DeleteCustServiceServlet?method=deleteAll"
           target="mainFrame"><button class="but">一键清空</button></a>
    </c:if><br><br><br>
	<fieldset>
        <font color="#FFF">总记录：&nbsp;${requestScope.totalCount}</font>&nbsp;&nbsp;
        <font color="#FFF">总页数：&nbsp;${requestScope.lastPage}</font>
	<c:if test="${empty CustServiceList}">
		<c:if test="${!empty deleteResult}">
			<font color="#FFF">&nbsp;&nbsp;&nbsp;${requestScope.deleteResult}</font>&nbsp;&nbsp;&nbsp;<br><br>
			<a
				href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=first"><button
					class="but">返回继续查看</button></a>
		</c:if>
		<c:if test="${empty deleteResult}">
			<center>
				<font color="#FFF">信息记录为空，请添加数据后再查看！</font>
			</center>
		</c:if>
	</c:if>
	<c:if test="${!empty CustServiceList}">
		<br>
		<br>
		<br>
		<table class="bordered" bgcolor="#FFFFFF">
			<tr>
				<td>服务单号</td>
				<td>客户名称</td>
				<td>联系人</td>
				<td>联系电话</td>
				<td>服务类型</td>
				<td>服务日期</td>
				<td>预估成本</td>
				<td>实际成本</td>
				<td>客户满意度</td>
                <td>操作</td>
			</tr>
			<c:forEach items="${CustServiceList}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.customername}</td>
					<td>${item.linkman}</td>
					<td>${item.linkphone}</td>
					<td>${item.servicetype}</td>
					<td>${item.servicedate}</td>
					<td>${item.estimatedcost}</td>
					<td>${item.actualcost}</td>
					<td>${item.satisfaction}</td>
					<td>
                        <a href="<%=request.getContextPath()%>/GetCustServiceServlet?cindex=${item.id}"
                           class="mo">修改</a>
                        <a href="<%=request.getContextPath()%>/DeleteCustServiceServlet?custServiceId=${item.id}"
                           class="de">删除</a></td>
					<!-- 注意url传值中文乱码 -->
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<a
				href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=first">首页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=up">上一页</a>
			<c:if test="${requestScope.beginPage>1}">
				<a href="#">...</a>
			</c:if>
			<c:forEach begin="${requestScope.beginPage}"
				end="${requestScope.endPage}" var="num">
				<c:choose>
					<c:when test="${num==requestScope.currentPage}">
						<a
							href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=${num}"
							class="de">${num}</a>
					</c:when>
					<c:otherwise>
						<a
							href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=${num}">${num}</a>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${endPage < lastPage}">
				<a href="#">...</a>
			</c:if>
			<a
				href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=down">下一页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=last">末页</a>
		</div>
	</c:if>
	<br><br>
    </fieldset>
</body>
</html>