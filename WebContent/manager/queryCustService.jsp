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
	href="<%=request.getContextPath()%>/css/queryCompany.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
<script type="text/javascript">

	function isnull(val) {
		var str = val.replace(/(^\s*)|(\s*$)/g, '');//把val首尾的空格去掉。

		if (str == '' || str == undefined || str == null) {//输入框中输入空格也为空
			return true;
		} else {
			return false;
		}
	}

	function check(){
		//检查用户输入修改信息格式是否正确
		var index = document.getElementById('index');

		if (isnull(index.value)) {
			alert("搜索信息不能为空!");
			return false;
		} else{
			return true;
		}
	}

</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/QueryCustServiceServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户服务信息查找栏</font>
			</legend>
			<table class="enen">
				<tr>
					<td><input type="text" class="input"
						placeholder="请输入你要查询的服务单号或者客户名称" id="index" name="cindex">
						<input type="submit" class="but" id="buttonbin" onclick="return check()" value="搜索"></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<br>
	<br>
	<fieldset>
		<legend>
			<font color="#FFFFFF">客户服务信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty CustServiceList}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无相关客户服务信息，请您检查服务单号或对客户名称填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty CustServiceList}">
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
                                <a href="<%=request.getContextPath()%>/DeleteCustServiceServlet?id=${item.id}"
                                   class="de">删除</a>
                            </td>
						</tr>
					</c:forEach>
				</table>
                <div class="page">
                    <a
                            href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=first">首页</a>
                    <a
                            href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=up">上一页</a>
                    <c:if test="${requestScope.beginPage>1}">
                        <a href="#">...</a>
                    </c:if>
                    <c:forEach begin="${requestScope.beginPage}"
                               end="${requestScope.endPage}" var="num">
                        <c:choose>
                            <c:when test="${num==requestScope.currentPage}">
                                <a
                                        href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=${num}"
                                        class="de">${num}</a>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=${num}">${num}</a>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    <c:if test="${endPage < lastPage}">
                        <a href="#">...</a>
                    </c:if>
                    <a
                            href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=down">下一页</a>
                    <a
                            href="<%=request.getContextPath()%>/QueryCustServiceServlet?page=last">末页</a>
                </div>
			</c:when>
		</c:choose>
		</fieldset>
		<br><br>
</body>
</html>