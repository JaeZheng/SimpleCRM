<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/addCompany.css" />
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
        var companyname = document.getElementById('companyname');
        var reason = document.getElementById('reason');
        if (isnull(lossdate.value)) {
            alert("流失日期不能为空!");
            return false;
        } else if (isnull(companyname.value)) {
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
	<form method="post"
		action="<%=request.getContextPath()%>/AddLostServlet?method=addLost">
		<fieldset>
			<legend>客户流失管理信息</legend>
			<table align="center">
				<tr>
					<td>客户加入日期:</td>
					<td><input type="text" name="joindate" id="joindate"><span
						id="result"></span></td>
				</tr>
				<tr>
					<td>客户流失日期(*):</td>
					<td><input type="text" name="lossdate" id="lossdate"></td>
				</tr>
				<tr>
					<td>客户名称(*):</td>
					<td><input type="text" name="companyname" id="companyname"><span
							id="result1"></span></td>
				</tr>
				<tr>
					<td>流失原因(*):</td>
					<td><input type="text" name="reason" id="reason"></td>
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