<%@page import="com.atsjp.webDemo.servlet.GetPageLostServlet"%>
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
    <a href="<%=request.getContextPath()%>/manager/queryLost.jsp"
       target="mainFrame"><button class="but">查询流失记录</button></a>&nbsp;&nbsp;
    <a href="<%=request.getContextPath()%>/manager/addLost.jsp"
       target="mainFrame"><button class="but">增加流失记录</button></a>&nbsp;&nbsp;
	<c:if test="${sessionScope.user.name=='admin'}" >
		<a href="<%=request.getContextPath()%>/DeleteLostServlet?method=deleteAll"
		   target="mainFrame"><button class="but">一键清空</button></a>
	</c:if><br><br><br>
	<fieldset>
        <font color="#FFF">总记录：&nbsp;${requestScope.totalCount}</font>&nbsp;&nbsp;
        <font color="#FFF">总页数：&nbsp;${requestScope.lastPage}</font>
	<c:if test="${empty LostList}">
		<c:if test="${!empty deleteResult}">
			<font color="#FFF">&nbsp;&nbsp;&nbsp;${requestScope.deleteResult}</font>&nbsp;&nbsp;&nbsp;<br><br>
			<a
				href="<%=request.getContextPath()%>/GetPageLostServlet?page=first"><button
				class="but">返回继续查看</button></a>
		</c:if>
		<c:if test="${empty deleteResult}">
			<center>
				<font color="#FFF">信息记录为空，请添加数据后再查看！</font>
			</center>
		</c:if>
	</c:if>
	<c:if test="${!empty LostList}">
		<br>
		<br>
		<br>
		<table class="bordered" bgcolor="#FFFFFF">
			<tr>
				<td>客户加入日期</td>
				<td>客户流失日期</td>
				<td>客户名称</td>
				<td>办公地址</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${LostList}" var="item">
				<tr>
					<td>${item.joindate}</td>
					<td>${item.lossdate}</td>
					<td>${item.companyname}</td>
					<td>${item.reason}</td>
					<td>
                        <a href="<%=request.getContextPath()%>/GetLostServlet?cindex=${item.id}"
                           class="mo">修改</a>
                        <a href="<%=request.getContextPath()%>/DeleteLostServlet?lostId=${item.id}"
                           class="de">删除</a></td>
					<!-- 注意url传值中文乱码 -->
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<a
				href="<%=request.getContextPath()%>/GetPageLostServlet?page=first">首页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageLostServlet?page=up">上一页</a>
			<c:if test="${requestScope.beginPage>1}">
				<a href="#">...</a>
			</c:if>
			<c:forEach begin="${requestScope.beginPage}"
				end="${requestScope.endPage}" var="num">
				<c:choose>
					<c:when test="${num==requestScope.currentPage}">
						<a
							href="<%=request.getContextPath()%>/GetPageLostServlet?page=${num}"
							class="de">${num}</a>
					</c:when>
					<c:otherwise>
						<a
							href="<%=request.getContextPath()%>/GetPageLostServlet?page=${num}">${num}</a>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${endPage < lastPage}">
				<a href="#">...</a>
			</c:if>
			<a
				href="<%=request.getContextPath()%>/GetPageLostServlet?page=down">下一页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageLostServlet?page=last">末页</a>
		</div>
	</c:if>
	<br><br>
    </fieldset>
</body>
</html>