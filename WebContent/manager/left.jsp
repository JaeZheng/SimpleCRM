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
	href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
	<table align="center" cellpadding="10" cellspacing="10">
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/GetPageCompanyServlet?page=0"
				target="mainFrame" class="xyz">全部客户信息</a></td>
		</tr>
		<%--<tr>--%>
			<%--<td><a--%>
				<%--href="<%=request.getContextPath()%>/manager/addCompany.jsp"--%>
				<%--target="mainFrame">增加客户</a></td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<td><a--%>
				<%--href="<%=request.getContextPath()%>/manager/queryCompany.jsp"--%>
				<%--target="mainFrame">查询客户</a></td>--%>
		<%--</tr>--%>
		<tr>
			<td><a
					href="<%=request.getContextPath()%>/GetPageContractServlet?page=0"
					target="mainFrame" class="xyz">全部合同信息</a></td>
		</tr>
		<%--<tr>--%>
			<%--<td><a--%>
					<%--href="<%=request.getContextPath()%>/manager/addContract.jsp"--%>
					<%--target="mainFrame">增加合同</a></td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<td><a--%>
					<%--href="<%=request.getContextPath()%>/manager/queryContract.jsp"--%>
					<%--target="mainFrame">查询合同</a></td>--%>
		<%--</tr>--%>
		<tr>
			<td><a
					href="<%=request.getContextPath()%>/GetPageCustServiceServlet?page=0"
					target="mainFrame" class="xyz">客户服务信息</a></td>
		</tr>
		<tr>
			<td><a
					href="<%=request.getContextPath()%>/GetPageOpinionServlet?page=0"
					target="mainFrame" class="xyz">意见反馈管理</a></td>
		</tr>
		<tr>
			<td><a
					href="<%=request.getContextPath()%>/GetPageLostServlet?page=0"
					target="mainFrame" class="xyz">客户流失管理</a></td>
		</tr>
		<tr>
			<td><a
					href="<%=request.getContextPath()%>/UserServlet?method=aboutUs"
					target="mainFrame">关于我们</a></td>
		</tr>
        <c:if test="${sessionScope.user.name=='admin'}" >
            <tr>
                <td><a
                        href="<%=request.getContextPath()%>/manager/modifyAboutUs.jsp"
                        target="mainFrame">系统设置</a></td>
            </tr>
        </c:if>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/UserServlet?method=logout"
				target="_top">退出系统</a></td>
		</tr>
	</table>
</body>
</html>