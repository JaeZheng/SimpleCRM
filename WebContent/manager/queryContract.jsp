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
						<input type="submit" class="but" id="buttonbin" onclick="return check()" value="搜索"></td>
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
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无相关合同信息，请您检查合同名称或对应发票抬头填写是否正确。
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
		</fieldset>
		<br><br>
</body>
</html>