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

	$(function() {
		$("#buttonbin1")
				.click(
						function() {
							//检查用户输入修改信息格式是否正确
                            var companyname = document.getElementById('companyname');
                            var linkman = document.getElementById('linkman');
                            var linkphone = document.getElementById('linkphone');
                            var address = document.getElementById('address');
                            if (isnull(companyname.value)){
                                alert("公司名称不能为空!");
                                return false;
                            }else if (isnull(linkman.val)){
                                alert("联系人不能为空!");
                                return false;
                            }else if (isnull(linkphone.val)){
                                alert("联系电话不能为空!");
                                return false;
                            }else if (isnull(address)){
                                alert("办公地址不能为空!");
                                return false;
                            }
                            var regphone = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
                            if (!regphone.test(linkphone.value)) {
                                alert("联系电话格式有误!");
                                return false;
                            }
                            <%--var url = "${pageContext.request.contextPath}/AddCompanyServlet";--%>
                            /*
                            //检查重复公司名称
                            var regname = /^[\u4e00-\u9fa5]{1,100}$/;
                            if (!regname.test(companyname.value)) {
                                alert("公司名称格式有误!");
                                return false;
                            }else {
                                //url add here
                                $.post(url,{
                                        method : "checkNameExist",
                                        companyName : companyname.value
                                    },
                                    function(data) {
                                        if (data == "true") {
                                            alert("此公司已存在!");
                                            return false;
                                        }
                                    });
                            }
                            //检查重复联系电话
                            var regphone = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
                            if (!regname.test(linkphone.value)) {
                                alert("联系电话格式有误!");
                                return false;
                            }else {
                                //url add here
                                $.post(url,{
                                        method : "checkPhoneExist",
                                        linkPhone : linkphone.value
                                    },
                                    function(data) {
                                        if (data == "true") {
                                            alert("此联系电话已存在!");
                                            return false;
                                        }
                                    });
                            }
                            */
							return true;
						});
	});
</script>
</head>
<body>
	<fieldset>
		<legend>
			<font color="#FFFFFF">客户信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty Company}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此客户信息，请检查您是否已搜索或公司名称填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty Company}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td width="200px">公司名称</td>
						<td width="50px">联系人</td>
						<td width="100px">联系电话</td>
						<td width="200px">办公地址</td>
					</tr>
					<tr>
						<td width="200px">${Company.companyname}</td>
						<td width="50px">${Company.linkman}</td>
						<td width="100px">${Company.linkphone}</td>
						<td width="200px">${Company.address}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

	</fieldset>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/ModifyCompanyServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户信息修改栏</font>
			</legend>
			<c:choose>
				<c:when test="${empty Company}">
					<font color="#FFFFFF">请先进行查询操作。</font>
				</c:when>
				<c:when test="${!empty Company}">
					<table class="bordered" bgcolor="#FFFFFF">
						<tr>
							<td>公司名称(*)</td>
							<td>联系人(*)</td>
							<td>联系电话(*)</td>
							<td>办公地址(*)</td>
						</tr>
						<tr>
							<td width="200px"><input type="text" class="input1"
								name="companyname" id="companyname" value="${Company.companyname}"></td>
							<td width="50px"><input type="text" class="input1"
								name="linkman" id="linkman" value="${Company.linkman}"></td>
							<td width="100px"><input type="text" class="input1"
								name="linkphone" id="linkphone" value="${Company.linkphone}"></td>
							<td width="200px"><input type="text" class="input1"
								name="address" id="address" value="${Company.address}"></td>
						</tr>
					</table>
					<br>
					<font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
					<table>
						<tr>
							<td><input type="hidden" id="id" name="id"
								value="${Company.id}"> <input type="submit"
								id="buttonbin1" class="but1" value="提交"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</fieldset>
	</form>
</body>
</html>