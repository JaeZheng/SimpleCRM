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
		var companyname = document.getElementById('companyname');
		var linkman = document.getElementById('linkman');
		var linkphone = document.getElementById('linkphone');
		var opiniondetail = document.getElementById('opiniondetail');
		if (isnull(companyname.value)) {
			alert("客户名称不能为空!");
			return false;
		} else if (isnull(linkman.value)) {
			alert("联系人不能为空!");
			return false;
		} else if (isnull(linkphone.value)) {
			alert("联系电话不能为空!");
			return false;
		} else if (isnull(opiniondetail.value)) {
			alert("意见内容不能为空!");
			return false;
		}
		var regphone = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
		if (!regphone.test(linkphone.value)) {
			alert("联系电话格式有误!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<fieldset>
		<legend>
			<font color="#FFFFFF">客户意见反馈信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty Opinion}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此意见单信息，请检查您是否已搜索或意见单号填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty Opinion}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td width="50px">意见单号</td>
						<td width="200px">客户名称</td>
						<td width="50px">联系人</td>
						<td width="100px">联系电话</td>
						<td width="200px">详细意见内容</td>
						<td width="50px">解决状态</td>
					</tr>
					<tr>
						<td width="50px">${Opinion.id}</td>
						<td width="200px">${Opinion.companyname}</td>
						<td width="50px">${Opinion.linkman}</td>
						<td width="100px">${Opinion.linkphone}</td>
						<td width="200px">${Opinion.opiniondetail}</td>
						<td width="50px">${Opinion.opinionstate}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

	</fieldset>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/ModifyOpinionServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户意见反馈信息修改栏</font>
			</legend>
			<c:choose>
				<c:when test="${empty Opinion}">
					<font color="#FFFFFF">请先进行查询操作。</font>
				</c:when>
				<c:when test="${!empty Opinion}">
					<table class="bordered" bgcolor="#FFFFFF">
						<tr>
							<td>客户名称(*)</td>
							<td>联系人(*)</td>
							<td>联系电话(*)</td>
							<td>详细意见内容(*)</td>
							<td>解决状态</td>
						</tr>
						<tr>
							<td width="200px"><input type="text" class="input1"
								name="companyname" id="companyname" value="${Opinion.companyname}"></td>
							<td width="50px"><input type="text" class="input1"
								name="linkman" id="linkman" value="${Opinion.linkman}"></td>
							<td width="100px"><input type="text" class="input1"
								name="linkphone" id="linkphone" value="${Opinion.linkphone}"></td>
							<td width="200px"><input type="text" class="input1"
								name="opiniondetail" id="opiniondetail" value="${Opinion.opiniondetail}"></td>
							<td width="50px"><input type="text" class="input1"
								name="opinionstate" id="opinionstate" value="${Opinion.opinionstate}"></td>
						</tr>
					</table>
					<br>
					<font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
					<table>
						<tr>
							<td><input type="hidden" id="id" name="id"
								value="${Opinion.id}"> <input type="submit" onclick="return check()"
								id="buttonbin1" class="but1" value="提交"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</fieldset>
	</form>
</body>
</html>