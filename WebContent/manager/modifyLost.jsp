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
	href="<%=request.getContextPath()%>/css/modifyC.css" />
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

    function check() {
        //检查用户输入修改信息格式是否正确
        var joindate = document.getElementById('joindate');
        var lossdate = document.getElementById('lossdate');
        var joindate = document.getElementById('joindate');
        var reason = document.getElementById('reason');
        if (isnull(lossdate.value)) {
            alert("流失日期不能为空!");
            return false;
        } else if (isnull(joindate.value)) {
            alert("客户名称不能为空!");
            return false;
        } else if (isnull(reason.value)) {
            alert("流失原因不能为空!");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
	<fieldset>
		<legend>
			<font color="#FFFFFF">客户流失信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty Lost}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此客户信息，请检查您是否已搜索或客户加入日期填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty Lost}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td width="200px">客户加入日期</td>
						<td width="50px">客户流失日期</td>
						<td width="100px">联系电话</td>
						<td width="200px">流失原因</td>
					</tr>
					<tr>
						<td width="200px">${Lost.joindate}</td>
						<td width="50px">${Lost.lossdate}</td>
						<td width="100px">${Lost.companyname}</td>
						<td width="200px">${Lost.reason}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

	</fieldset>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/ModifyLostServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户流失信息修改栏</font>
			</legend>
			<c:choose>
				<c:when test="${empty Lost}">
					<font color="#FFFFFF">请先进行查询操作。</font>
				</c:when>
				<c:when test="${!empty Lost}">
					<table class="bordered" bgcolor="#FFFFFF">
						<tr>
							<td>客户加入日期(*)</td>
							<td>客户流失日期(*)</td>
							<td>联系电话(*)</td>
							<td>流失原因(*)</td>
						</tr>
						<tr>
							<td width="200px"><input type="text" class="input1"
								name="joindate" id="joindate" value="${Lost.joindate}"></td>
							<td width="50px"><input type="text" class="input1"
								name="lossdate" id="lossdate" value="${Lost.lossdate}"></td>
							<td width="100px"><input type="text" class="input1"
								name="companyname" id="companyname" value="${Lost.companyname}"></td>
							<td width="200px"><input type="text" class="input1"
								name="reason" id="reason" value="${Lost.reason}"></td>
						</tr>
					</table>
					<br>
					<font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
					<table>
						<tr>
							<td><input type="hidden" id="id" name="id"
								value="${Lost.id}"> <input type="submit" onclick="return check()"
								id="buttonbin1" class="but1" value="提交"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</fieldset>
	</form>
</body>
</html>