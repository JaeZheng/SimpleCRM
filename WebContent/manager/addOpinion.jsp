<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/addOpinion.css" />
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
        var opiniondetail  = document.getElementById('opiniondetail');
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
	<form method="post"
		action="<%=request.getContextPath()%>/AddOpinionServlet?method=addOpinion">
		<fieldset>
			<legend>客户意见反馈信息</legend>
			<table align="center">
				<tr>
					<td>公司名称(*):</td>
					<td><input type="text" name="companyname" id="companyname"><span
						id="result"></span></td>
				</tr>
				<tr>
					<td>联系人(*):</td>
					<td><input type="text" name="linkman" id="linkman"></td>
				</tr>
				<tr>
					<td>联系电话(*):</td>
					<td><input type="text" name="linkphone" id="linkphone"><span
							id="result1"></span></td>
				</tr>
				<tr>
					<td>详细意见内容(*):</td>
					<td><input type="text" name="opiniondetail" id="opiniondetail"></td>
				</tr>
				<tr>
					<td>解决状态:</td>
					<td><input type="text" name="opinionstate" id="opinionstate"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><br> <br> <input type="submit" onclick="return check()"
													 name="button" id="bubmitBtn" class="but" value="提交"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>