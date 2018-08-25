<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入css样式的时候引入绝对路径 -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/about.css" />
</head>
<body>
<p align="center"><strong>关于我们</strong><br></p>
<table class="bordered" bgcolor="#FFFFFF">
    <tr>
        <td>软件名称</td>
        <td>${requestScope.software}</td>
    </tr>
    <tr>
        <td>版权所属</td>
        <td>${requestScope.banquan}</td>
    </tr>
    <tr>
        <td>办公地址</td>
        <td>${requestScope.address}</td>
    </tr>
    <tr>
        <td>联系人</td>
        <td>${requestScope.linkman}</td>
    </tr>
    <tr>
        <td>联系方式</td>
        <td>${requestScope.linkphone}</td>
    </tr>
</table>
</body>
</html>