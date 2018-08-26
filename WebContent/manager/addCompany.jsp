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
        var companyname = document.getElementById('companyname');
        var linkman = document.getElementById('linkman');
        var linkphone = document.getElementById('linkphone');
        var address = document.getElementById('address');
        if (isnull(companyname.value)) {
            alert("公司名称不能为空!");
            return false;
        } else if (isnull(linkman.value)) {
            alert("联系人不能为空!");
            return false;
        } else if (isnull(linkphone.value)) {
            alert("联系电话不能为空!");
            return false;
        } else if (isnull(address.value)) {
            alert("办公地址不能为空!");
            return false;
        }
        var regphone = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
        if (!regphone.test(linkphone.value)) {
            alert("联系电话格式有误!");
            return false;
        }
        return true;
    }

		// 检查重复名
		$("#companyname").blur( function() {
							var reg = /^[\u4e00-\u9fa5a-zA-Z0-9]{1,100}$/;
							if (!reg.test(this.value)) {
								this.focus();
								$("#result").html("");
								return false;
							} else {
								var url = "${pageContext.request.contextPath}/AddCompanyServlet";
								$.post(url,{
											method : "checkNameExist",
											companyName : this.value
										},
										function(data) {
											if (data == "true") {
												$("#result").html("&nbsp;<font color='red'>此公司已存在</font>");
											} else {
												$("#result").html("&nbsp;<font color='blue'>公司未入数据库,可用</font>");
											}
										});
							}
		});

		//检查重复手机号码
		$("#linkphone").blur( function() {
				var reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$)/;
				if (!reg.test(this.value)) {
					this.focus();
					$("#result1").html("&nbsp;<font color='red'>联系电话格式不正确</font>");
					return false;
				} else {
					var url = "${pageContext.request.contextPath}/AddCompanyServlet";
					$.post(url,{
								method : "checkPhoneExist",
								linkPhone : this.value
								},
							function(data) {
								if (data == "true") {
									$("#result1").html("&nbsp;<font color='red'>此联系电话已存在</font>");
								} else {
									$("#result1").html("&nbsp;<font color='blue'>此联系电话可用</font>");
								}
					});
				}
		});

	});
</script>
</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/AddCompanyServlet?method=addCompany">
		<fieldset>
			<legend>客户信息</legend>
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
					<td>办公地址(*):</td>
					<td><input type="text" name="address" id="address"></td>
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