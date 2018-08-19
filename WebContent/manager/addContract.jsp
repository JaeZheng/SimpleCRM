<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/addContract.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#bubmitBtn")
				.click(
						function() {
							return true;
						});
        //检查重复发票编号
        $("#invoicenumber").blur( function() {
            var url = "${pageContext.request.contextPath}/AddCompanyServlet";
            $.post(url,{
                    method : "checkInvoiceExist",
                    invoiceNumber : this.value
                },
                function(data) {
                    if (data == "true") {
                        $("#result1").html("&nbsp;<font color='red'>此发票编号已存在</font>");
                    } else {
                        $("#result1").html("&nbsp;<font color='blue'>此发票编号可用</font>");
                    }
                });
        });
	});
</script>
</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/AddContractServlet?method=addContract">
		<fieldset>
			<legend>合同信息</legend>
			<table align="center">
				<tr>
					<td>签合同时间(*):</td>
					<td><input type="text" name="contracttime" id="contracttime"><span
						id="result"></span></td>
				</tr>
				<tr>
					<td>合同名称(*):</td>
					<td><input type="text" name="contractname" id="contractname"></td>
				</tr>
				<tr>
					<td>对应发票抬头(*):</td>
					<td><input type="text" name="invoicetitle" id="invoicetitle"><span
							id="result1"></span></td>
				</tr>
				<tr>
					<td>办公地址(*):</td>
					<td><input type="text" name="address" id="address"></td>
				</tr>
                <tr>
                    <td>合同内容(*):</td>
                    <td><input type="text" name="contractcontent" id="contractcontent"></td>
                </tr>
                <tr>
                    <td>对应发票明细(*):</td>
                    <td><input type="text" name="invoicedetail" id="invoicedetail"></td>
                </tr>
                <tr>
                    <td>开票时间(*):</td>
                    <td><input type="text" name="invoicetime" id="invoicetime"></td>
                </tr>
                <tr>
                    <td>发票编号(*):</td>
                    <td><input type="text" name="invoicenumber" id="invoicenumber"></td>
                </tr>
                <tr>
                    <td>开票金额(*):</td>
                    <td><input type="text" name="invoiceamount" id="invoiceamount"></td>
                </tr>
				<tr align="center">
					<td colspan="2"><br> <br> <input type="submit"
													 name="button" id="bubmitBtn" class="but" value="提交"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>