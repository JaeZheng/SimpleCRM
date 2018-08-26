<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css" />
    <%--<script type="text/javascript"--%>
            <%--src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>--%>
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--var url = "${pageContext.request.contextPath}/UserServlet";--%>
            <%--$.post(url,{--%>
                        <%--method : "init"--%>
                    <%--},--%>
                    <%--function(data) {--%>
                        <%--document.getElementById("name").innerHTML=data;--%>
                    <%--});--%>
        <%--});--%>
    <%--</script>--%>
</head>
<body>
	<center>
		<br> <br> <font color="#FFFFFF" size=20 id="name">${sessionScope.software}</font>
	</center>
</body>
</html>