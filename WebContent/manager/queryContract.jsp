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
	$(function() {
		$("#buttonbin").click(function() {
			//检查输入信息是否不为空钩
			//alet("--");
			var $cindexEle = $("#index");
			var reg = /\s/;
			if (!reg.test($cindexEle)) {
				alert("输入信息为空，请重新输入");
				//$cindexEle[0].focus();
				return false;
			}
			return true;
		});

		$("#buttonbin1")
				.click(
						function() {
							//检查用户输入修改信息格式是否正确
                            //检查姓名: 姓名只能输入中文且不能为空
							//alert("--");
							/*
							var $nameEle = $("#linkman");
							var reg = /^[\u4e00-\u9fa5]{1,5}$/;
							if (!reg.test($nameEle.val())) {
								alert("姓名只能输入中文且不能为空,最少1个汉字最多5个汉字");
								$nameEle[0].focus();
								return false;
							}

							//手机号码必须为11位
							var $phoneEle = $("#linkphone");
							reg = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
							if (!reg.test($phoneEle.val())) {
								$phoneEle[0].focus();
								alert("手机格式不正确!");
								return false;
							}
                            */
							return true;
						});

	});
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/QueryContractServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">合同信息查找栏</font>
			</legend>
			<table class="enen">
				<tr>
					<td><input type="text" class="input"
						placeholder="请输入你要查询的合同名称或者对应发票抬头" id="index" name="cindex">
						<input type="submit" class="but" id="buttonbin" value="搜索"></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<br>
	<br>
	<fieldset>
		<legend>
			<font color="#FFFFFF">合同信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty ContractList}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无相关合同信息，请您检查公司名称或联系人填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty ContractList}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td>签合同时间</td>
						<td>合同名称</td>
						<td>对应发票抬头</td>
						<td>公司地址</td>
						<td>合同内容</td>
						<td>对应发票明细</td>
						<td>开票时间</td>
						<td>发票编号</td>
						<td>开票金额</td>
					</tr>
					<c:forEach items="${ContractList}" var="item">
						<tr>
							<td>${item.contracttime}</td>
							<td>${item.contractname}</td>
							<td>${item.invoicetitle}</td>
							<td>${item.address}</td>
							<td>${item.contractcontent}</td>
							<td>${item.invoicedetail}</td>
							<td>${item.invoicetime}</td>
							<td>${item.invoicenumber}</td>
							<td>${item.invoiceamount}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/GetContractServlet?cindex=${item.invoicenumber}"
                                   class="mo">修改</a>
                                <a href="<%=request.getContextPath()%>/DeleteContractServlet?id=${item.id}"
                                   class="de">删除</a>
                            </td>
						</tr>
					</c:forEach>
				</table>
                <div class="page">
                    <a
                            href="<%=request.getContextPath()%>/QueryContractServlet?page=first">首页</a>
                    <a
                            href="<%=request.getContextPath()%>/QueryContractServlet?page=up">上一页</a>
                    <c:if test="${requestScope.beginPage>1}">
                        <a href="#">...</a>
                    </c:if>
                    <c:forEach begin="${requestScope.beginPage}"
                               end="${requestScope.endPage}" var="num">
                        <c:choose>
                            <c:when test="${num==requestScope.currentPage}">
                                <a
                                        href="<%=request.getContextPath()%>/QueryContractServlet?page=${num}"
                                        class="de">${num}</a>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="<%=request.getContextPath()%>/QueryContractServlet?page=${num}">${num}</a>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    <c:if test="${endPage < lastPage}">
                        <a href="#">...</a>
                    </c:if>
                    <a
                            href="<%=request.getContextPath()%>/QueryContractServlet?page=down">下一页</a>
                    <a
                            href="<%=request.getContextPath()%>/QueryContractServlet?page=last">末页</a>
                </div>
			</c:when>
		</c:choose>
		<br><br>
</body>
</html>