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
          href="<%=request.getContextPath()%>/css/modifyContract.css" />
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
            var contracttime = document.getElementById('contracttime');
            var invoicedetail = document.getElementById('invoicedetail');
            var contractname = document.getElementById('contractname');
            var invoicetime = document.getElementById('invoicetime');
            var invoicetitle = document.getElementById('invoicetitle');
            var invoicenumber = document.getElementById('invoicenumber');
            var address = document.getElementById('address');
            var invoiceamount = document.getElementById('invoiceamount');
            var contractcontent = document.getElementById('contractcontent');

            if (isnull(contracttime.value)) {
                alert("合同时间不能为空!");
                return false;
            } else if (isnull(invoicedetail.value)) {
                alert("发票明细不能为空!");
                return false;
            } else if (isnull(contractname.value)) {
                alert("合同名称不能为空!");
                return false;
            } else if (isnull(invoicetime.value)) {
                alert("开票日期不能为空!");
                return false;
            } else if (isnull(invoicetitle.value)) {
                alert("发票抬头不能为空!");
                return false;
            } else if (isnull(invoicenumber.value)) {
                alert("发票编号不能为空!");
                return false;
            } else if (isnull(address.value)) {
                alert("办公地址不能为空!");
                return false;
            } else if (isnull(invoiceamount.value)) {
                alert("发票金额不能为空!");
                return false;
            } else if (isnull(contractcontent.value)) {
                alert("发票内容不能为空!");
                return false;
            }
        }
    </script>
</head>
<body>
<fieldset>
    <legend>
        <font color="#FFFFFF">合同信息显示栏</font>
    </legend>
    <c:choose>
        <c:when test="${empty Contract}">
            <font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此合同信息，请检查您是否已搜索或发票编号填写是否正确。
            </font>
        </c:when>
        <c:when test="${!empty Contract}">
            <table class="bordered" bgcolor="#FFFFFF">
                <tr>
                    <td width="60px">签合同时间</td>
                    <td width="180px">合同名称</td>
                    <td width="180px">对应发票抬头</td>
                    <td width="50px">公司地址</td>
                    <td width="50px">合同内容</td>
                    <td width="80px">对应发票明细</td>
                    <td width="50px">开票时间</td>
                    <td width="50px">发票编号</td>
                    <td width="50px">开票金额</td>
                </tr>
                <tr>
                    <td width="60px">${Contract.contracttime}</td>
                    <td width="180px">${Contract.contractname}</td>
                    <td width="180px">${Contract.invoicetitle}</td>
                    <td width="50px">${Contract.address}</td>
                    <td width="50px">${Contract.contractcontent}</td>
                    <td width="80px">${Contract.invoicedetail}</td>
                    <td width="50px">${Contract.invoicetime}</td>
                    <td width="50px">${Contract.invoicenumber}</td>
                    <td width="50px">${Contract.invoiceamount}</td>
                </tr>
            </table>
        </c:when>
    </c:choose>

</fieldset>
<br>
<br>
<form action="<%=request.getContextPath()%>/ModifyContractServlet"
      method="post">
    <fieldset>
        <legend>
            <font color="#FFFFFF">合同信息修改栏</font>
        </legend>
        <c:choose>
            <c:when test="${empty Contract}">
                <font color="#FFFFFF">请先进行查询操作。</font>
            </c:when>
            <c:when test="${!empty Contract}">
                <table class="bordered" bgcolor="#FFFFFF">
                    <tr>
                        <td>签合同时间(*)</td>
                        <td>合同名称(*)</td>
                        <td>对应发票抬头(*)</td>
                        <td>公司地址(*)</td>
                        <td>合同内容(*)</td>
                        <td>对应发票明细(*)</td>
                        <td>开票时间(*)</td>
                        <td>发票编号(*)</td>
                        <td>开票金额(*)</td>
                    </tr>
                    <tr>
                        <td width="50px"><input type="text" class="input1"
                                                name="contracttime" id="contracttime" value="${Contract.contracttime}"></td>
                        <td width="180px"><input type="text" class="input1"
                                                 name="contractname" id="contractname" value="${Contract.contractname}"></td>
                        <td width="180px"><input type="text" class="input1"
                                                 name="invoicetitle" id="invoicetitle" value="${Contract.invoicetitle}"></td>
                        <td width="80px"><input type="text" class="input1"
                                                name="address" id="address" value="${Contract.address}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="contractcontent" id="contractcontent" value="${Contract.contractcontent}"></td>
                        <td width="60px"><input type="text" class="input1"
                                                name="invoicedetail" id="invoicedetail" value="${Contract.invoicedetail}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoicetime" id="invoicetime" value="${Contract.invoicetime}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoicenumber" id="invoicenumber" value="${Contract.invoicenumber}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoiceamount" id="invoiceamount" value="${Contract.invoiceamount}"></td>
                    </tr>
                </table>
                <br>
                <font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
                <table>
                    <tr>
                        <td><input type="hidden" id="id" name="id"
                                   value="${Contract.id}"> <input type="submit" onclick="return check()"
                                                                  id="buttonbin1" class="but1" value="提交"></td>
                    </tr>
                </table>
            </c:when>
        </c:choose>
    </fieldset>
</form>
</body>
</html>