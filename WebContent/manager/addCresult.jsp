<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/addC.css" />
</head>
<body>
	<font color="FFFFFF">${requestScope.message}</font>
	<br>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/manager/addCompany.jsp"><button class="but">返回继续添加</button></a><br>
	<a href="<%=request.getContextPath()%>/GetPageCompanyServlet?page=0"><button class="but">查看已添加的信息</button></a>
</body>
</html>