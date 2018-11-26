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
		var customername = document.getElementById('customername');
		var linkman = document.getElementById('linkman');
		var linkphone = document.getElementById('linkphone');
		if (isnull(customername.value)) {
			alert("客户名称不能为空!");
			return false;
		} else if (isnull(linkman.value)) {
			alert("联系人不能为空!");
			return false;
		} else if (isnull(linkphone.value)) {
			alert("联系电话不能为空!");
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
			<font color="#FFFFFF">客户服务信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty CustService}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此客户信息，请检查您是否已搜索或公司名称填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty CustService}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td width="180px">客户名称</td>
						<td width="50px">联系人</td>
						<td width="180px">联系电话</td>
						<td width="180px">服务类型</td>
						<td width="50px">服务日期</td>
						<td width="50px">预估成本</td>
						<td width="50px">实际成本</td>
						<td width="50px">满意程度</td>
					</tr>
					<tr>
						<td width="180px">${CustService.customername}</td>
						<td width="50px">${CustService.linkman}</td>
						<td width="180px">${CustService.linkphone}</td>
						<td width="180px">${CustService.servicetype}</td>
						<td width="50px">${CustService.servicedate}</td>
						<td width="50px">${CustService.estimatedcost}</td>
						<td width="50px">${CustService.actualcost}</td>
						<td width="50px">${CustService.satisfaction}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

	</fieldset>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/ModifyCustServiceServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户服务信息修改栏</font>
			</legend>
			<c:choose>
				<c:when test="${empty CustService}">
					<font color="#FFFFFF">请先进行查询操作。</font>
				</c:when>
				<c:when test="${!empty CustService}">
					<table class="bordered" bgcolor="#FFFFFF">
						<tr>
							<td>客户名称(*)</td>
							<td>联系人(*)</td>
							<td>联系电话(*)</td>
							<td>服务类型</td>
							<td>服务日期</td>
							<td>预估成本</td>
							<td>实际成本</td>
							<td>满意程度</td>
						</tr>
						<tr>
							<td width="180px"><input type="text" class="input1"
								name="customername" id="customername" value="${CustService.customername}"></td>
							<td width="50px"><input type="text" class="input1"
								name="linkman" id="linkman" value="${CustService.linkman}"></td>
							<td width="180px"><input type="text" class="input1"
								name="linkphone" id="linkphone" value="${CustService.linkphone}"></td>
							<td width="180px"><input type="text" class="input1"
								name="servicetype" id="servicetype" value="${CustService.servicetype}"></td>
							<td width="50px"><input type="text" class="input1"
                                name="servicedate" id="servicedate" value="${CustService.servicedate}"></td>
							<td width="50px"><input type="text" class="input1"
								name="estimatedcost" id="estimatedcost" value="${CustService.estimatedcost}"></td>
							<td width="50px"><input type="text" class="input1"
								name="actualcost" id="actualcost" value="${CustService.actualcost}"></td>
							<td width="50px"><input type="text" class="input1"
								name="satisfaction" id="satisfaction" value="${CustService.satisfaction}"></td>
						</tr>
					</table>
					<br>
					<font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
					<table>
						<tr>
							<td><input type="hidden" id="id" name="id"
								value="${CustService.id}"> <input type="submit" onclick="return check()"
								id="buttonbin1" class="but1" value="提交"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</fieldset>
	</form>
</body>
</html>