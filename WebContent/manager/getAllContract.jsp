<%@page import="com.atsjp.webDemo.servlet.GetPageContractServlet"%>
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
<a href="<%=request.getContextPath()%>/manager/queryContract.jsp"
   target="mainFrame"><button class="but">查询合同</button></a>&nbsp;&nbsp;
<a href="<%=request.getContextPath()%>/manager/addContract.jsp"
   target="mainFrame"><button class="but">增加合同</button></a>&nbsp;&nbsp;
<c:if test="${sessionScope.user.name=='admin'}" >
    <a href="<%=request.getContextPath()%>/DeleteContractServlet?method=deleteAll"
	   onclick= "if(confirm('是否真的要清空数据？')==false) return false; "
       target="mainFrame"><button class="but">一键清空</button></a>
</c:if><br><br><br>
	<fieldset>
	<font color="#FFF">总记录：&nbsp;${requestScope.totalCount}</font>&nbsp;&nbsp;
	<font color="#FFF">总页数：&nbsp;${requestScope.lastPage}</font>
	<c:if test="${empty ContractList}">
		<c:if test="${!empty deleteResult}">
			<font color="#FFF">&nbsp;&nbsp;&nbsp;${requestScope.deleteResult}</font>&nbsp;&nbsp;&nbsp;<br><br>
			<a
                    href="<%=request.getContextPath()%>/GetPageContractServlet?page=first"><button
                    class="but">返回继续查看</button></a>
		</c:if>
		<c:if test="${empty deleteResult}">
			<center>
				<font color="#FFF">信息记录为空，请添加数据后再查看！</font>
			</center>
		</c:if>
	</c:if>
	<c:if test="${!empty ContractList}">
		<br>
		<br>
		<br>
		<table class="bordered" bgcolor="#FFFFFF">
			<tr>
				<td>签合同时间</td>
				<td>合同名称</td>
				<td>对应发票抬头</td>
				<td>公司地址</td>
				<td>合同内容</td>
				<td>对应发票明细</td>
				<td>开票时间</td>
				<td>发票编号</td>
				<td>开票金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${ContractList}" var="item">
				<tr>
					<td>${item.contracttime}</td>
					<td>${item.contractname}</td>
					<td>${item.invoicetitle}</td>
					<td>${item.address}</td>
					<td>${item.contractcontent}</td>
					<td>${item.invoicedetail}</td>
					<td>${item.invoicetime}</td>
					<td>${item.invoicenumber}</td>
					<td>${item.invoiceamount}</td>
					<td>
                        <a href="<%=request.getContextPath()%>/GetContractServlet?cindex=${item.invoicenumber}"
                           class="mo">修改</a>
						<a href="<%=request.getContextPath()%>/DeleteContractServlet?id=${item.id}"
                           class="de">删除</a></td>
					<!-- 注意url传值中文乱码 -->
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<a
				href="<%=request.getContextPath()%>/GetPageContractServlet?page=first">首页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageContractServlet?page=up">上一页</a>
			<c:if test="${requestScope.beginPage>1}">
				<a href="#">...</a>
			</c:if>
			<c:forEach begin="${requestScope.beginPage}"
				end="${requestScope.endPage}" var="num">
				<c:choose>
					<c:when test="${num==requestScope.currentPage}">
						<a
							href="<%=request.getContextPath()%>/GetPageContractServlet?page=${num}"
							class="de">${num}</a>
					</c:when>
					<c:otherwise>
						<a
							href="<%=request.getContextPath()%>/GetPageContractServlet?page=${num}">${num}</a>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${endPage < lastPage}">
				<a href="#">...</a>
			</c:if>
			<a
				href="<%=request.getContextPath()%>/GetPageContractServlet?page=down">下一页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageContractServlet?page=last">末页</a>
		</div>
	</c:if>
	<br><br>
	</fieldset>
    <%
        Object addResult = request.getAttribute("addResult");
        if(addResult!=null && !"".equals(addResult)){
    %>
    <script type="text/javascript">
        alert("<%=addResult%>");
    </script>
    <%} %>
    <%
        Object deleteResult = request.getAttribute("deleteResult");
        if(deleteResult!=null && !"".equals(deleteResult)){
    %>
    <script type="text/javascript">
        alert("<%=deleteResult%>");
    </script>
    <%} %>
</body>
</html>